package urlshortener.cabonline.urlshortener.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import urlshortener.cabonline.urlshortener.exception.UrlShortenerNotFoundException;
import urlshortener.cabonline.urlshortener.repository.UrlShortenerRepository;

import java.util.Optional;

@Service
public class UrlShortenerService {

    Logger logger = LoggerFactory.getLogger(UrlShortenerService.class);

    @Value("${shortened.url.length}")
    private int shortenedUrlLength;

    @Autowired
    private UrlShortenerRepository repository;

    public String createAndStoreShortenedUrl(String url) {
        String shortenedUrl = generateShortenedUrl(0);
        saveUrlShortenerDetails(shortenedUrl, url);
        logger.info("Shortened url {} generated for the url {}", shortenedUrl, url);
        return shortenedUrl;
    }

    private void saveUrlShortenerDetails(String shortenedUrl, String url) {
        var value = repository.saveUrlShortenerDetails(shortenedUrl, url);
        if (null != value){
            throw new UrlShortenerNotFoundException("Server error. Unable to save the url and shortened url ");
        }
    }

    private String generateShortenedUrl(int counter){
        String shortenedUrl = null;
        if (counter<=5){
            logger.debug("Counter {} and shortened URL len {}", counter, shortenedUrlLength);
            shortenedUrl = RandomStringUtils.randomAlphanumeric(shortenedUrlLength);
            if(isShortenedUrlPresentInTheMap(shortenedUrl)){
                generateShortenedUrl(counter++);
            }
        }else {
            shortenedUrlLength++;
            generateShortenedUrl(0);
        }
        return shortenedUrl;
    }

    private boolean isShortenedUrlPresentInTheMap(String shortenedUrl){
        Optional<String> optionalUrl = repository.getActualUrlForShortenedUrl(shortenedUrl);
        if (optionalUrl.isPresent()){
            return true;
        }
        return false;
    }

    public String getUrlOfShortenedUrl(String shortenedUrl) {
        Optional<String> optionalUrl = repository.getActualUrlForShortenedUrl(shortenedUrl);
        return optionalUrl.orElseThrow(() -> new UrlShortenerNotFoundException(String.format("Full url not found for the shortened url {}", shortenedUrl)));
    }
}
