package com.sakila.entity;

import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @UpdateTimestamp
    @Column(name = "last_update", nullable = false)
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "category")
    private Set<FilmCategory> filmCategories = new LinkedHashSet<>();

    public Set<FilmCategory> getFilmCategories() {
        return filmCategories;
    }

    public void setFilmCategories(Set<FilmCategory> filmCategories) {
        this.filmCategories = filmCategories;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}