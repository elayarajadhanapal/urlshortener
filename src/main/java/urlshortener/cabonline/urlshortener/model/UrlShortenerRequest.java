package urlshortener.cabonline.urlshortener.model;

import javax.validation.constraints.NotNull;

public class UrlShortenerRequest {
    @NotNull
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "UrlShortenerRequest{" +
                "url='" + url + '\'' +
                '}';
    }
}
