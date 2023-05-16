package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Tags;
import com.webApp14.UniHub.repository.TagsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/tags")
public class RestTagsController {

    @Autowired
    private TagsRepository tagsRepository;


    @GetMapping("/")
    public ResponseEntity<Collection<Tags>> getTags(){
        List<Tags> tags = tagsRepository.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }


}
