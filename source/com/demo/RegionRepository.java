package com.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * Created by EE on 15.12.2017.
 */
@Component
public interface RegionRepository extends JpaRepository<Region, Long> {
    Region findFirstByName(String name);
}