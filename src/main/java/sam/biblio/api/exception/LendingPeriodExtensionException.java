package sam.biblio.api.exception;

public class LendingPeriodExtensionException extends RuntimeException {

    public LendingPeriodExtensionException(Long id, Integer period){
        super("Attempting to set lending period extension to " + period  + " for lending id=" + id);
    }


}
