package com.example.MySpringApi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RestController
public class LibraryController {
    private List<Book> library = new ArrayList<>();
    private final AtomicInteger bookCounter = new AtomicInteger(1);

    @PostMapping("/library/books")
    public ResponseEntity<Void> addBook(@RequestBody Book book) {
        int id = bookCounter.getAndIncrement();
        book.setId(String.valueOf(id));
        library.add(book);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/library/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> sortedBooks = library.stream()
                .sorted(Comparator.comparing(Book::getAuthor).thenComparing(Book::getTitle))
                .collect(Collectors.toList());
        return ResponseEntity.ok(sortedBooks);
    }

    @DeleteMapping("/library/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        Book removedBook = null;
        for (Book book : library) {
            if (book.getId().equals(id)) {
                removedBook = book;
                break;
            }
        }
        if (removedBook != null) {
            library.remove(removedBook);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/library/books/{title}")
    public ResponseEntity<Book> getBookByTitle(@PathVariable String title) {
        for (Book book : library) {
            if (book.getTitle().equals(title)) {
                return ResponseEntity.ok(book);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/library/books/{id}")
    public ResponseEntity<Void> updateBookAuthor(@PathVariable int id, @RequestBody Book newBook) {
        boolean updated = false;
        for (Book book : library) {
            if (book.getId().equals(String.valueOf(id))) {
                book.setAuthor(newBook.getAuthor());
                updated = true;
                break;
            }
        }
        if (updated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}