package it.sbcourse.sbpostit;

public record GenericResponseDTO<T>(
    String error,
    T data
) {
    
}
