package com.fcamara.netlivro.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Author {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private Boolean active = true;
    @OneToMany(
            mappedBy="author", orphanRemoval = true
    )
    private List<Book> books;

}
