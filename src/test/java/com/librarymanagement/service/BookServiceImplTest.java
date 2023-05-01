package com.librarymanagement.service;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.BookSearchRequest;
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

import java.util.ArrayList;
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
    private Book book3;
    private Book book4;
    private Book book5;
    private BookObj bookObj;

    private BookObj bookObj1;
    private BookObj bookObj2;
    private BookObj bookObj3;
    private BookObj bookObj4;
    private BookObj bookObj5;

    private List<Book> bookList = new ArrayList<>();
    private List<Book> filterBookList = new ArrayList<>();

    private List<BookObj> bookObjList = new ArrayList<>();

    private List<BookObj> filterBookObjList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        fillBooks();
        fillBookObjs();
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

        bookList.add(book1);
        bookList.add(book2);
        bookList.add(book3);
        bookList.add(book4);
        bookList.add(book5);

        filterBookList.add(book3);
        filterBookList.add(book4);
        filterBookList.add(book5);

        bookObjList.add(bookObj1);
        bookObjList.add(bookObj2);
        bookObjList.add(bookObj3);
        bookObjList.add(bookObj4);
        bookObjList.add(bookObj5);

        filterBookObjList.add(bookObj3);
        filterBookObjList.add(bookObj4);
        filterBookObjList.add(bookObj5);

    }

    private void fillBooks() {
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
        book3 = Book.builder()
                .id(2)
                .title("Los cosos")
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
        book4 = Book.builder()
                .id(2)
                .title("Los sueños de otro mundo")
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
        book5 = Book.builder()
                .id(2)
                .title("Los barrios")
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

    private void fillBookObjs() {
        bookObj1 = BookObj.builder()
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
        bookObj2 = BookObj.builder()
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
        bookObj3 = BookObj.builder()
                .id(2)
                .title("Los cosos")
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
        bookObj4 = BookObj.builder()
                .id(2)
                .title("Los sueños de otro mundo")
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
        bookObj5 = BookObj.builder()
                .id(2)
                .title("Los barrios")
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
        BookObj actual = BookObj.builder()
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
        // Crear un objeto simulado de libro existente en la base de datos
        BookObj existingBook = bookObj;
        existingBook.setId(1);

        // Configurar el comportamiento simulado del repositorio para devolver el libro existente
        Mockito.when(repository.saveAndFlush(Mockito.any())).thenReturn(book1);
        Mockito.when(bookHelper.bookToObj(Mockito.any())).thenReturn(existingBook);

        // Llamar al método editBook() de la clase de servicio
        BookObj expected = bookService.editBook(actual, existingBook.getId());

        // Verificar que el libro actualizado tenga los valores esperados
        assertNotNull(expected);
        assertNotEquals(expected.getTitle(), actual.getTitle());
        assertNotEquals(expected.getAuthor(), actual.getAuthor());
        assertNotEquals(expected.getGender(), actual.getGender());
        assertNotEquals(expected.getEditorial(), actual.getEditorial());
        assertNotEquals(expected.getPublishYear(), actual.getPublishYear());
        assertNotEquals(expected.getEdition(), actual.getEdition());
        assertNotEquals(expected.getCopiesQuantity(), actual.getCopiesQuantity());
        assertNotEquals(expected.getSynopsis(), actual.getSynopsis());
        assertNotEquals(expected.getSCDD(), actual.getSCDD());
        assertNotEquals(expected.getIsAvailable(), actual.getIsAvailable());
    }

    // Anotación que indica que esta función es una prueba y se mostrará en el reporte con el nombre "GetBookByFilter"
    @DisplayName("GetBookByFilter")
    @Test
    void GetBookByFilter() {
        // Crear una instancia de BookSearchRequest
        BookSearchRequest searchRequest = new BookSearchRequest();
        // Definir una cadena de búsqueda para el título
        String searchWordInTitle = "Lo";
        // Establecer la cadena de búsqueda en la instancia de BookSearchRequest
        searchRequest.setTitle(searchWordInTitle);
        // Mockear el comportamiento del repositorio para el método saveAndFlush() para cada uno de los libros
        Mockito.when(repository.saveAndFlush(book1)).thenReturn(book1);
        Mockito.when(repository.saveAndFlush(book2)).thenReturn(book2);
        Mockito.when(repository.saveAndFlush(book3)).thenReturn(book3);
        Mockito.when(repository.saveAndFlush(book4)).thenReturn(book4);
        Mockito.when(repository.saveAndFlush(book5)).thenReturn(book5);

        // Mockear el comportamiento del repositorio para el método FindAllBooksByCriteria() con el objeto searchRequest
        Mockito.when(repository.FindAllBooksByCriteria(searchRequest)).thenReturn(filterBookList);
        // Mockear el comportamiento del objeto bookHelper para el método bookListToObjList() con filterBookList
        Mockito.when(bookHelper.bookListToObjList(filterBookList)).thenReturn(filterBookObjList);

        // Guardar los libros en el repositorio
        book1 = repository.saveAndFlush(book1);
        book2 = repository.saveAndFlush(book2);
        book3 = repository.saveAndFlush(book3);
        book4 = repository.saveAndFlush(book4);
        book5 = repository.saveAndFlush(book5);

        // Llamar al método getBookByFilter() del objeto bookService con searchRequest y guardar el resultado en allBooks
        List<BookObj> allBooks = bookService.getBookByFilter(searchRequest);

        // Asegurarse de que la lista de resultados no esté vacía
        assertTrue(allBooks.size()>0);

        // Verificar que cada libro en la lista de resultados contenga la cadena de búsqueda en el título
        allBooks.forEach(b -> {
            assertTrue(b.getTitle().contains(searchWordInTitle));
        });
    }

}