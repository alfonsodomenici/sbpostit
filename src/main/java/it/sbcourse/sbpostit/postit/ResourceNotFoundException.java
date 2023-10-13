package it.sbcourse.sbpostit.postit;

//Eccezione unchecked
public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message){
        super(message);
    }
}
