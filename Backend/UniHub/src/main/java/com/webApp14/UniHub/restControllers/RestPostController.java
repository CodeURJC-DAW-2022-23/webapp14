package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Post;
import com.webApp14.UniHub.repository.FormsRepository;
import com.webApp14.UniHub.repository.PostRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class RestPostController {

    // Attributes
    @Autowired
    private FormsRepository formsRepository;

    @Autowired
    private PostRepository postRepository;

    // Retrieves all the post available
    @Operation(summary = "Get all the posts")
    @ApiResponse(
            responseCode = "200",
            description = "Found the posts",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))
    )
    @ApiResponse(responseCode = "404", description = "Posts not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @GetMapping("/")
    public Collection<List<Post>> getPosts(){
        Collection<Forms> forms = formsRepository.findAll();
        // Iterate each form and store each post into a list of posts
        Collection<List<Post>> posts = new ArrayList<>();
        for(Forms form : forms){
            posts.add(form.getPosts());
        }
        return posts;
    }

    // Create a post for a form given its id and the post
    @Operation(summary = "Create a new post for a form")
    @ApiResponse(
            responseCode = "201",
            description = "Post created",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))
    )
    @ApiResponse(responseCode = "404", description = "Form not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @PostMapping("/comment/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Post> createPost(@RequestBody Post post, @PathVariable long id){
        Optional<Forms> tryForm = formsRepository.findById(id);
        System.out.println(tryForm);
        if(tryForm.isPresent()){
            Forms form = tryForm.get();
            /*LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
            String formattedDate = now.format(formatter);
            post.setPostDate(formattedDate);*/
            form.getPosts().add(post);
            postRepository.save(post);
            formsRepository.save(form);
            return new ResponseEntity<>(post, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a certain post given its id and the form id
    @Operation(summary = "Delete a post for a form")
    @ApiResponse(
            responseCode = "200",
            description = "Post deleted",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))
    )
    @ApiResponse(responseCode = "404", description = "Form or post not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @DeleteMapping("/{formId}/comment/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable long id, @PathVariable long formId){
        Optional<Forms> tryForm = formsRepository.findById(formId);
        if(tryForm.isPresent()){
            Forms form = tryForm.get();
            Optional<Post> tryPost = postRepository.findById(id);
            if(tryPost.isPresent()){
                Post post = tryPost.get();
                form.getPosts().remove(post);
                postRepository.delete(post);
                formsRepository.save(form);
                return new ResponseEntity<>(post, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Update a certain post given its id and the form id
    @Operation(summary = "Update a post for a form")
    @ApiResponse(
            responseCode = "200",
            description = "Post updated",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Post.class))
    )
    @ApiResponse(responseCode = "404", description = "Form or post not found", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @ApiResponse(responseCode = "400", description = "Bad request", content = @Content)
    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    @PutMapping("/{formId}/comment/{id}")
    public ResponseEntity<Post> updatePost( @PathVariable long formId, @PathVariable long id, @RequestBody Post postNew){
        Optional<Forms> tryForm = formsRepository.findById(formId);
        if(tryForm.isPresent()){
            Forms form = tryForm.get();
            Optional<Post> tryPost = postRepository.findById(id);
            if(tryPost.isPresent()){
                Post postUpdate = tryPost.get();
                postUpdate.setPostContent(postNew.getPostContent());
                postUpdate.setPostUpvotes(postNew.getPostUpvotes());
                postUpdate.setPostAuthor(postNew.getPostAuthor());
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy : HH:mm");
                String formattedDate = now.format(formatter);
                postUpdate.setPostDate(formattedDate);
                postRepository.save(postUpdate);
                // Convert long to int
                int idInt = (int) id;
                form.getPosts().get(idInt-1).setPostDate(postUpdate.getPostDate());
                form.getPosts().get(idInt-1).setPostAuthor(postUpdate.getPostAuthor());
                form.getPosts().get(idInt-1).setPostContent(postUpdate.getPostContent());
                form.getPosts().get(idInt-1).setPostUpvotes(postUpdate.getPostUpvotes());
                formsRepository.save(form);
                return new ResponseEntity<>(postUpdate, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}
