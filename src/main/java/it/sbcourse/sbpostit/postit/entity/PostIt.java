package it.sbcourse.sbpostit.postit.entity;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
})

public class PostIt implements Serializable {

    public static final String FIND_ALL = "PostIt.findAll";

    public static final String FIND_BY_MESSAGE_CONTAINS = "PostIt.findByMsg";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(nullable = false)
    private String msg;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    @FutureOrPresent
    private LocalDate quando;

    public PostIt() {
        this(null);
    }

    public PostIt(String msg) {
        this.msg = msg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PostIt other = (PostIt) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "PostIt [id=" + id + ", msg=" + msg + "]";
    }

}
