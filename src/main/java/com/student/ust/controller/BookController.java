package com.student.ust.controller;

import com.student.ust.entity.Book;
import com.student.ust.entity.Student;
import com.student.ust.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Book controller.
 */
@Slf4j
@RestController
public class BookController {

    /**
     * The Book service.
     */
    @Autowired
    BookService bookService;

    /**
     * Get response entity.
     *
     * @return the response entity
     */
    @GetMapping("/books")
    public ResponseEntity<List<Book>> get()
    {
        try
        {
            List<Book> bookList = bookService.getBooks();
            return new ResponseEntity<List<Book>>(bookList, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<List<Book>>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> get(@PathVariable Integer id)
    {
        try
        {
            Book book = bookService.getBookById(id);
            log.debug("Passed student id: >>>>>"+id);
            return new ResponseEntity<Book>(book, HttpStatus.OK);
        }
        catch (NoSuchElementException e)
        {
            return new ResponseEntity<Book>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Add response entity.
     *
     * @param book the book
     * @return the response entity
     */
    @PostMapping("/book")
    public ResponseEntity<Book> add(@RequestBody Book book)
    {
        //try
        //{
            Book bookAdd = bookService.addBook(book);
            return new ResponseEntity<Book>(bookAdd, HttpStatus.OK);

    }

    /**
     * Update book response entity.
     *
     * @param book the book
     * @return the response entity
     */
    @PutMapping("/bookUpdate")
    public ResponseEntity<Book> updateBook(@RequestBody Book book)
    {
        Book updateBook = bookService.updateBook(book);
        return new ResponseEntity<Book>(updateBook, HttpStatus.OK);
    }

    /**
     * Delete.
     *
     * @param id the id
     */
    @DeleteMapping("/bookDelete/{id}")
    public void delete(@PathVariable Integer id)
    {
        bookService.deleteBook(id);
    }

}
