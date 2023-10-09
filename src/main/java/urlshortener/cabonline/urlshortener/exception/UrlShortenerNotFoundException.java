package urlshortener.cabonline.urlshortener.exception;

public class UrlShortenerNotFoundException extends RuntimeException{
    public UrlShortenerNotFoundException(String message){
        super(message);
    }
}
