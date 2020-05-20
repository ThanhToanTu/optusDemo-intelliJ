package optus.assessment.demo.exception;

import optus.assessment.demo.model.SearchErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class SearchTextExceptionHandler {

    // add exception handling code
    // add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<SearchErrorResponse> handleException(WrongNumberException exc) {

        // create a SearchErrorResponse
        SearchErrorResponse error = new SearchErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

    }

    // add another exception handler - to catch any exception (catch all)
    @ExceptionHandler
    public ResponseEntity<SearchErrorResponse> handleException(Exception exc) {

        // create a SearchErrorResponse
        SearchErrorResponse error = new SearchErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}

