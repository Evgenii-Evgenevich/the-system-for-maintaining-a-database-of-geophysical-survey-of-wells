package com.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by EE on 13.12.2017.
 */
public interface WellRepository extends JpaRepository<Well, Long> {

    List<Well> findAllByName();

    List<Well> findAllByRegion();

    List<Well> findAllByWellfield();

    List<Well> findAllByRegionAndWellfield();

}
