package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/packs")
public class RestPackController {

    // Attributes
    @Autowired
    private PackRepository packRepository;

    //Crea un metodo que devuelva todos los packs
    @GetMapping("/")
    public ResponseEntity<Collection<Pack>> getAllPacks(){
        Collection<Pack> packs = packRepository.findAll();
        return new ResponseEntity<>(packs, HttpStatus.OK);
    }

    // Get a specific pack based on the id
    @GetMapping("/packs/{id}")
    public ResponseEntity<Pack> getPack(@PathVariable long id){
        Optional<Pack> tryPack = packRepository.findById(id);
        if(tryPack.isPresent()) {
            Pack pack = tryPack.get();
            return new ResponseEntity<>(pack, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a specific pack based on the sent pack
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Pack createPack(@RequestBody Pack pack){
        packRepository.save(pack);
        return pack;
    }

    // Updates a pack based on the id and the sent pack
    @PutMapping("/{id}")
    public ResponseEntity<Pack> updatePack(@PathVariable long id, @RequestBody Pack pack){
        Optional<Pack> tryPack = packRepository.findById(id);
        if(tryPack.isPresent()){
            Pack oldPack = tryPack.get();
            oldPack.setPackTitle(pack.getPackTitle());
            oldPack.setPackTitle_expanded(pack.getPackTitle_expanded());
            oldPack.setPackPrice(pack.getPackPrice());
            oldPack.setpackDescriptionLong(pack.getpackDescriptionLong());
            oldPack.setPackDescription_short(pack.getPackDescription_short());
            oldPack.setTags(pack.getTags());
            oldPack.setPackImage(pack.getPackImage());
            packRepository.save(oldPack);
            return new ResponseEntity<>(oldPack, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Deletes a pack based on the id
    @DeleteMapping("/{id}")
    public ResponseEntity<Pack> deletePack(@PathVariable long id){
        Optional<Pack> tryPack = packRepository.findById(id);
        if(tryPack.isPresent()){
            Pack pack = tryPack.get();
            packRepository.delete(pack);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
