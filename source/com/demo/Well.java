package com.demo;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by EE on 13.12.2017.
 */
@Entity
@Data
public class Well implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //@NotNull
    //private String name;

    @NotNull
    private long number;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Lazy
    @NotNull
    @Column(name = "wellfield_id")
    private Wellfield wellfield;

    public void setWellfield(Wellfield wellfield) {
        this.wellfield = wellfield;
    }

    public Wellfield getWellfield() {
        return wellfield;
    }

    @Lazy
    @NotNull
    @Column(name = "region_id")
    private Region region;

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @NotNull
    private double x;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    @NotNull
    private double y;

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @NotNull
    private double z;

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public Well(long number, Wellfield wellfield, Region region, double x, double y, double z) {
        this.number = number;
        this.wellfield = wellfield;
        this.region = region;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
