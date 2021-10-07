package org.learning.promo.data;

import lombok.Data;

import java.util.List;

@Data
public class SKUComb extends BaseCondition {
    List<String> skuList;
}
