package it.sbcourse.sbpostit.postit.boundary;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.sbcourse.sbpostit.postit.control.PostItService;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/postits")
public class PostItResources {

    private final Logger log = LoggerFactory.getLogger(getClass());

    PostItService service;

    public PostItResources(PostItService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<PostIt> search(@RequestParam(required = false) String search) {
        return service.search(search);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PostIt> find(@PathVariable(name = "id") Integer postitId) {
        
        Optional<PostIt> opt = service.find(postitId);

        return opt.isEmpty() ?  ResponseEntity.notFound().build() : ResponseEntity.ok(opt.get());
    }

    @PutMapping(path = "/{id}")
    public PostIt update(@PathVariable Integer id, @RequestBody PostIt postit) {
        postit.setId(id);
        return service.update(postit);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
