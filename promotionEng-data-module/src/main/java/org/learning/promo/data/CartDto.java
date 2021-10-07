package org.learning.promo.data;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDto {
    List<CartEntry> cartEntries;
    @Data
    public static class CartEntry {
        String skuId;
        int quantity;
        BigDecimal price;
        BigDecimal totalPriceAfterDiscount;
        boolean promoApplied;
    }
}

