package it.sbcourse.sbpostit.control;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.sbcourse.sbpostit.category.control.CategoryService;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItService;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private PostItService postitService;

    @Autowired
    private CategoryService service;

    @Test
    public void delete() {
        Category tosave = new Category("TEST");
        Category saved = service.create(tosave);
        PostIt postit = new PostIt("test message..", LocalDate.now());
        postit.setCategoria(saved);
        postitService.create(postit);
        postit = new PostIt("altro test message..", LocalDate.now());
        postit.setCategoria(saved);
        postitService.create(postit);
        service.delete(saved.getId());
        assertThat(service.search(null)).hasSize(0);
    }

}
