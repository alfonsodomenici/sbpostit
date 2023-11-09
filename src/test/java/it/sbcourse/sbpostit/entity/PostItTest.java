package it.sbcourse.sbpostit.entity;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@ActiveProfiles("test")
@SpringBootTest
public class PostItTest {

    @Autowired
    Validator validator;

    @Test
    public void postItCreationOk() {
        PostIt p = new PostIt("ciao", LocalDate.now());
        assertThat(p).isNotNull()
                .hasFieldOrPropertyWithValue("msg", "ciao");
        Set<ConstraintViolation<PostIt>> validationResults = validator.validate(p);
        assertThat(validationResults).isEmpty();
    }

    @Test
    public void postItCreationBlankFailed() {
        PostIt p = new PostIt();
        Set<ConstraintViolation<PostIt>> validationResults = validator.validate(p);
        assertThat(validationResults).isNotEmpty();
    }

}
