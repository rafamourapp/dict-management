package com.pp.dictmanagement.api;

import com.pp.dictmanagement.dto.KeyDTO;
import com.pp.dictmanagement.entity.Key;
import com.pp.dictmanagement.service.KeyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "key",produces = MediaType.APPLICATION_JSON_VALUE)
public class KeyResource implements KeyAPI{

    private final KeyService service;

    @PostMapping
    public KeyDTO create(@RequestBody Key key){
        return service.create(key);
    }

    @GetMapping(path = "/{id}")
    public List<Key> findByUserId(@PathVariable String id){
        return service.listKeysByUser(id);
    }

}
