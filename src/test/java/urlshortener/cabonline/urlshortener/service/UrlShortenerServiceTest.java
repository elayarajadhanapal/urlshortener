package urlshortener.cabonline.urlshortener.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;
import urlshortener.cabonline.urlshortener.repository.UrlShortenerRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UrlShortenerServiceTest {

    @MockBean
    private UrlShortenerRepository repository;

    @InjectMocks
    private UrlShortenerService service;

    @BeforeAll
    public void setUp() {
        ReflectionTestUtils.setField(service, "shortenedUrlLength", 5);
    }

    @Test
    public void urlShortened_success(){
        String url = "http://www.cabonline.com";
        Mockito.when(repository.saveUrlShortenerDetails(any(), eq(url))).thenReturn(null);

        var actualResponse = service.createAndStoreShortenedUrl(url);
        assertTrue(actualResponse.length() == 5);

        Mockito.when(repository.getActualUrlForShortenedUrl(actualResponse)).thenReturn(Optional.of(url));
        var optionalUrlFromRepository = repository.getActualUrlForShortenedUrl(actualResponse);
        if(optionalUrlFromRepository.isPresent()){
            assertEquals(url, optionalUrlFromRepository.get());
        }

    }
}
