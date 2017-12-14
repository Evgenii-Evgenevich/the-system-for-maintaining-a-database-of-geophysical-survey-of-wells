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

    @OneToOne(optional = false, mappedBy = "wellfield")
    private Well well;

}
