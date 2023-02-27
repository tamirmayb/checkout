package com.tamirm.checkout;

import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.reader.JsonRuleDefinitionReader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

public class CheckoutRegistry {
    private final Map<String, RegistryItem> groceries = new HashMap<>();
    private final CheckoutItemCalculator calculator = new CheckoutItemCalculator();


    public void initRegistry(String configFileName) throws Exception {
        MVELRuleFactory ruleFactory = new MVELRuleFactory(new JsonRuleDefinitionReader());
        Rules rules = ruleFactory.createRules(new FileReader(configFileName));
        calculator.initCheckoutItemCalculator(rules);
        groceries.clear();
    }

    public void initRegistry(Rules rules) {
        calculator.initCheckoutItemCalculator(rules);
    }

    public void registerItem(String itemName) {
        RegistryItem registryItem = RegistryItem.of(itemName);
        groceries.putIfAbsent(itemName, registryItem);
        RegistryItem item = groceries.get(itemName);
        int quantity = item.getQuantity();
        item.setQuantity(++quantity);
        groceries.replace(itemName, item);
    }

    public float checkout(String checkoutItems) {
        for (int i = 0; i < checkoutItems.length(); i++) {
            registerItem(checkoutItems.substring(i, i + 1));
        }
        return total();
    }

    public float total() {
        AtomicReference<Float> totalPrice = new AtomicReference<>((float) 0);
        groceries.values().forEach(registryItem -> totalPrice
                .set(totalPrice.get() + calculator.calculateRegistryItemPrice(registryItem)));
        groceries.clear();
        return totalPrice.get();
    }
}
