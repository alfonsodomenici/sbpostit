package it.sbcourse.sbpostit.control;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import it.sbcourse.sbpostit.category.control.CategoryRepository;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItRepository;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    CategoryRepository repo;

    @Autowired
    PostItRepository postitRepo;

    @Autowired
    TestEntityManager em;

    @Test
    public void categoriesWithPostItContainsMessage() {
        Category cat = repo.save(new Category("JAVA"));
        PostIt postIt = new PostIt("ciao", LocalDate.now());
        postIt.setCategoria(cat);
        postitRepo.save(postIt);
        assertThat(repo.findAll())
                .hasSize(1);
        assertThat(repo.categoriesContainsPostItsWithMessage("ciao"))
                .hasSize(1);
        assertThat(repo.categoriesContainsPostItsWithMessage("xx"))
                .hasSize(0);
    }

}
