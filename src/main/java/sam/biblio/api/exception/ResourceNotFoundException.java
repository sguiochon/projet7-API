package sam.biblio.api.exception;

/**
 * Exception raised when a resource doesn't exist
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Long id){
        super("No " + resourceName + " exists having id=" + id);
    }

    public ResourceNotFoundException(String resourceName, String identifier){
        super("No " + resourceName + " exists having identifier=" + identifier);
    }

}
