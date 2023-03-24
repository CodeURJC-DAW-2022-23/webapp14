package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;


@RestController
@RequestMapping("/api/search")
public class RestSearchController {
    @Autowired
    private PackRepository packRepository;

    @GetMapping("/{keyword}")
    public Collection<Pack> search(@PathVariable String keyword){
        Collection <Pack> packs = packRepository.findBypackDescriptionLongContaining(keyword);
        return packs;
    }

}
