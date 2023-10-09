package urlshortener.cabonline.urlshortener.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import urlshortener.cabonline.urlshortener.repository.UrlShortenerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UrlShortenerServiceTest {

    @MockBean
    private UrlShortenerRepository repository;

    @InjectMocks
    private UrlShortenerService service;

    @Test
    public void urlShortened_success(){
        String url = "http://www.cabonline.com";
        Mockito.doNothing().when(repository).saveUrlShortenerDetails(eq(url), anyString());

        var actualResponse = service.createAndStoreShortenedUrl(url);
        assertTrue(actualResponse.length() == 5);

        Mockito.when(repository.getActualUrlForShortenedUrl(actualResponse)).thenReturn(Optional.of(url));
        var optionalUrlFromRepository = repository.getActualUrlForShortenedUrl(actualResponse);
        assertEquals(url, optionalUrlFromRepository.isPresent());
    }
}
