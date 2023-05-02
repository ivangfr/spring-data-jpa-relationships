package com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest;

import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.mapper.BookMapper;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Book;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.BookResponse;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.UpdateBookRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.service.BookService;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.service.WriterService;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.mapper.WriterMapper;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.model.Writer;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.CreateBookRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.CreateWriterRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.UpdateWriterRequest;
import com.ivanfranchin.springdatajparelationships.manytomany.simplepk.rest.dto.WriterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WriterBookController {

    private final WriterService writerService;
    private final BookService bookService;
    private final WriterMapper writerMapper;
    private final BookMapper bookMapper;

    // ------
    // Writer

    @GetMapping("/writers/{writerId}")
    public WriterResponse getWriter(@PathVariable Long writerId) {
        Writer writer = writerService.validateAndGetWriter(writerId);
        return writerMapper.toWriterResponse(writer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/writers")
    public WriterResponse createWriter(@Valid @RequestBody CreateWriterRequest createWriterRequest) {
        Writer writer = writerMapper.toWriter(createWriterRequest);
        writer = writerService.saveWriter(writer);
        return writerMapper.toWriterResponse(writer);
    }

    @PutMapping("/writers/{writerId}")
    public WriterResponse updateWriter(@PathVariable Long writerId,
                                       @Valid @RequestBody UpdateWriterRequest updateWriterRequest) {
        Writer writer = writerService.validateAndGetWriter(writerId);
        writerMapper.updateWriterFromRequest(updateWriterRequest, writer);
        writer = writerService.saveWriter(writer);
        return writerMapper.toWriterResponse(writer);
    }

    @DeleteMapping("/writers/{writerId}")
    public WriterResponse deleteWriter(@PathVariable Long writerId) {
        Writer writer = writerService.validateAndGetWriter(writerId);
        writer.getBooks().forEach(book -> book.removeWriter(writer));
        writerService.deleteWriter(writer);
        return writerMapper.toWriterResponse(writer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/writers/{writerId}/books/{bookId}")
    public WriterResponse addWriterBook(@PathVariable Long writerId, @PathVariable Long bookId) {
        Writer writer = writerService.validateAndGetWriter(writerId);
        Book book = bookService.validateAndGetBook(bookId);
        writer.addBook(book);
        writer = writerService.saveWriter(writer);
        return writerMapper.toWriterResponse(writer);
    }

    @DeleteMapping("/writers/{writerId}/books/{bookId}")
    public WriterResponse removeWriterBook(@PathVariable Long writerId, @PathVariable Long bookId) {
        Writer writer = writerService.validateAndGetWriter(writerId);
        Book book = bookService.validateAndGetBook(bookId);
        writer.removeBook(book);
        writer = writerService.saveWriter(writer);
        return writerMapper.toWriterResponse(writer);
    }

    // -----
    // Books

    @GetMapping("/books/{bookId}")
    public BookResponse getBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBook(bookId);
        return bookMapper.toBookResponse(book);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    public BookResponse createBook(@Valid @RequestBody CreateBookRequest createBookRequest) {
        Book book = bookMapper.toBook(createBookRequest);
        book = bookService.saveBook(book);
        return bookMapper.toBookResponse(book);
    }

    @PutMapping("/books/{bookId}")
    public BookResponse updateBook(@PathVariable Long bookId, @Valid @RequestBody UpdateBookRequest updateBookRequest) {
        Book book = bookService.validateAndGetBook(bookId);
        bookMapper.updateBookFromRequest(updateBookRequest, book);
        book = bookService.saveBook(book);
        return bookMapper.toBookResponse(book);
    }

    @DeleteMapping("/books/{bookId}")
    public BookResponse deleteBook(@PathVariable Long bookId) {
        Book book = bookService.validateAndGetBook(bookId);
        book.getWriters().forEach(writer -> writer.removeBook(book));
        bookService.deleteBook(book);
        return bookMapper.toBookResponse(book);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books/{bookId}/writers/{writerId}")
    public BookResponse addBookWriter(@PathVariable Long bookId, @PathVariable Long writerId) {
        Book book = bookService.validateAndGetBook(bookId);
        Writer writer = writerService.validateAndGetWriter(writerId);
        book.addWriter(writer);
        book = bookService.saveBook(book);
        return bookMapper.toBookResponse(book);
    }

    @DeleteMapping("/books/{bookId}/writers/{writerId}")
    public BookResponse removeBookWriter(@PathVariable Long bookId, @PathVariable Long writerId) {
        Book book = bookService.validateAndGetBook(bookId);
        Writer writer = writerService.validateAndGetWriter(writerId);
        book.removeWriter(writer);
        book = bookService.saveBook(book);
        return bookMapper.toBookResponse(book);
    }
}
