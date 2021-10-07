package org.learning.promo.data;

import lombok.Data;

@Data
public class SKUNQuantity extends BaseCondition{
    String skuId;
    int quantity;
}
