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

    @NotNull
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "wellfield_id")
    private Wellfield wellfield;

    @NotNull
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "region_id")
    private Region region;

    @NotNull
    private double x;

    public double getX() {
        return x;
    }

    @NotNull
    private double y;

    public double getY() {
        return y;
    }

    @NotNull
    private double z;

    public double getZ() {
        return z;
    }

    @NotNull
    private short intensity;

    public short getIntensity() {
        return intensity;
    }

    @NotNull
    private double time;

    public double getTime() {
        return time;
    }

    public Well() {}

    public Well(long number, Wellfield wellfield, Region region, double x, double y, double z, short intensity, double time) {
        this.number = number;
        this.wellfield = wellfield;
        this.region = region;
        this.x = x;
        this.y = y;
        this.z = z;
        this.intensity = intensity;
        this.time = time;
    }
}
