package com.fcamara.netlivro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    @ManyToMany(mappedBy="categories")
    private List<Book> books = new ArrayList<>();
}
