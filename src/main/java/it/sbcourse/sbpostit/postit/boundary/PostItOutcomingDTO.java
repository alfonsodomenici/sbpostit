package it.sbcourse.sbpostit.postit.boundary;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

public record PostItOutcomingDTO(
    Integer id,
    String msg,
    @JsonFormat(pattern = "dd/MM/yyyy") LocalDate quando
) {
    
}
