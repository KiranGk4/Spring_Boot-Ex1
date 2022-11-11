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

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book addBook(Book book){
        book.setCreateDateTime(LocalDateTime.now());
        book.setModifiedDateTime(book.getCreateDateTime());
       return bookRepository.save(book);
    }

    public Book updateBook(Book book){
        Book updateBook = bookRepository.findById(book.getBookId()).orElseThrow(()->new NoSuchElementException());
        updateBook.setBookName(book.getBookName());
        updateBook.setIsbn(book.getIsbn());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setModifiedDateTime(LocalDateTime.now());
        return bookRepository.save(updateBook);
    }

    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }
}
