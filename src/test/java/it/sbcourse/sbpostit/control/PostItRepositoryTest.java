package it.sbcourse.sbpostit.control;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import it.sbcourse.sbpostit.category.control.CategoryRepository;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItRepository;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
public class PostItRepositoryTest {

    @Autowired
    PostItRepository repo;

    @Autowired
    CategoryRepository catRepo;

    @Test
    public void create() {
        Category cat = catRepo.save(new Category("TEST"));
        PostIt postIt = new PostIt("ciao", LocalDate.now());
        postIt.setCategoria(cat);
        PostIt saved = repo.save(postIt);
        assertThat(saved)
                .hasFieldOrPropertyWithValue("msg", "ciao")
                .hasFieldOrPropertyWithValue("quando", LocalDate.now())
                .hasFieldOrPropertyWithValue("categoria", cat);
    }

    @Test
    public void searchByMsg() {
        Category cat = catRepo.save(new Category("TEST"));
        PostIt postIt = new PostIt("ciao", LocalDate.now());
        postIt.setCategoria(cat);
        PostIt saved = repo.save(postIt);
        postIt = new PostIt("arrivederci", LocalDate.now());
        postIt.setCategoria(cat);
        saved = repo.save(postIt);
        Page<PostIt> result = repo.findByMsgContains("ciao", PageRequest.of(0, 10));
        assertThat(result.getTotalElements()).isEqualTo(1);
    }

    @Test
    public void findByCategory() {
        Category cat = catRepo.save(new Category("TEST"));
        PostIt postIt = new PostIt("ciao", LocalDate.now());
        postIt.setCategoria(cat);
        PostIt saved = repo.save(postIt);
        postIt = new PostIt("arrivederci", LocalDate.now());
        postIt.setCategoria(cat);
        saved = repo.save(postIt);
        ;
        assertThat(repo.findByCategory(cat.getId()))
                .hasSize(2);

        assertThat(repo.dellaCategoria(cat.getId()))
                .hasSize(2);

        assertThat(repo.ancoraDellaCategoria(cat.getId()))
                .hasSize(2);
    }
}
