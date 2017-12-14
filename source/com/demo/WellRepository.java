package com.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by EE on 13.12.2017.
 */
@Component
public interface WellRepository extends JpaRepository<Well, Long> {

    List<Well> findAllByNumber(long number);

    List<Well> findAllByRegion(Region region);

    List<Well> findAllByWellfield(Wellfield wellfield);

    List<Well> findAllByRegionAndWellfield(Region region, Wellfield wellfield);

}
