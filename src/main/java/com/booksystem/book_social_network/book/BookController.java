package com.booksystem.book_social_network.book;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booksystem.book_social_network.book.bookservices.BorrowBookService;
import com.booksystem.book_social_network.book.bookservices.FindAllBooksByOwnerService;
import com.booksystem.book_social_network.book.bookservices.FindAllDisplayableBookService;
import com.booksystem.book_social_network.book.bookservices.FindBookByIdService;
import com.booksystem.book_social_network.book.bookservices.GetAllBorrowedBookService;
import com.booksystem.book_social_network.book.bookservices.SaveBookService;
import com.booksystem.book_social_network.book.dto.BookRequest;
import com.booksystem.book_social_network.book.dto.BookResponse;
import com.booksystem.book_social_network.book.dto.BorrowedBookResponse;
import com.booksystem.book_social_network.common.PageResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private final SaveBookService SavebookService;
    private final FindBookByIdService findBookByIdService;
    private final FindAllDisplayableBookService findAllDisplayableBookService;
    private final FindAllBooksByOwnerService findAllBooksByOwnerService;
    private final GetAllBorrowedBookService getAllBorrowedBookService;
    private final BorrowBookService borrowBookService;

    @PostMapping("/save")
    public ResponseEntity<Long> saveBook(@Valid @RequestBody BookRequest request, Authentication connectUser) {

        long savedUserId = SavebookService.execute(request, connectUser);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedUserId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(findBookByIdService.execute(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<BookResponse>> getAllBooks(
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "0") int page,
            Authentication authentication) {
        return ResponseEntity.ok(findAllDisplayableBookService.execute(page, size, authentication));
    }

    @GetMapping("/owner")
    public ResponseEntity<PageResponse<BookResponse>> getAllBooksByOwner(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int size,
            Authentication authentication) {
        PageResponse<BookResponse> books = findAllBooksByOwnerService.execute(page, size, authentication);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/borrowed")
    public ResponseEntity<PageResponse<BorrowedBookResponse>> getAllBorrowedBooks(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size, Authentication authentication) {
        return ResponseEntity.ok(getAllBorrowedBookService.execute(page, size, authentication));
    }

    @PostMapping("/borrow/{id}")
    public ResponseEntity<Long> borrowBook(@PathVariable long id, Authentication authentication) {
        return ResponseEntity.ok(borrowBookService.execute(id, authentication));
    }

}
