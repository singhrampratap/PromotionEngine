package org.learning.promo.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RuleCondition {

    public String conditionId;
    public String conditionName;
    public PromotionType promotionType;
    public BigDecimal discount;

}
