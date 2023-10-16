package it.sbcourse.sbpostit.postit.boundary;

import java.util.Collection;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.sbcourse.sbpostit.category.boundary.CategoryOutcomingDTO;
import it.sbcourse.sbpostit.postit.control.PostItService;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.validation.Valid;

@Tag(name = "PostIt Resources", description = "Rest Controller per gestire le risorse PostIt")
@RestController
@RequestMapping("/postits")
public class PostItResources {

    private final Logger log = LoggerFactory.getLogger(getClass());

    PostItService service;

    public PostItResources(PostItService service) {
        this.service = service;
    }

    @ResponseStatus(code = HttpStatus.OK)
    @Operation(summary = "Ricerca PostIt", description = "Restituisce tutti i PostIt oppure cerca nel testo...")
    @GetMapping
    public Collection<PostItOutcomingFullDTO> search(@RequestParam(required = false) String search,
            Pageable pageable) {
        log.info(pageable.toString());
        Page<PostIt> results = service.search(search, pageable);
        return results.getContent()
                .stream()
                .map(v -> new PostItOutcomingFullDTO(v.getId(), v.getMsg(), v.getQuando(),
                        new CategoryOutcomingDTO(v.getCategoria().getId(), v.getCategoria().getNome())))
                .toList();
    }

    @GetMapping(path = "/{id}")
    public PostItOutcomingDTO find(@PathVariable(name = "id") Integer postitId) {
        return service.find(postitId).toDTO();
    }

    @PutMapping(path = "/{id}")
    public PostItOutcomingDTO update(@PathVariable Integer id, @RequestBody @Valid PostItIncomingDTO dto) {
        return service.update(id, dto).toDTO();
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
