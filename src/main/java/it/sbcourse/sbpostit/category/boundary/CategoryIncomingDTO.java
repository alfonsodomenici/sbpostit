package it.sbcourse.sbpostit.category.boundary;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CategoryIncomingDTO(
        @NotBlank @Size(min = 1, max = 50) String nome

) {

}
