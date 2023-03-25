package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Pack;
import com.webApp14.UniHub.repository.PackRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/api/search")
public class RestSearchController {
    @Autowired
    private PackRepository packRepository;

    @Operation(summary = "Search for a pack")
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
    @GetMapping("/{keyword}")
    public Collection<Pack> search(@PathVariable String keyword){
        Collection <Pack> packs = packRepository.findBypackDescriptionLongContaining(keyword);
        return packs;
    }

}
