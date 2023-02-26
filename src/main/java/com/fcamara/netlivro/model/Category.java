package com.fcamara.netlivro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category implements Serializable {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    @ManyToMany(mappedBy="categories")
    @JsonIgnore
    private List<Book> books = new ArrayList<>();
}
