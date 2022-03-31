package com.sakila.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "sales_by_film_category")
public class SalesByFilmCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "category", nullable = false, length = 25)
    private String category;

    @Column(name = "total_sales", precision = 27, scale = 2)
    private BigDecimal totalSales;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getTotalSales() {
        return totalSales;
    }

    public String getCategory() {
        return category;
    }

    protected SalesByFilmCategory() {
    }
}