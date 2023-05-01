package com.librarymanagement.service;

import com.librarymanagement.db.Book;
import com.librarymanagement.helpers.BookHelper;
import com.librarymanagement.helpers.BookHelperImpl;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository repository;
    @Mock
    private BookHelper bookHelper;

    private Book book1;
    private Book book2;
    private BookObj bookObj;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        book1 = Book.builder()
                .id(1)
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
        book2 = Book.builder()
                .id(2)
                .title("Las crónicas del barrio")
                .author("El señor santo")
                .gender("Conicas")
                .editorial("Global")
                .publishYear("2023")
                .edition("2")
                .copiesQuantity(3L)
                .synopsis("Las crónicas del barrio solo son eso: crónicas. Quizá lo más curioso de ellas es que sucedieron en la Ciudad de México, en plena época del neoliberalismo neón, cuando la “modernidad” quería arribar al país y las fantasías de prosperidad se volvieron pesadillas que transformaron al mismo barrio. Las crónicas publicadas originalmente en formato podcast aparecen en esta segunda edición en formato impreso, acompañadas del enorme talento de grandes ilustradores mexicanos.")
                .SCDD("607.29")
                .isAvailable(true)
                .build();
        bookObj = BookObj.builder()
                .id(0)
                .title("Las crónicas del barrio")
                .author("El señor santo")
                .gender("Conicas")
                .editorial("Global")
                .publishYear("2023")
                .edition("2")
                .copiesQuantity(3L)
                .synopsis("Las crónicas del barrio solo son eso: crónicas. Quizá lo más curioso de ellas es que sucedieron en la Ciudad de México, en plena época del neoliberalismo neón, cuando la “modernidad” quería arribar al país y las fantasías de prosperidad se volvieron pesadillas que transformaron al mismo barrio. Las crónicas publicadas originalmente en formato podcast aparecen en esta segunda edición en formato impreso, acompañadas del enorme talento de grandes ilustradores mexicanos.")
                .SCDD("607.29")
                .isAvailable(true)
                .build();
    }

    @DisplayName("Get all Books")
    @Test
    void getAllBoks() {
        List<Book> savedBooks = Arrays.asList(book1, book2);
        when(repository.findAll()).thenReturn(savedBooks);
        List<BookObj> expectedBooks = bookHelper.bookListToObjList(savedBooks);
        List<BookObj> actualBooks = bookService.getAllBooks();

        assertEquals(expectedBooks, actualBooks);

    }

    // Anotación que indica el nombre de la prueba
    @DisplayName("SVT_TestCase_registrarlibros")
    @Test
    void CreateBook() {
        // Se utiliza Mockito para simular el comportamiento del método saveAndFlush del repositorio
        Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(book1);
        // Se utiliza Mockito para simular el comportamiento del método bookToObj del helper de libro
        Mockito.when(bookHelper.bookToObj(Mockito.any())).thenReturn(bookObj);

        // Se llama al método de servicio createBook con un objeto de libro
        BookObj bookResponse = bookService.createBook(bookObj);

        // Se realizan las aserciones para verificar que el objeto de respuesta coincide con el objeto de entrada
        assertNotNull(bookResponse);
        assertThat(bookResponse.getId() < 0);
        assertEquals(bookResponse.getTitle(), bookObj.getTitle());
        assertEquals(bookResponse.getAuthor(), bookObj.getAuthor());
        assertEquals(bookResponse.getGender(), bookObj.getGender());
        assertEquals(bookResponse.getEditorial(), bookObj.getEditorial());
        assertEquals(bookResponse.getPublishYear(), bookObj.getPublishYear());
        assertEquals(bookResponse.getEdition(), bookObj.getEdition());
        assertEquals(bookResponse.getCopiesQuantity(), bookObj.getCopiesQuantity());
        assertEquals(bookResponse.getSynopsis(), bookObj.getSynopsis());
        assertEquals(bookResponse.getSCDD(), bookObj.getSCDD());
        assertEquals(bookResponse.getIsAvailable(), bookObj.getIsAvailable());
    }

    @DisplayName("EditBook")
    @Test
    void EditBook() {
        // Crear un objeto simulado de libro a actualizar
        BookObj bookToUpdate = BookObj.builder()
                .id(0)
                .title("title")
                .author("Author")
                .gender("Gender")
                .editorial("Gender")
                .publishYear("Gender")
                .edition("Gender")
                .copiesQuantity(0L)
                .synopsis("Synopsis")
                .SCDD("SCDD")
                .isAvailable(false)
                .build();
        ;

        // Crear un objeto simulado de libro existente en la base de datos
        BookObj existingBook = bookObj;
        existingBook.setId(1);

        // Configurar el comportamiento simulado del repositorio para devolver el libro existente

        Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(book1);
        Mockito.when(bookHelper.bookToObj(Mockito.any())).thenReturn(existingBook);
        // Llamar al método updateBook() de la clase de servicio

        BookObj bookObj1 = bookService.editBook(bookToUpdate, existingBook.getId());
        // Verificar que el libro actualizado tenga los valores esperados
        assertNotNull(bookObj1);
        assertNotEquals(bookObj1.getTitle(), bookToUpdate.getTitle());
        assertNotEquals(bookObj1.getAuthor(), bookToUpdate.getAuthor());
        assertNotEquals(bookObj1.getGender(), bookToUpdate.getGender());
        assertNotEquals(bookObj1.getEditorial(), bookToUpdate.getEditorial());
        assertNotEquals(bookObj1.getPublishYear(), bookToUpdate.getPublishYear());
        assertNotEquals(bookObj1.getEdition(), bookToUpdate.getEdition());
        assertNotEquals(bookObj1.getCopiesQuantity(), bookToUpdate.getCopiesQuantity());
        assertNotEquals(bookObj1.getSynopsis(), bookToUpdate.getSynopsis());
        assertNotEquals(bookObj1.getSCDD(), bookToUpdate.getSCDD());
        assertNotEquals(bookObj1.getIsAvailable(), bookToUpdate.getIsAvailable());

    }

    @DisplayName("GetBookByFilter")
    @Test
    void GetBookByFilter() {
    }
}