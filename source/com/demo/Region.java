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
public class Region implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    public String getName() {
        return name;
    }

    @OneToOne(optional = false, mappedBy = "region")
    private Well well;

    public Region() {}

    public Region(String name) {
        this.name = name;
    }

}
