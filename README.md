Certainly! Below is a README file for your web crawler application:

---

# Web Crawler Application

The Web Crawler Application is a Java Spring Boot application designed to crawl web pages and extract information in a structured format. The application uses the Jsoup library for HTML parsing and Spring Boot for web functionality.

## Prerequisites

Before running the application, ensure that you have the following installed:

- Java Development Kit (JDK) - version 8 or higher
- Maven build tool

## Getting Started

1. Clone the repository:
    git clone https://github.com/yourusername/web-crawler.git
   

2. Navigate to the project directory:
    cd web-crawler
   

3. Build the application using Maven:
   mvn clean install
   

4. Run the application:
   java -jar target/webCrawler-0.0.1-SNAPSHOT.jar
   

5. Open your web browser and go to [http://localhost:8080/](http://localhost:8080/). This will trigger the web crawler to start crawling the Wikipedia homepage (`https://wikipedia.org/`), and the results will be displayed in JSON format.

## Application Structure

- **WebCrawlerApplication.java**: Main class that starts the Spring Boot application.
- **CrawlerController.java**: Defines the REST endpoint ("/crawl") triggering the crawling process and ("/") for the home page.
- **Crawler.java**: Contains the logic for web crawling, including the recursive crawl method and the method for making HTTP requests.

## Customization

- Modify the starting URL by changing the `url` variable in `CrawlerController.java`.
- Adjust the maximum crawl depth in the `crawl` method of `Crawler.java`.

## Notes

- The crawled data is presented in a tree-like structure with page titles and associated links.
- The application limits crawling depth to 5 levels (`if (level <= 5)`) to prevent excessive crawling.

## Dependencies

- Spring Boot: Web framework for building web applications.
- Jsoup: Java library for working with real-world HTML.

## Acknowledgments

This web crawler is a basic example and can be further enhanced for production-ready solutions. The code provides a foundation for understanding web crawling concepts using Java and Spring Boot.

---
