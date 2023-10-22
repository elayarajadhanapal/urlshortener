package urlshortener.cabonline.urlshortener.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import urlshortener.cabonline.urlshortener.repository.UrlShortenerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;


@ExtendWith(MockitoExtension.class)
public class UrlShortenerServiceTest {

    @Mock
    private UrlShortenerRepository repository;

    @InjectMocks
    UrlShortenerService service;

    @Test
    public void urlShortened_default_length_success(){
        String url = "http://www.cabonline.com";
        Mockito.when(repository.saveUrlShortenerDetails(any(), eq(url))).thenReturn(null);

        ReflectionTestUtils.setField(service, "shortenedUrlLength", 5);

        var actualResponse = service.createAndStoreShortenedUrl(url);
        assertTrue(actualResponse.length() == 5);

        Mockito.when(repository.getActualUrlForShortenedUrl(actualResponse)).thenReturn(Optional.of(url));
        var optionalUrlFromRepository = repository.getActualUrlForShortenedUrl(actualResponse);
        if(optionalUrlFromRepository.isPresent()){
            assertEquals(url, optionalUrlFromRepository.get());
        }
    }

    @Test
    public void urlShortened_multiple_hits_increase_length_success(){
        String url = "http://www.cabonline.com";
        Mockito.when(repository.saveUrlShortenerDetails(any(), eq(url))).thenReturn(null);

        ReflectionTestUtils.setField(service, "shortenedUrlLength", 2);

        Mockito.when(repository.getActualUrlForShortenedUrl(any())).thenReturn(Optional.of(url),
                Optional.of(url), Optional.of(url), Optional.of(url), Optional.of(url),Optional.of(url), Optional.empty());

        var actualResponse = service.createAndStoreShortenedUrl(url);
        assertTrue(actualResponse.length() == 3);


        Mockito.when(repository.getActualUrlForShortenedUrl(actualResponse)).thenReturn(Optional.of(url));
        var optionalUrlFromRepository = repository.getActualUrlForShortenedUrl(actualResponse);
        if(optionalUrlFromRepository.isPresent()){
            assertEquals(url, optionalUrlFromRepository.get());
        }
    }
}
