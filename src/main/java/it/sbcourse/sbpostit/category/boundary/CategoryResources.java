package it.sbcourse.sbpostit.category.boundary;

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

import it.sbcourse.sbpostit.category.control.CategoryService;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItService;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.validation.Valid;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class CategoryResources {

    private final CategoryService service;
    private final PostItService postitService;



    public CategoryResources(CategoryService service, PostItService postitService) {
        this.service = service;
        this.postitService = postitService;
    }

    @GetMapping
    public Collection<Category> search(@RequestParam(required = false) String search) {
        return service.search(search);
    }

    @PostMapping
    public Category create(@RequestBody @Valid CategoryIncomingDTO entity) {
        return service.create(Category.from(entity));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Category> find(@PathVariable Integer id) {
        Optional<Category> opt = service.find(id);
        return opt.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(opt.get());
    }

    @PutMapping(path = "/{id}")
    public Category update(@PathVariable Integer id, @RequestBody @Valid CategoryIncomingDTO dto) {
        
        return service.update(id, service.find(id).absorbeFrom(dto));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    //---------------- postits resources

    @GetMapping(path = "/{id}/postits")
    public Collection<PostIt> postits(@PathVariable Integer id) {
        return postitService.findByCategory(id);
    }

    @PostMapping(path = "/{id}/postits")
    public PostIt create(@PathVariable Integer id, @RequestBody @Valid PostIt postit) {
        Category category = service.find(id).get();
        postit.setCategoria(category);
        return postitService.create(postit);
    }
}
