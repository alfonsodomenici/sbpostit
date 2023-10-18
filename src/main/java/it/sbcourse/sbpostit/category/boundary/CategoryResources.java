package it.sbcourse.sbpostit.category.boundary;

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
import it.sbcourse.sbpostit.postit.boundary.PostItIncomingDTO;
import it.sbcourse.sbpostit.postit.boundary.PostItOutcomingDTO;
import it.sbcourse.sbpostit.postit.control.PostItService;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.validation.Valid;

import java.util.Collection;

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
    public Collection<CategoryOutcomingDTO> search(@RequestParam(required = false) String search) {
        return service.search(search)
                .stream()
                .map(v -> new CategoryOutcomingDTO(v.getId(), v.getNome()))
                .toList();
    }

    @PostMapping
    public CategoryOutcomingDTO create(@RequestBody @Valid CategoryIncomingDTO dto) {
        return service.create(dto).toDTO();
    }

    @GetMapping(path = "/{id}")
    public CategoryOutcomingDTO find(@PathVariable Integer id) {
        return service.find(id).toDTO();
    }

    @PutMapping(path = "/{id}")
    public CategoryOutcomingDTO update(@PathVariable Integer id, @RequestBody @Valid CategoryIncomingDTO dto) {
        return service.update(id, dto).toDTO();
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // ---------------- postits resources

    @GetMapping(path = "/{id}/postits")
    public Collection<PostItOutcomingDTO> postits(@PathVariable Integer id) {
        return postitService.findByCategory(id)
            .stream()
            .map(v -> new PostItOutcomingDTO(v.getId(), v.getMsg(), v.getQuando()))
            .toList();
    }

    @PostMapping(path = "/{id}/postits")
    public PostItOutcomingDTO create(@PathVariable Integer id, @RequestBody @Valid PostItIncomingDTO dto) {
        return postitService.create(service.find(id), dto).toDTO();
    }
}
