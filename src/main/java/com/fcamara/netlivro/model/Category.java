package com.fcamara.netlivro.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    @ManyToMany(mappedBy="categories")
    private List<Book> books = new ArrayList<>();
}
