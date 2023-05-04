package com.librarymanagement.repository;

import com.librarymanagement.db.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    private Book book;

    @BeforeEach
    void feedBook() {
        book = Book.builder()
                .id(0)
                .title("Las voces que fueron")
                .author("Juan Kala")
                .gender("Terror")
                .editorial("Global")
                .publishYear("2001")
                .edition("2023")
                .copiesQuantity(5L)
                .synopsis("Despues de una vida llena de actos violentos cometidos para obtener una vida digna, Pedro decide que es momento de terminar con todo, hasta que se da cuenta que las voces no eran por su trabajo. En esta historia donde el terror psicologico se indaga desde el punto de vista de una persona que lo unico que quiere es descansar, encontramos lo mejor de esta obra, paz.")
                .SCDD("931.53")
                .isAvailable(true)
                .build();
    }

    @DisplayName("SVT_TestCase_registrarlibros")
    @Test
    void createBook() {
        Book expected = repository.save(book);

        assertNotNull(expected);
        assertTrue(expected.getId() > 0);
        assertEquals(expected.getTitle(), book.getTitle());
        assertEquals(expected.getAuthor(), book.getAuthor());
        assertEquals(expected.getGender(), book.getGender());
        assertEquals(expected.getEditorial(), book.getEditorial());
        assertEquals(expected.getPublishYear(), book.getPublishYear());
        assertEquals(expected.getEdition(), book.getEdition());
        assertEquals(expected.getCopiesQuantity(), book.getCopiesQuantity());
        assertEquals(expected.getSynopsis(), book.getSynopsis());
        assertEquals(expected.getSCDD(), book.getSCDD());
        assertEquals(expected.getIsAvailable(), book.getIsAvailable());
    }

    @DisplayName("SVT_TestCase_ editarlibros")
    @Test
    void updateBook() {
        Book expected = repository.save(book);
        expected.setTitle("Los rostros sin nombres");

        assertNotNull(expected);
        assertTrue(expected.getId() > 0);
        assertEquals(expected.getTitle(), book.getTitle());
        assertEquals(expected.getAuthor(), book.getAuthor());
        assertEquals(expected.getGender(), book.getGender());
        assertEquals(expected.getEditorial(), book.getEditorial());
        assertEquals(expected.getPublishYear(), book.getPublishYear());
        assertEquals(expected.getEdition(), book.getEdition());
        assertEquals(expected.getCopiesQuantity(), book.getCopiesQuantity());
        assertEquals(expected.getSynopsis(), book.getSynopsis());
        assertEquals(expected.getSCDD(), book.getSCDD());
        assertEquals(expected.getIsAvailable(), book.getIsAvailable());
    }
}