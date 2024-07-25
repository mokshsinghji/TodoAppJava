package com.moksh.annotations;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Todos")
public class Todo {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private
}
