package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.repository.FormsRepository;
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
@RequestMapping("/api/forms")
public class RestFormsController {

    @Autowired
    private FormsRepository formsRepository;

    @GetMapping("/")
    public Collection<Forms> getForms() {
        return formsRepository.findAll();
    }

    @Operation(summary = "Get a form by id")
    @ApiResponse(
            responseCode = "200",
            description = "Found the form",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Forms.class))
    )
    @ApiResponse(responseCode = "404", description = "Form not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @GetMapping("/{id}")
    public ResponseEntity<Forms> getForm(@PathVariable long id) {
        Optional<Forms> tryForm = formsRepository.findById(id);
        return tryForm.map(form -> new ResponseEntity<>(form, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Create a new form")
    @ApiResponse(
            responseCode = "201",
            description = "Form created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Forms.class))
    )
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Forms createForm(@RequestBody Forms form) {
        formsRepository.save(form);
        return form;
    }

    @Operation(summary = "Update an existing form by id")
    @ApiResponse(
            responseCode = "200",
            description = "Form updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Forms.class))
    )
    @ApiResponse(responseCode = "404", description = "Form not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @PutMapping("/{id}")
    public ResponseEntity<Forms> updateForm(@PathVariable long id, @RequestBody Forms form) {
        Optional<Forms> tryForm = formsRepository.findById(id);
        return tryForm.map(formToUpdate -> {
            formToUpdate.setThreadAuthor(form.getThreadAuthor());
            formToUpdate.setThreadContent_short(form.getThreadContent_short());
            formToUpdate.setThreadContent(form.getThreadContent());
            formToUpdate.setThreadUpvotes(form.getThreadUpvotes());
            formToUpdate.setPosts(form.getPosts());
            formToUpdate.setThreadDate(form.getThreadDate());
            formToUpdate.setThreadTitle(form.getThreadTitle());
            formToUpdate.setThreadImage(form.getThreadImage());
            formsRepository.save(formToUpdate);
            return new ResponseEntity<>(formToUpdate, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(summary = "Delete a form by id")
    @ApiResponse(
            responseCode = "200",
            description = "Form deleted successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Forms.class))
    )
    @ApiResponse(responseCode = "404", description = "Form not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Forms> deleteForm(@PathVariable long id) {
        Optional<Forms> tryForm = formsRepository.findById(id);
        return tryForm.map(form -> {
            formsRepository.delete(form);
            return new ResponseEntity<>(form, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
