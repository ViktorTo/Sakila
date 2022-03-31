package com.sakila.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Immutable
@Table(name = "nicer_but_slower_film_list")
public class NicerButSlowerFilmList {
    @Id
    @Column(name = "FID")
    private Integer fid;

    @Column(name = "title", length = 128)
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "category", nullable = false, length = 25)
    private String category;

    @Column(name = "price", precision = 4, scale = 2)
    private BigDecimal price;

    @Column(name = "length")
    private Integer length;

    @Lob
    @Column(name = "rating")
    private String rating;

    @Lob
    @Column(name = "actors")
    private String actors;

    public String getActors() {
        return actors;
    }

    public String getRating() {
        return rating;
    }

    public Integer getLength() {
        return length;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public Integer getFid() {
        return fid;
    }

    protected NicerButSlowerFilmList() {
    }
}