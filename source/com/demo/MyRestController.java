package com.demo;

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

    private WellRepository wellRepository;


    public MyRestController(WellRepository repository){
        this.wellRepository = repository;
    }

    @GetMapping
    public List<Well> getWells() {
        return wellRepository.findAll();
    }

    @PostMapping
    public void newWell(Well entity) {
        wellRepository.save(entity);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void editWell(Well entity) {
        wellRepository.save(entity);
    }

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!";
    }

}
