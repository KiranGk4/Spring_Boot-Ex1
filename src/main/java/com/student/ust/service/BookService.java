package com.student.ust.service;

import com.student.ust.entity.Book;
import com.student.ust.entity.Student;
import com.student.ust.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Book service.
 */
@Service
public class BookService {

    /**
     * The Book repository.
     */
    @Autowired
    BookRepository bookRepository;

    /**
     * Get books list.
     *
     * @return the list
     */
    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    /**
     * Gets book by id.
     *
     * @param id the id
     * @return the book by id
     */
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    /**
     * Add book book.
     *
     * @param book the book
     * @return the book
     */
    public Book addBook(Book book){
        book.setCreateDateTime(LocalDateTime.now());
        book.setModifiedDateTime(book.getCreateDateTime());
       return bookRepository.save(book);
    }

    /**
     * Update book book.
     *
     * @param book the book
     * @return the book
     */
    public Book updateBook(Book book){
        Book updateBook = bookRepository.findById(book.getBookId()).orElseThrow(()->new NoSuchElementException());
        updateBook.setBookName(book.getBookName());
        updateBook.setIsbn(book.getIsbn());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setModifiedDateTime(LocalDateTime.now());
        return bookRepository.save(updateBook);
    }

    /**
     * Delete book.
     *
     * @param id the id
     */
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
