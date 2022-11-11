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

@Slf4j
@RestController
public class BookController {

    @Autowired
    BookService bookService;

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

    @PostMapping("/book")
    public ResponseEntity<Book> add(@RequestBody Book book)
    {
        //try
        //{
            Book bookAdd = bookService.addBook(book);
            return new ResponseEntity<Book>(bookAdd, HttpStatus.OK);

    }

    @PutMapping("/bookUpdate")
    public ResponseEntity<Book> updateBook(@RequestBody Book book)
    {
        Book updateBook = bookService.updateBook(book);
        return new ResponseEntity<Book>(updateBook, HttpStatus.OK);
    }

    @DeleteMapping("/bookDelete/{id}")
    public void delete(@PathVariable Integer id)
    {
        bookService.deleteBook(id);
    }

}
