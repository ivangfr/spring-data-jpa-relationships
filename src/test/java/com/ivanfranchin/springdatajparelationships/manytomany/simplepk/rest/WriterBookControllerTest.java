package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest;

import com.ivanfranchin.springdatajparelationships.MyContainers;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Book;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Writer;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.repository.BookRepository;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.repository.WriterRepository;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.BookResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.CreateBookRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.CreateWriterRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.UpdateBookRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.UpdateWriterRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.WriterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.context.ImportTestcontainers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "spring.jpa.properties.hibernate.enable_lazy_load_no_trans=true"
)
@ImportTestcontainers(MyContainers.class)
class WriterBookControllerTest implements MyContainers {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        writerRepository.deleteAll();
    }

    @Test
    void testGetWriter() {
        Writer writer = writerRepository.save(getDefaultWriter());

        String url = String.format(API_WRITERS_WRITER_ID_URL, writer.getId());
        ResponseEntity<WriterResponse> responseEntity = testRestTemplate.getForEntity(url, WriterResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(writer.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(writer.getName());
        assertThat(responseEntity.getBody().books().size()).isEqualTo(0);
    }

    @Test
    void testCreateWriter() {
        CreateWriterRequest createWriterRequest = new CreateWriterRequest("Ivan Franchin");
        ResponseEntity<WriterResponse> responseEntity = testRestTemplate.postForEntity(
                API_WRITERS_URL, createWriterRequest, WriterResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createWriterRequest.name());
        assertThat(responseEntity.getBody().books().size()).isEqualTo(0);

        Optional<Writer> writerOptional = writerRepository.findById(responseEntity.getBody().id());
        assertThat(writerOptional.isPresent()).isTrue();
        writerOptional.ifPresent(w -> assertThat(w.getName()).isEqualTo(createWriterRequest.name()));
    }

    @Test
    void testUpdateWriter() {
        Writer writer = writerRepository.save(getDefaultWriter());
        UpdateWriterRequest updateWriterRequest = new UpdateWriterRequest("Steve Jobs");

        HttpEntity<UpdateWriterRequest> requestUpdate = new HttpEntity<>(updateWriterRequest);
        String url = String.format(API_WRITERS_WRITER_ID_URL, writer.getId());
        ResponseEntity<WriterResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, requestUpdate, WriterResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(updateWriterRequest.name());

        Optional<Writer> writerOptional = writerRepository.findById(writer.getId());
        assertThat(writerOptional.isPresent()).isTrue();
        writerOptional.ifPresent(w -> assertThat(w.getName()).isEqualTo(updateWriterRequest.name()));
    }

    @Test
    void testDeleteWriter() {
        Writer writer = writerRepository.save(getDefaultWriter());

        String url = String.format(API_WRITERS_WRITER_ID_URL, writer.getId());
        ResponseEntity<WriterResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, WriterResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(writer.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(writer.getName());
        assertThat(responseEntity.getBody().books().size()).isEqualTo(0);

        Optional<Writer> writerOptional = writerRepository.findById(writer.getId());
        assertThat(writerOptional.isPresent()).isFalse();
    }

    @Test
    void testAddWriterBook() {
        final Writer writer = writerRepository.save(getDefaultWriter());
        final Book book = bookRepository.save(getDefaultBook());

        String url = String.format(API_WRITERS_WRITER_ID_BOOKS_BOOK_ID_URL, writer.getId(), book.getId());
        ResponseEntity<WriterResponse> responseEntity = testRestTemplate.postForEntity(url, null, WriterResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(writer.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(writer.getName());
        assertThat(responseEntity.getBody().books().size()).isEqualTo(1);
        assertThat(responseEntity.getBody().books().getFirst().id()).isEqualTo(book.getId());

        Optional<Writer> writerOptional = writerRepository.findById(writer.getId());
        assertThat(writerOptional.isPresent()).isTrue();
        writerOptional.ifPresent(w -> {
            assertThat(w.getBooks().size()).isEqualTo(1);
            assertThat(w.getBooks().contains(book)).isTrue();
        });

        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        assertThat(bookOptional.isPresent()).isTrue();
        bookOptional.ifPresent(b -> {
            assertThat(b.getWriters().size()).isEqualTo(1);
            assertThat(b.getWriters().contains(writer)).isTrue();
        });
    }

    @Test
    void testRemoveWriterBook() {
        Book book = bookRepository.save(getDefaultBook());

        Writer writer = getDefaultWriter();
        writer.addBook(book);
        writer = writerRepository.save(writer);

        String url = String.format(API_WRITERS_WRITER_ID_BOOKS_BOOK_ID_URL, writer.getId(), book.getId());
        ResponseEntity<WriterResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, WriterResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(writer.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(writer.getName());
        assertThat(responseEntity.getBody().books().size()).isEqualTo(0);

        Optional<Writer> writerOptional = writerRepository.findById(writer.getId());
        assertThat(writerOptional.isPresent()).isTrue();
        writerOptional.ifPresent(w -> assertThat(w.getBooks().size()).isEqualTo(0));

        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        assertThat(bookOptional.isPresent()).isTrue();
        bookOptional.ifPresent(b -> assertThat(b.getWriters().size()).isEqualTo(0));
    }

    @Test
    void testGetBook() {
        Book book = bookRepository.save(getDefaultBook());

        String url = String.format(API_BOOKS_BOOK_ID_URL, book.getId());
        ResponseEntity<BookResponse> responseEntity = testRestTemplate.getForEntity(url, BookResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(book.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(book.getName());
        assertThat(responseEntity.getBody().writers().size()).isEqualTo(0);
    }

    @Test
    void testCreateBook() {
        CreateBookRequest createBookRequest = new CreateBookRequest("Introduction to Java 8");
        ResponseEntity<BookResponse> responseEntity = testRestTemplate.postForEntity(
                API_BOOKS_URL, createBookRequest, BookResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(createBookRequest.name());
        assertThat(responseEntity.getBody().writers().size()).isEqualTo(0);

        Optional<Book> bookOptional = bookRepository.findById(responseEntity.getBody().id());
        assertThat(bookOptional.isPresent()).isTrue();
        bookOptional.ifPresent(b -> assertThat(b.getName()).isEqualTo(createBookRequest.name()));
    }

    @Test
    void testUpdateBook() {
        Book book = bookRepository.save(getDefaultBook());
        UpdateBookRequest updateBookRequest = new UpdateBookRequest("Introduction to Java 11");

        HttpEntity<UpdateBookRequest> requestUpdate = new HttpEntity<>(updateBookRequest);
        String url = String.format(API_BOOKS_BOOK_ID_URL, book.getId());
        ResponseEntity<BookResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.PUT, requestUpdate, BookResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().name()).isEqualTo(updateBookRequest.name());

        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        assertThat(bookOptional.isPresent()).isTrue();
        bookOptional.ifPresent(b -> assertThat(b.getName()).isEqualTo(updateBookRequest.name()));
    }

    @Test
    void testDeleteBook() {
        Book book = bookRepository.save(getDefaultBook());

        String url = String.format(API_BOOKS_BOOK_ID_URL, book.getId());
        ResponseEntity<BookResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, BookResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(book.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(book.getName());
        assertThat(responseEntity.getBody().writers().size()).isEqualTo(0);

        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        assertThat(bookOptional.isPresent()).isFalse();
    }

    @Test
    void testAddBookWriter() {
        Book book = bookRepository.save(getDefaultBook());
        Writer writer = writerRepository.save(getDefaultWriter());

        String url = String.format(API_BOOKS_BOOK_ID_WRITERS_WRITER_ID_URL, book.getId(), writer.getId());
        ResponseEntity<BookResponse> responseEntity = testRestTemplate.postForEntity(url, null, BookResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(book.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(book.getName());
        assertThat(responseEntity.getBody().writers().size()).isEqualTo(1);
        assertThat(responseEntity.getBody().writers().getFirst().id()).isEqualTo(writer.getId());

        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        assertThat(bookOptional.isPresent()).isTrue();
        bookOptional.ifPresent(b -> {
            assertThat(b.getWriters().size()).isEqualTo(1);
            assertThat(b.getWriters().contains(writer)).isTrue();
        });

        Optional<Writer> writerOptional = writerRepository.findById(writer.getId());
        assertThat(writerOptional.isPresent()).isTrue();
        writerOptional.ifPresent(w -> {
            assertThat(w.getBooks().size()).isEqualTo(1);
            assertThat(w.getBooks().contains(book)).isTrue();
        });
    }

    @Test
    void testRemoveBookWriter() {
        Book book = bookRepository.save(getDefaultBook());
        Writer writer = writerRepository.save(getDefaultWriter());

        String url = String.format(API_BOOKS_BOOK_ID_WRITERS_WRITER_ID_URL, book.getId(), writer.getId());
        ResponseEntity<BookResponse> responseEntity = testRestTemplate.exchange(
                url, HttpMethod.DELETE, null, BookResponse.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody().id()).isEqualTo(book.getId());
        assertThat(responseEntity.getBody().name()).isEqualTo(book.getName());
        assertThat(responseEntity.getBody().writers().size()).isEqualTo(0);

        Optional<Book> bookOptional = bookRepository.findById(book.getId());
        assertThat(bookOptional.isPresent()).isTrue();
        bookOptional.ifPresent(b -> assertThat(b.getWriters().size()).isEqualTo(0));

        Optional<Writer> writerOptional = writerRepository.findById(writer.getId());
        assertThat(writerOptional.isPresent()).isTrue();
        writerOptional.ifPresent(w -> assertThat(w.getBooks().size()).isEqualTo(0));
    }

    private Writer getDefaultWriter() {
        Writer writer = new Writer();
        writer.setName("Ivan Franchin");
        return writer;
    }

    private Book getDefaultBook() {
        Book book = new Book();
        book.setName("Introduction to Java 8");
        return book;
    }

    private static final String API_WRITERS_URL = "/api/writers";
    private static final String API_WRITERS_WRITER_ID_URL = "/api/writers/%s";
    private static final String API_BOOKS_URL = "/api/books";
    private static final String API_BOOKS_BOOK_ID_URL = "/api/books/%s";
    private static final String API_WRITERS_WRITER_ID_BOOKS_BOOK_ID_URL = "/api/writers/%s/books/%s";
    private static final String API_BOOKS_BOOK_ID_WRITERS_WRITER_ID_URL = "/api/books/%s/writers/%s";
}