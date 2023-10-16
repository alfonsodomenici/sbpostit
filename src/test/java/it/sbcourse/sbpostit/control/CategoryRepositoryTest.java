package it.sbcourse.sbpostit.control;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import it.sbcourse.sbpostit.category.control.CategoryRepository;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.control.PostItRepository;
import it.sbcourse.sbpostit.postit.entity.PostIt;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class CategoryRepositoryTest {

        @Autowired
        private CategoryRepository repo;

        @Autowired
        private PostItRepository postitRepo;

        @Test
        public void create() {
                Category tosave = new Category("SPRING BOOT");
                Category saved = repo.save(tosave);
                assertThat(saved)
                                .hasFieldOrPropertyWithValue("nome", "SPRING BOOT");
        }

        @Test
        public void update() {
                Category saved = repo.save(new Category("SPRING BOOT"));
                saved.setNome("updated...");
                Category updated = repo.save(saved);
                assertThat(updated)
                                .hasFieldOrPropertyWithValue("nome", "updated...");
        }

        @Test
        public void delete() {
                Category saved = repo.save(new Category("SPRING BOOT"));
                repo.deleteById(saved.getId());
                assertThat(repo.findAll())
                                .hasSize(0);
        }

        @Test
        public void findById() {
                Category saved = repo.save(new Category("SPRING BOOT"));
                assertThat(repo.findById(saved.getId())).isPresent();
        }

        @Test
        public void findByNome() {
                Category saved = repo.save(new Category("SPRING BOOT"));
                assertThat(repo.findByNomeContains("SPRING BOOT"))
                                .hasSize(1);
                assertThat(repo.findByNomeContains("BOOT"))
                                .hasSize(1);
                assertThat(repo.findByNomeContains("XX"))
                                .hasSize(0);
        }

        @Test
        public void findByName() {
                Category saved = repo.save(new Category("SPRING BOOT"));
                assertThat(repo.findByName("SPRING BOOT"))
                                .hasSize(1);
                assertThat(repo.findByName("%BOOT%"))
                                .hasSize(1);
                assertThat(repo.findByName("%XX%"))
                                .hasSize(0);
        }

        @Test
        public void cercaPerNome() {
                Category saved = repo.save(new Category("SPRING BOOT"));
                assertThat(repo.cercaPerNome("SPRING BOOT"))
                                .hasSize(1);
                assertThat(repo.cercaPerNome("%BOOT%"))
                                .hasSize(1);
                assertThat(repo.cercaPerNome("%XX%"))
                                .hasSize(0);
        }

        @Test
        public void ancoraCercaPerNome() {
                Category saved = repo.save(new Category("SPRING BOOT"));
                assertThat(repo.ancoraCercaPerNome("SPRING BOOT"))
                                .hasSize(1);
                assertThat(repo.ancoraCercaPerNome("%BOOT%"))
                                .hasSize(1);
                assertThat(repo.ancoraCercaPerNome("%XX%"))
                                .hasSize(0);
        }

        @Test
        public void findCategoriesContainingPostitWithMessage() {
                Category cat = repo.save(new Category("SPRING BOOT"));
                Category cat1 = repo.save(new Category("PYTHON FLASK"));
                PostIt tosave = new PostIt("test message..", LocalDate.now());
                tosave.setCategoria(cat);
                postitRepo.save(tosave);
                tosave = new PostIt("altro test message..", LocalDate.now());
                tosave.setCategoria(cat);
                postitRepo.save(tosave);
                tosave = new PostIt("message..", LocalDate.now());
                tosave.setCategoria(cat1);
                postitRepo.save(tosave);

                assertThat(repo.findCategoriesCountaingPostitWithMessage("test"))
                        .hasSize(1);
                assertThat(repo.findCategoriesCountaingPostitWithMessage("message"))
                        .hasSize(2);
        }
}
