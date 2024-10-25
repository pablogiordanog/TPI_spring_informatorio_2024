package com.informatorio.resetascocina_app.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 60, columnDefinition = "varchar(60)", nullable = false)
    private String name;

    @Column(length = 500, columnDefinition = "varchar(500)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "step_id")
    private Step step;
}

