package urlshortener.cabonline.urlshortener.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import urlshortener.cabonline.urlshortener.service.UrlShortenerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UrlShortenerRepository {

    Logger logger = LoggerFactory.getLogger(UrlShortenerRepository.class);

    private Map<String, String> urlShortenerMap = new HashMap<>();

    public String saveUrlShortenerDetails(String shortenedUrl, String url){
        logger.debug("Inserting key {} and value {} in map", shortenedUrl, url);
        return urlShortenerMap.put(shortenedUrl, url);
    }

    public Optional<String> getActualUrlForShortenedUrl(String shortenedUrl){
        logger.debug("Fetching value for the key {} from map.", shortenedUrl);
        return Optional.ofNullable(urlShortenerMap.get(shortenedUrl));
    }
}
