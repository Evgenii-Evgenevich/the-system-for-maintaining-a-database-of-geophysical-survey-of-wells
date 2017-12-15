package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by EE on 13.12.2017.
 */
@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MyRestController {

    @Autowired
    WellRepository wellRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    WellfieldRepository wellfieldRepository;

    @Autowired
    public MyRestController(WellRepository wellRepository, RegionRepository regionRepository, WellfieldRepository wellfieldRepository) {
        this.wellRepository = wellRepository;
        this.regionRepository = regionRepository;
        this.wellfieldRepository = wellfieldRepository;
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

}
