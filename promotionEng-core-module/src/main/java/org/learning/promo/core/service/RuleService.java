package org.learning.promo.core.service;

import org.learning.promo.data.CartDto;
import org.learning.promo.data.Rule;

import java.util.List;

public interface RuleService {

    List<Rule> getRules();

    boolean applicable(CartDto cartDto,Rule rule);
}
