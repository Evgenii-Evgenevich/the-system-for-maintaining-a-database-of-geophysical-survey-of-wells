package com.demo;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by EE on 14.12.2017.
 */
@Entity
@Data
public class Wellfield implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String title;

    public String getTitle() {
        return title;
    }

    @OneToOne(optional = false, mappedBy = "wellfield")
    private Well well;

    public Wellfield() {}

    public Wellfield(String title) {
        this.title = title;
    }

}
