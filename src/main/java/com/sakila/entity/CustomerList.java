package com.sakila.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "customer_list")
public class CustomerList {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Column(name = "name", length = 91)
    private String name;

    @Column(name = "address", nullable = false, length = 50)
    private String address;

    @Column(name = "`zip code`", length = 10)
    private String zipCode;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "notes", nullable = false, length = 6)
    private String notes;

    @Column(name = "SID", nullable = false)
    private Integer sid;

    public Integer getSid() {
        return sid;
    }

    public String getNotes() {
        return notes;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getZipCode() {
        return zipCode;
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    protected CustomerList() {
    }
}