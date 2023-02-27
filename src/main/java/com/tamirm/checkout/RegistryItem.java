package com.tamirm.checkout;

import lombok.*;

@Setter
@Getter
@Builder
public class RegistryItem {
    private String name;
    private int quantity;
    private float price;

    public static RegistryItem of (String name) {
        return RegistryItem.builder()
                .name(name)
                .build();
    }
}