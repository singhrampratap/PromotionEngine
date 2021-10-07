package org.learning.promo.data;

import lombok.Data;

import java.util.List;

@Data
public class Cart {
    List<CartItem> cartItems;

    @Data
    public static class CartItem {
        String skuId;
        int quantity;
    }


}
