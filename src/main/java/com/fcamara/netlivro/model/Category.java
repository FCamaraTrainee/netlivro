package com.fcamara.netlivro.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
}
