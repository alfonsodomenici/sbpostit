package it.sbcourse.sbpostit.postit;

import static org.assertj.core.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@SpringBootTest
public class PostItTest {

    @Autowired
    Validator validator;

    @Test
    public void todoCreationOk() {
        PostIt p = new PostIt("ciao");
        assertThat(p).isNotNull()
                .hasFieldOrProperty("id")
                .hasFieldOrPropertyWithValue("msg", "ciao");
        Set<ConstraintViolation<PostIt>> validationResults = validator.validate(p);
        assertThat(validationResults).isEmpty();
    }

    @Test
    public void todoCreationBlankFailed() {
        PostIt p = new PostIt("   ");
        Set<ConstraintViolation<PostIt>> validationResults = validator.validate(p);
        assertThat(validationResults).isNotEmpty();
    }

    @Test
    public void todoCreationSizeFailed() {
        PostIt p = new PostIt("abbiamo quasi finito..");
        Set<ConstraintViolation<PostIt>> validationResults = validator.validate(p);
        assertThat(validationResults).isNotEmpty();
    }

    @Test
    public void todoCreationIdNullFailed() {
        PostIt p = new PostIt("abbiamo quasi finito..");
        p.setId(null);
        Set<ConstraintViolation<PostIt>> validationResults = validator.validate(p);
        assertThat(validationResults).isNotEmpty();
    }

    @Test
    public void todoCreationIdNegativeFailed() {
        PostIt p = new PostIt("abbiamo quasi finito..");
        p.setId(-10);
        Set<ConstraintViolation<PostIt>> validationResults = validator.validate(p);
        assertThat(validationResults).isNotEmpty();
    }

}
