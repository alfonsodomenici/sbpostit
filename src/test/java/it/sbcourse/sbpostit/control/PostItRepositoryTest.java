package it.sbcourse.sbpostit.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import it.sbcourse.sbpostit.category.control.CategoryRepository;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItRepository;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

@DataJpaTest
public class PostItRepositoryTest {

    @Autowired
    private PostItRepository repo;

    @Autowired
    private CategoryRepository catReporepo;

    @Test
    public void create() {
        Category cat = catReporepo.save(new Category("SPRING BOOT"));
        PostIt tosave = new PostIt("test message..", LocalDate.now());
        tosave.setCategoria(cat);
        PostIt saved = repo.save(tosave);
        assertThat(saved)
                .hasFieldOrPropertyWithValue("msg", "test message..")
                .hasFieldOrPropertyWithValue("quando", LocalDate.now())
                .hasFieldOrPropertyWithValue("categoria", cat);
    }

    @Test
    public void update() {
        Category cat = catReporepo.save(new Category("SPRING BOOT"));
        PostIt tosave = new PostIt("test message..", LocalDate.now());
        tosave.setCategoria(cat);
        PostIt saved = repo.save(tosave);
        saved.setMsg("updated...");
        PostIt updated = repo.save(saved);
        assertThat(updated)
                .hasFieldOrPropertyWithValue("msg", "updated...")
                .hasFieldOrPropertyWithValue("quando", LocalDate.now())
                .hasFieldOrPropertyWithValue("categoria", cat);
    }

    @Test
    public void delete() {
        Category cat = catReporepo.save(new Category("SPRING BOOT"));
        PostIt tosave = new PostIt("test message..", LocalDate.now());
        tosave.setCategoria(cat);
        PostIt saved = repo.save(tosave);
        repo.deleteById(saved.getId());
        assertThat(repo.findAll())
                .hasSize(0);
    }

    @Test
    public void findById() {
        Category cat = catReporepo.save(new Category("SPRING BOOT"));
        PostIt tosave = new PostIt("test message..", LocalDate.now());
        tosave.setCategoria(cat);
        PostIt saved = repo.save(tosave);
        assertThat(repo.findById(saved.getId())).isPresent();
    }

    @Test
    public void findByCategoria() {
        Category cat = catReporepo.save(new Category("SPRING BOOT"));
        PostIt tosave = new PostIt("test message..", LocalDate.now());
        tosave.setCategoria(cat);
        repo.save(tosave);
        tosave = new PostIt("altro test message..", LocalDate.now());
        tosave.setCategoria(cat);
        repo.save(tosave);
        assertThat(repo.findByCategory(cat.getId())).hasSize(2);
    }

    @Test
    public void deleteByCategoria() {
        Category cat = catReporepo.save(new Category("SPRING BOOT"));
        PostIt tosave = new PostIt("test message..", LocalDate.now());
        tosave.setCategoria(cat);
        repo.save(tosave);
        tosave = new PostIt("altro test message..", LocalDate.now());
        tosave.setCategoria(cat);
        repo.save(tosave);
        repo.deleteByCategory(cat.getId());
        assertThat(repo.findAll()).hasSize(0);
    }

}
