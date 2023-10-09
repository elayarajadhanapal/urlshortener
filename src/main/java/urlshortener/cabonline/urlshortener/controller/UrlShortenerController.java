package urlshortener.cabonline.urlshortener.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import urlshortener.cabonline.urlshortener.model.UrlShortenerRequest;
import urlshortener.cabonline.urlshortener.service.UrlShortenerService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class UrlShortenerController {

    Logger logger = LoggerFactory.getLogger(UrlShortenerController.class);

    @Autowired
    private UrlShortenerService service;

    @PostMapping("/")
    public ResponseEntity<String> createUrlShortener(@Valid @RequestBody UrlShortenerRequest request){
        logger.debug("Creating url shorten for the request {}", request);
        return ResponseEntity.ok().body(service.createAndStoreShortenedUrl(request.getUrl()));
    }

    @GetMapping("/{shortenedUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortenedUrl) throws URISyntaxException {
        logger.debug("Fetching full url for the shortened url {}", shortenedUrl);
        String url = service.getUrlOfShortenedUrl(shortenedUrl);
        logger.info("Redirecting to {}", url);
        URI uri = new URI(url);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uri);
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(uri).build();
    }
}
