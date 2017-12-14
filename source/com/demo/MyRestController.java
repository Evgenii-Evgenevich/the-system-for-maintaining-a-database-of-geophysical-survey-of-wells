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

    private WellRepository repository;


    public MyRestController(WellRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public List<Well> getWells() {
        return repository.findAll();
    }


    @PostMapping
    public void newWell(Well entity) {
        repository.save(entity);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void editWell(Well entity) {
        repository.save(entity);
    }

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

}
