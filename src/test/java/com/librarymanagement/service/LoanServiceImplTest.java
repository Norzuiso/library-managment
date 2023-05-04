package com.librarymanagement.service;

import com.librarymanagement.db.Book;
import com.librarymanagement.db.Loan;
import com.librarymanagement.db.Reader;
import com.librarymanagement.helpers.LoanHelper;
import com.librarymanagement.obj.BookObj;
import com.librarymanagement.obj.LoanObj;
import com.librarymanagement.obj.ReaderObj;
import com.librarymanagement.repository.LoanRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class LoanServiceImplTest {


    @InjectMocks
    private LoanServiceImpl service;

    @Mock
    private LoanHelper helper;

    @Mock
    private LoanRepository repository;

    @Mock
    private BookService bookService;

    @Mock
    private ReaderService readerService;

    private LoanObj expected;
    private Loan loan;
    private BookObj bookObj;
    private ReaderObj readerObj;

    @BeforeEach
    void setUp() {
        loan = Loan.builder()
                .loanDate(LocalDate.now())
                .expiredDate(LocalDate.now().plusDays(4))
                .book(Book.builder()
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
                        .build())
                .reader(Reader.builder()
                        .name("Leonardo Chavez")
                        .address("C. Colon #23, Mun. Guadalajara")
                        .phone("4017894129")
                        .build())
                .build();
        bookObj = BookObj.builder()
                .id(1)
                .title("Los barrios")
                .author("El señor santo")
                .gender("Conicas")
                .editorial("Global")
                .publishYear("2023")
                .edition("2")
                .copiesQuantity(1L)
                .synopsis("Las crónicas del barrio solo son eso: crónicas. Quizá lo más curioso de ellas es que sucedieron en la Ciudad de México, en plena época del neoliberalismo neón, cuando la “modernidad” quería arribar al país y las fantasías de prosperidad se volvieron pesadillas que transformaron al mismo barrio. Las crónicas publicadas originalmente en formato podcast aparecen en esta segunda edición en formato impreso, acompañadas del enorme talento de grandes ilustradores mexicanos.")
                .SCDD("607.29")
                .isAvailable(true)
                .build();
        readerObj = ReaderObj.builder()
                .id(1)
                .name("Leonardo Chavez")
                .address("C. Colon #23, Mun. Guadalajara")
                .phone("4017894129")
                .build();
        expected = LoanObj.builder()
                .loanDate(LocalDate.now())
                .expiredDate(LocalDate.now().plusDays(4))
                .book(bookObj)
                .reader(readerObj)
                .build();

    }

    @DisplayName("CreateLoan")
    @Test
    void CreateLoan() {
        Mockito.when(helper.objToLoan(expected)).thenReturn(loan);
        Mockito.when(repository.saveAndFlush(loan)).thenReturn(loan);
        Mockito.when(bookService.getBookById(Mockito.any())).thenReturn(bookObj);
        Mockito.when(bookService.editBook(bookObj, bookObj.getId())).thenReturn(bookObj);
        Mockito.when(readerService.getById(readerObj.getId())).thenReturn(readerObj);
        Mockito.when(helper.loanToObj(Mockito.any())).thenReturn(expected);

        LoanObj actual = service.createLoan(expected);

        assertNotNull(actual);
        assertEquals(actual, expected);

    }
}