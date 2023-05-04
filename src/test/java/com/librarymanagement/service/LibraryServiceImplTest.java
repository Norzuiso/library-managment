package com.librarymanagement.service;

import com.librarymanagement.db.Library;
import com.librarymanagement.helpers.LibraryHelper;
import com.librarymanagement.repository.LibraryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

@ExtendWith(MockitoExtension.class)
class LibraryServiceImplTest {

    @InjectMocks
    private LibraryServiceImpl libraryService;

    @Mock
    private LibraryHelper helper;

    @Mock
    private LibraryRepository repository;

    private Library library;

    @BeforeEach
    void setUp() {
        library = Library
                .builder()
                .name("n")
                .address("a")
                .phone("0")
                .password("TestPassword")
                .build();
        Mockito.when(repository.saveAndFlush(library)).thenReturn(library);
        Library library1 = repository.saveAndFlush(library);
    }
    // Se define el nombre de la prueba que se mostrará en la consola de ejecución
    @DisplayName("Login")
    @Test
    void Login() {
        // Se utiliza Mockito para simular el comportamiento del método "existsLibraryByPassword" de la clase "repository"
        Mockito.when(repository.existsLibraryByPassword(Mockito.anyString()))
                .thenAnswer(invocation -> {
                    String password = invocation.getArgument(0); // Se obtiene el argumento que se ha pasado al método
                    if (password.equals("TestPassword")) { // Se verifica si la contraseña es igual a "TestPassword"
                        return true; // Si es así, se devuelve true
                    } else {
                        return false; // Si no es así, se devuelve false
                    }
                });

        // Se llama al método "login" de la clase "libraryService" con la contraseña "TestPassword" y se guarda el resultado en la variable "testPassword"
        Boolean testPassword = libraryService.login("TestPassword");
        Boolean wrongPassword = libraryService.login("Library123");
        // Se verifica que el resultado de la llamada al método sea true, lo que indica que la contraseña era correcta
        assertTrue("Password was the correct one!",testPassword);

        // Se verifica que el resultado de la llamada al método sea false, lo que indica que la contraseña era incorrecta
        assertFalse("Password was incorrect! Try again", wrongPassword);
    }
}