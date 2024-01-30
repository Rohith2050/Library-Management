package com.LibraryManagement.Library.Controller;

import com.LibraryManagement.Library.Data.Borrow;
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
class BorrowControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    int id = generateRandomId();;

    private int generateRandomId() {
        return new Random().nextInt(1000);
    }

    @Test
    void createBorrow() throws Exception {
        Borrow borrow=new Borrow(id,201,114,"27-dec-2023","29-jan-2024");

        mockMvc.perform(MockMvcRequestBuilders.post("/Borrow/add")
                        .content(objectMapper.writeValueAsString(borrow))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateBorrow() throws Exception {
        Borrow updatedBorrow=new Borrow(1,201,114,"27-dec-2023","29-jan-2024");

        mockMvc.perform(MockMvcRequestBuilders.put("/Borrow/10")
                        .content(objectMapper.writeValueAsString(updatedBorrow))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Updated"));
    }

    @Test
    void getBorrow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Borrow/get/1"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deleteBorrow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Borrow/12"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Deleted"));
    }

    @Test
    void borrowBook() throws Exception {

        Borrow borrow=new Borrow(id,20,1,"29-dec-2023","29-jan-2024");

        mockMvc.perform(MockMvcRequestBuilders.post("/Borrow/BookBorrow")
                        .param("book_id", String.valueOf(1))
                        .param("borrowAmt", String.valueOf(1))
                        .content(objectMapper.writeValueAsString(borrow))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Borrowed_Book_Updated"));
    }

    @Test
    void returnBook() throws Exception {
        Borrow borrow=new Borrow(1,20,1,"29-dec-2023","29-jan-2024");

        mockMvc.perform(MockMvcRequestBuilders.post("/Borrow/BookReturn")
                        .param("book_id", String.valueOf(1))
                        .param("member_id", String.valueOf(1))
                        .content(objectMapper.writeValueAsString(borrow))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Returned_Book_Updated"));
    }
}