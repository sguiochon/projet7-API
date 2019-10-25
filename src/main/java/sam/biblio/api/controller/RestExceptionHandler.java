package sam.biblio.api.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sam.biblio.api.exception.ExceptionDescription;
import sam.biblio.api.exception.LendingPeriodExtensionException;
import sam.biblio.api.exception.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(ResourceNotFoundException exception) {
        ExceptionDescription desc = new ExceptionDescription(HttpStatus.NOT_FOUND, exception.getMessage(), "");
        return new ResponseEntity<Object>(desc, desc.getStatus());
    }

    @ExceptionHandler(LendingPeriodExtensionException.class)
    protected ResponseEntity<Object> handleLendingPeriodExtensionError(LendingPeriodExtensionException exception) {
        ExceptionDescription desc = new ExceptionDescription(HttpStatus.NOT_ACCEPTABLE, exception.getMessage(), "");
        return new ResponseEntity<Object>(desc, desc.getStatus());
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex, WebRequest request) {
        System.out.println("-----<<<<<-----<<<<<-----<<<<<-----<<<<<-----<<<<<-----<<<<<-----<<<<<-----<<<<<-----<<<<<");
        return new ResponseEntity<Object>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


}
