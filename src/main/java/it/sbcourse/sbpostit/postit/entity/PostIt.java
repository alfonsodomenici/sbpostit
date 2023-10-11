package it.sbcourse.sbpostit.postit.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import it.sbcourse.sbpostit.BaseEntity;
import it.sbcourse.sbpostit.category.entity.Category;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @FutureOrPresent
    private LocalDate quando;

    @ManyToOne(optional = false)
    private Category categoria;

    public PostIt() {
    }

    public PostIt(String msg, LocalDate quando) {
        this.msg = msg;
        this.quando = quando;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @JsonIgnore
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
