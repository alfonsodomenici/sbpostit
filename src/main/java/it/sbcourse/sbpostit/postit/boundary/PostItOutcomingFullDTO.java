package it.sbcourse.sbpostit.postit.boundary;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import it.sbcourse.sbpostit.category.boundary.CategoryOutcomingDTO;

public record PostItOutcomingFullDTO(
        @JsonUnwrapped PostItOutcomingDTO postit,
        CategoryOutcomingDTO categoria) {

}
