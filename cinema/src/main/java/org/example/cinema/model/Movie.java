package org.example.cinema.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String summary;
    private Double rating;

    @ManyToOne
    private Production production;

    public Movie() {
    }

    public Movie(String title, String summary, Double rating, Production production) {
        this.title = title;
        this.summary = summary;
        this.rating = rating;
        this.production = production;
    }
}
