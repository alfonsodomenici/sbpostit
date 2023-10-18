package it.sbcourse.sbpostit.postit.entity;

import java.time.LocalDate;

import it.sbcourse.sbpostit.BaseEntity;
import it.sbcourse.sbpostit.category.boundary.CategoryIncomingDTO;
import it.sbcourse.sbpostit.category.boundary.CategoryOutcomingDTO;
import it.sbcourse.sbpostit.category.entity.Category;
import it.sbcourse.sbpostit.postit.boundary.PostItIncomingDTO;
import it.sbcourse.sbpostit.postit.boundary.PostItOutcomingDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "post-it")
@NamedQueries({
        @NamedQuery(name = PostIt.FIND_ALL, query = "select e from PostIt e order by e.msg"),
        @NamedQuery(name = PostIt.FIND_BY_MESSAGE_CONTAINS, query = "select e from PostIt e where e.msg like ?1 order by e.msg"),
        @NamedQuery(name = PostIt.FIND_BY_CATEGORY, query = "select e from PostIt e where e.categoria.id = ?1"),
})
public class PostIt extends BaseEntity {

    public static final String FIND_ALL = "PostIt.findAll";

    public static final String FIND_BY_MESSAGE_CONTAINS = "PostIt.findByMsg";

    public static final String FIND_BY_CATEGORY = "PostIt.findByCategory";

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String msg;

    @NotNull
    @FutureOrPresent
    private LocalDate quando;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Category categoria;

    public PostIt() {
    }

    public PostIt(String msg, LocalDate quando) {
        this.msg = msg;
        this.quando = quando;
    }

    public static PostIt fromDTO(PostItIncomingDTO dto) {
        return new PostIt(dto.msg(),dto.quando());
    }

    public PostIt mergeDTO(PostItIncomingDTO dto) {
        this.msg = dto.msg();
        this.quando = dto.quando();
        return this;
    }

    public PostItOutcomingDTO toDTO() {
        return new PostItOutcomingDTO(id, msg, quando);
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LocalDate getQuando() {
        return quando;
    }

    public void setQuando(LocalDate quando) {
        this.quando = quando;
    }

    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return String.format("PostIt {id:%s, msg:%s, quando:%s }", id, msg, quando);
    }

}
