package urlshortener.cabonline.urlshortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import urlshortener.cabonline.urlshortener.utility.ApplicationConstants;

@ControllerAdvice
public class UrlShortenerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(Exception e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setDetail(ApplicationConstants.SERVER_ERROR);
        return problemDetail;
    }

    @ExceptionHandler(UrlShortenerNotFoundException.class)
    public ProblemDetail handleBadRequestException(UrlShortenerNotFoundException e){
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setDetail(ApplicationConstants.URL_SHORTENER_KEY_NOT_FOUND);
        return problemDetail;
    }
}
