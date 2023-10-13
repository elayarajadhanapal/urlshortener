# Getting Started

If you have any integrated development environment, you can skip the git and maven setup.

To install git please follow the official documentation:
https://git-scm.com/downloads

To install maven please follow the official documentation:
https://maven.apache.org/install.html

Using IDE (IntelliJ):

Click on file -> Project from Version Control
Give clone URL: 

```
https://github.com/elayarajadhanapal/urlshortener.git
```

Once the project is cloned, all the dependencies will be downloaded automatically.
Make sure you have internet connectivity or if using proxy, please enable the proxy in the IDE proxy settings.


Or if you have the git installed locally you can clone it with
```
git clone https://github.com/elayarajadhanapal/urlshortener.git
```

And import the project into your favourite IDE.

Using maven command:

* Build

```.\mvnw clean install```

* Run

```.\mvnw spring-boot:run```

* Or you can also run the application using

```java -jar target/urlshortener-0.0.1-SNAPSHOT.jar```

Or from IDE you can run main method of UrlShortenerApplication.java
urlshortener\urlshortener\src\main\java\urlshortener\cabonline\urlshortener\UrlShortenerApplication.java


How to test the application

Using postman
Create a POST request with URL, 
```
http://localhost:8080/
```

with body raw json as below

```
{
"url" : "https://www.cabonline.com"
}
```

Hit send button,

You should see 200 OK status with the response body of shortened URL.

Copy the shortened url, append with the url like below,

```
http://localhost:8080/<shortened-url>
```

Using any browser, paste the url in search bar and press enter key of click go button.
You should redirect to the full url, in our example 
https://www.cabonline.com