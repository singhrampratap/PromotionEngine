package org.learning.promo.dao.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class SKU {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PE_ENTITY_GENERATOR")
    @SequenceGenerator(name = "PE_ENTITY_GENERATOR", sequenceName = "PE_ENTITY_SEQUENCE", allocationSize = 1, initialValue = 100)
    private Long id;

    private String skuId;

    private BigDecimal price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        SKU sku = (SKU) o;

        return Objects.equals(id, sku.id);
    }

    @Override
    public int hashCode() {
        return 589290389;
    }
}
