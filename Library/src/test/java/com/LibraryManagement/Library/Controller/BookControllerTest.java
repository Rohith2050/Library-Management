package com.LibraryManagement.Library.Controller;

import com.LibraryManagement.Library.Data.Book;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    int id = generateRandomId();;

    private int generateRandomId() {
        return new Random().nextInt(1000);
    }
    @Test
    void createBook() throws Exception {

        Book newBook = new Book(id,"Think like a monk","Jay Sheety","978-3-16-148425","17-oct-2019",25);
        id++;
        mockMvc.perform(MockMvcRequestBuilders.post("/Book/add")
                        .content(objectMapper.writeValueAsString(newBook))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBook() throws Exception {
        Book updatedBook = new Book(1,"Atomic Habits","James Clear","978-3-16-148415","16-oct-2018",15);

        mockMvc.perform(MockMvcRequestBuilders.put("/Book/1")
                        .content(objectMapper.writeValueAsString(updatedBook))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Updated Book Title"));
    }

    @Test
    void getBook1() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Book/get/1"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getBookByTitle() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Book/Title/Atomic Habits"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getBookByAuthor() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Book/Author/James Clear"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void getBookByIsbn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Book/Isbn/978-3-16-148415"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Book/3"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Book deleted successfully"));
    }
}