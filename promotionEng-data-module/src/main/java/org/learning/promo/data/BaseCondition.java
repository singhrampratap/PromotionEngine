package org.learning.promo.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseCondition {
    public String conditionId;
    public String conditionName;
    public BigDecimal discount;
}
