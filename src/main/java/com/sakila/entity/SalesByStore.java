package com.sakila.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "sales_by_store")
public class SalesByStore {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "store", length = 101)
    private String store;

    @Column(name = "manager", length = 91)
    private String manager;

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

    public String getManager() {
        return manager;
    }

    public String getStore() {
        return store;
    }

    protected SalesByStore() {
    }
}