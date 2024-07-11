package cl.felipe.obrestdatajpa.controller;

import cl.felipe.obrestdatajpa.entities.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {


    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:"+port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @DisplayName("Comprobar Hola mundo desde controladores Spring Rest")
    @Test
    void hello() {
        ResponseEntity<String> response = testRestTemplate.getForEntity("/hola", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200,response.getStatusCode().value());
        assertEquals("Hola mundo que tal vamos!! Hasta luego",response.getBody());
    }

    @DisplayName("Comprobar findAll desde controladores Spring Rest")
    @Test
    void findAll() {
        ResponseEntity<Book[]> response =
                testRestTemplate.getForEntity("/api/books", Book[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(200,response.getStatusCode().value());

        List<Book> books = Arrays.asList(response.getBody());
        System.out.println(books.size());


    }

    @DisplayName("Comprobar findOneById desde controladores Spring Rest")
    @Test
    void findOneById() {

        ResponseEntity<Book> response = testRestTemplate.getForEntity("/api/books/8", Book.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());


    }


    @DisplayName("Comprobar create desde controladores Spring Rest")
    @Test
    void create() {

        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String json = """
                {
                        "titulo": "Libro creado desde Spring Test",
                        "autor": "Emily Lavanda",
                        "paginas": 250,
                        "precio": 91.99,
                        "fechaLanzamiento": "2019-12-01",
                        "online": false
                    }
                """;
        HttpEntity<String> request = new HttpEntity<>(json,headers);
        ResponseEntity<Book> response = testRestTemplate.exchange("/api/books", HttpMethod.POST,request,Book.class);
        Book result = response.getBody();

        assertEquals(1L, result.getId());
        assertEquals("Libro creado desde Spring Test", result.getTitulo());


    }
}