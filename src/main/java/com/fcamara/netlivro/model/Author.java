package com.fcamara.netlivro.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    @Column(name = "active")
    private Boolean active = true;
    @OneToMany(
            mappedBy="author", orphanRemoval = true
    )
    private List<Book> books;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", active=" + active +
                ", books=" + books +
                '}';
    }
}
