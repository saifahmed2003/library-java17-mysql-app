package com.sparta.mg.libraryproject2;

import com.sparta.mg.libraryproject2.controller.AuthorWebController;
import com.sparta.mg.libraryproject2.model.entities.Author;
import com.sparta.mg.libraryproject2.model.repositories.AuthorRepository;
import com.sparta.mg.libraryproject2.model.repositories.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

@WebMvcTest(AuthorWebController.class)
@AutoConfigureMockMvc
class MVCTests {

    @Autowired
    private MockMvc mockMvc;

    // ✅ mock the dependencies so Spring can create the web controller successfully
    @MockBean
    private AuthorRepository authorRepository;

    @MockBean
    private BookRepository bookRepository;

    @Test
    @DisplayName("Test Authors Page renders successfully")
    void testAuthorsPage() throws Exception {
        // Mock the repository behavior
        Author author = new Author();
        author.setId(1);
        author.setFullName("Manish");
        Mockito.when(authorRepository.findAll()).thenReturn(List.of(author));

        // Perform and verify
        mockMvc.perform(MockMvcRequestBuilders.get("/web/authors"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
