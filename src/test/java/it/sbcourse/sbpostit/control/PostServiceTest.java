package it.sbcourse.sbpostit.control;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import it.sbcourse.sbpostit.postit.control.PostItService;
import it.sbcourse.sbpostit.postit.entity.PostIt;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PostServiceTest {
    
    @Autowired
    PostItService service;

    @Test
    public void createOk(){
        PostIt tosave = new PostIt("test postit..", LocalDate.now());
        PostIt saved = service.create(tosave);
        assertThat(saved)
            .hasNoNullFieldsOrPropertiesExcept("aggiornatoIl");
    }


}
