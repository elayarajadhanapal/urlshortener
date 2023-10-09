package urlshortener.cabonline.urlshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import urlshortener.cabonline.urlshortener.exception.UrlShortenerNotFoundException;
import urlshortener.cabonline.urlshortener.repository.UrlShortenerRepository;

import java.util.Optional;

@Service
public class UrlShortenerService {

    Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

    @Autowired
    private UrlShortenerRepository repository;

    public String createAndStoreShortenedUrl(String url) {
        String shortenedUrl = generateShortenedUrl();
        saveUrlShortenerDetails(shortenedUrl, url);
        logger.info("Shortened url {} generated for the url {}", shortenedUrl, url);
        return shortenedUrl;
    }

    private void saveUrlShortenerDetails(String shortenedUrl, String url) {
        var value = repository.saveUrlShortenerDetails(shortenedUrl, url);
        if (null == value){
            new Exception("Server error. Unable to save the url and shortened url ");
        }
    }

    private String generateShortenedUrl(){
        return RandomStringUtils.randomAlphanumeric(5);
    }

    public String getUrlOfShortenedUrl(String shortenedUrl) {
        Optional<String> optionalUrl = repository.getActualUrlForShortenedUrl(shortenedUrl);
        return optionalUrl.orElseThrow(() -> new UrlShortenerNotFoundException(String.format("Full url not found for the shortened url {}", shortenedUrl)));
    }
}
