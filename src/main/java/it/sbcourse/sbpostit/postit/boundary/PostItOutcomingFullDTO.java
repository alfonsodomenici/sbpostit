package it.sbcourse.sbpostit.postit.boundary;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import it.sbcourse.sbpostit.category.boundary.CategoryOutcomingDTO;

public record PostItOutcomingFullDTO(
    Integer id,
    String msg,
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate quando,
    CategoryOutcomingDTO categoria
) {
    
}
