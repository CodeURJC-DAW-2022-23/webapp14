package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @Operation(summary = "Get all packs")
    @ApiResponse(
            responseCode = "200",
            description = "Found the packs",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pack.class))
    )
    @ApiResponse(responseCode = "404", description = "Packs not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @GetMapping("/")
    public ResponseEntity<Collection<Pack>> getPacks(){
        Collection<Pack> packs = packRepository.findAll();
        return new ResponseEntity<>(packs, HttpStatus.OK);
    }

    // Get a specific pack based on the id
    @Operation(summary = "Get a pack by id")
    @ApiResponse(
            responseCode = "200",
            description = "Found the pack",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pack.class))
    )
    @ApiResponse(responseCode = "404", description = "Pack not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @GetMapping("/{id}")
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
    @Operation(summary = "Create a pack")
    @ApiResponse(
            responseCode = "201",
            description = "Pack created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pack.class))
    )
    @ApiResponse(responseCode = "404", description = "Pack not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Pack createPack(@RequestBody Pack pack){
        packRepository.save(pack);
        return pack;
    }

    // Updates a pack based on the id and the sent pack
    @Operation(summary = "Update a pack")
    @ApiResponse(
            responseCode = "200",
            description = "Pack updated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pack.class))
    )
    @ApiResponse(responseCode = "404", description = "Pack not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
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
    @Operation(summary = "Delete a pack")
    @ApiResponse(
            responseCode = "200",
            description = "Pack deleted",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pack.class))
    )
    @ApiResponse(responseCode = "404", description = "Pack not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
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
