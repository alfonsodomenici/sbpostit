package it.sbcourse.sbpostit.category.entity;

import it.sbcourse.sbpostit.BaseEntity;
import it.sbcourse.sbpostit.category.boundary.CategoryIncomingDTO;
import it.sbcourse.sbpostit.postit.entity.PostIt;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@NamedQueries({
    @NamedQuery(name = Category.FIND_BY_NAME, query = "select e from Category e where e.nome like ?1 ")
})

@Entity
@Table(name = "categoria")
public class Category extends BaseEntity {

    public static final String FIND_BY_NAME = "Category.findByName";

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(nullable = false)
    private String nome;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", fetch = FetchType.LAZY)
    private List<PostIt> postits = new ArrayList<>();

    public Category() {
    }

    public Category(String nome) {
        this.nome = nome;
    }

    public static Category from(CategoryIncomingDTO dto){
        return new Category(dto.nome());
    }

    public Category absorbeFrom(CategoryIncomingDTO dto){
        this.nome=dto.nome();
        return this;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<PostIt> getPostits() {
        return postits;
    }

    public void setPostits(List<PostIt> postits) {
        this.postits = postits;
    }

    @Override
    public String toString() {
        return String.format("Category {id:%s, nome:%s}", id, nome);
    }

}
