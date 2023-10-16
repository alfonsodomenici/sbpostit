package it.sbcourse.sbpostit.postit.boundary;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostItIncomingDTO(
        @NotBlank @Size(min = 1, max = 255) String msg,
        @JsonFormat(pattern = "dd/MM/yyyy") @FutureOrPresent LocalDate quando
) {

}
