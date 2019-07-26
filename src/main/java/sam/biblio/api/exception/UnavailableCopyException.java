package sam.biblio.api.exception;

/**
 * Exception raised when one's tries to lend a copy not available (for instance, already lent to someone)
 */
public class UnavailableCopyException extends RuntimeException {

    public UnavailableCopyException(Long id){
        super("Copy with id=" + id + " is already lent.");
    }
}
