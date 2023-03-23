package com.webApp14.UniHub.restControllers;

import com.webApp14.UniHub.model.Forms;
import com.webApp14.UniHub.model.Post;
import com.webApp14.UniHub.repository.FormsRepository;
import com.webApp14.UniHub.repository.PostRepository;
import com.webApp14.UniHub.repository.ThreadPicsRepository;
import com.webApp14.UniHub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api/forms")
public class RestFormsController {

    // Attributes
    @Autowired
    private FormsRepository formsRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThreadPicsRepository threadPicsRepository;

    // Retrieves all the forms available
    @GetMapping("/")
    public Collection<Forms> getForms(){
        return formsRepository.findAll();
    }

    // Deletes a form based on the id
    @DeleteMapping("/{id}")
    public ResponseEntity<Forms> deleteForm(@PathVariable long id){
        Optional<Forms> tryForm = formsRepository.findById(id);
        if(tryForm.isPresent()){
            Forms form = tryForm.get();
            formsRepository.delete(form);
            return new ResponseEntity<>(form, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Updates a form based on the id and the sent form
    @PutMapping("/forms/{id}")
    public ResponseEntity<Forms> updateForm(@PathVariable long id, @RequestBody Forms form){
        Optional<Forms> tryForm = formsRepository.findById(id);
        if(tryForm.isPresent()){
            Forms formToUpdate = tryForm.get();
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
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    // Retrieves a desired form from the list and gives back the correct status if it is found
    @GetMapping("/{id}")
    public ResponseEntity<Forms> getPost(@PathVariable long id){
        Optional<Forms> tryForm = formsRepository.findById(id);
        if(tryForm.isPresent()){
            Forms form = tryForm.get();
            return new ResponseEntity<>(form, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Creates a form based on a sent form
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Forms createForm(@RequestBody Forms form){
        formsRepository.save(form);
        return form;
    }


}
