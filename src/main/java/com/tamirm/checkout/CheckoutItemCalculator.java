package com.tamirm.checkout;

import lombok.NoArgsConstructor;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

@NoArgsConstructor
public class CheckoutItemCalculator {
    private Rules rules;

    public void initCheckoutItemCalculator(Rules rules) {
        this.rules = rules;
    }

    public float calculateRegistryItemPrice(RegistryItem item) {
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Facts facts = new Facts();

        facts.put("item", item);
        rulesEngine.fire(rules, facts);

        return item.getPrice();
    }
}
