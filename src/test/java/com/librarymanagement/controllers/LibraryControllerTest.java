package com.librarymanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.librarymanagement.obj.ReaderObj;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class LibraryControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @DisplayName("SVT_TestCase_log")
    @Test
    void SVT_TestCase_log() {
        try {
            // Se llama el login
            mockMvc.perform(MockMvcRequestBuilders.get("/library/password")).andExpect(status().is(200));

            // Se llama el crear book
            mockMvc.perform(MockMvcRequestBuilders.post("/book")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "  \"id\": 0,\n" +
                            "  \"title\": \"string\",\n" +
                            "  \"author\": \"string\",\n" +
                            "  \"gender\": \"string\",\n" +
                            "  \"editorial\": \"string\",\n" +
                            "  \"publishYear\": \"string\",\n" +
                            "  \"edition\": \"string\",\n" +
                            "  \"copiesQuantity\": 0,\n" +
                            "  \"synopsis\": \"string\",\n" +
                            "  \"isAvailable\": true,\n" +
                            "  \"scdd\": \"string\"\n" +
                            "}")).andExpect(status().is(200));

            // se manda a llamar a getBooks
            mockMvc.perform(MockMvcRequestBuilders.get("/book")).andExpect(status().is(200));


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("SVT_TestCase_lectores")
    @Test
    void SVT_TestCase_lectores() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            MvcResult createReaderResult = mockMvc.perform(MockMvcRequestBuilders.post("/reader")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{\n" +
                            "  \"id\": 0,\n" +
                            "  \"name\": \"string\",\n" +
                            "  \"address\": \"string\",\n" +
                            "  \"phone\": \"string\"\n" +
                            "}")).andExpect(status().is(200)).andReturn();
            String contentAsString = createReaderResult.getResponse().getContentAsString();
            ReaderObj reader = objectMapper.readValue(contentAsString, ReaderObj.class);

            String readerJson = objectMapper.writeValueAsString(reader);
           mockMvc.perform(MockMvcRequestBuilders.put("/reader/" + reader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(readerJson)).andExpect(status().is(200)).andReturn();

            mockMvc.perform(MockMvcRequestBuilders.delete("/reader/" + reader.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(readerJson)).andExpect(status().is(200));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("SVT_TestCase_prestamo")
    @Test
    void SVT_TestCase_prestamo() {

    }
}