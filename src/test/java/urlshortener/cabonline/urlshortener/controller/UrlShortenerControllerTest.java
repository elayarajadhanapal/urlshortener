package urlshortener.cabonline.urlshortener.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import urlshortener.cabonline.urlshortener.model.UrlShortenerRequest;
import urlshortener.cabonline.urlshortener.service.UrlShortenerService;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UrlShortenerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UrlShortenerService service;

    @Test
    public void createUrlShortener_success() throws Exception {

        ObjectMapper mapper = new ObjectMapper();

        doReturn("YfcPy")
                .when(service)
                .createAndStoreShortenedUrl("https://www.cabonline.com");

        UrlShortenerRequest request = new UrlShortenerRequest();
        request.setUrl("https://www.cabonline.com");

        mockMvc.perform(
                        post("http://localhost:8080/").content(mapper.writeValueAsBytes(request)).contentType(MediaType.APPLICATION_JSON_VALUE)
                                .accept(MediaType.APPLICATION_JSON_VALUE))

                .andExpect(status().isOk());

        verify(service)
                .createAndStoreShortenedUrl("https://www.cabonline.com");
    }
}
