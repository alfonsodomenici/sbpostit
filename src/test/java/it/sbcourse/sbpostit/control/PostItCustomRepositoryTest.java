package it.sbcourse.sbpostit.control;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.sbcourse.sbpostit.category.control.CategoryCustomRepository;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItCustomRepository;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

/*
 * Viene usato lo stesso datasource con autorollback delle transazioni...
 * 
 */

@SpringBootTest
public class PostItCustomRepositoryTest {

    @Autowired
    PostItCustomRepository repo;

    @Autowired
    CategoryCustomRepository catRepo;

    @Transactional
    @Test
    public void createOk() {
        Category cat = catRepo.create(new Category("TEST"));
        PostIt postIt = new PostIt("ciao", LocalDate.now());
        postIt.setCategoria(cat);
        PostIt saved = repo.create(postIt);
        assertThat(saved)
                .hasFieldOrPropertyWithValue("msg", "ciao")
                .hasFieldOrPropertyWithValue("quando", LocalDate.now())
                .hasFieldOrPropertyWithValue("categoria", cat);
    }
}
