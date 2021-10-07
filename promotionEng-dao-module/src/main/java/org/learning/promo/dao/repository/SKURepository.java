package org.learning.promo.dao.repository;

import org.learning.promo.dao.entity.SKU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SKURepository extends JpaRepository<SKU,Long> {
    Set<SKU> findBySkuIdIn(List<String> skuIds);
}
