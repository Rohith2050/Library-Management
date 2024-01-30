package com.LibraryManagement.Library.Controller;

import com.LibraryManagement.Library.Data.Member;
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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    int id = generateRandomId();;

    private int generateRandomId() {
        return new Random().nextInt(1000);
    }
    @Test
    void createMember() throws Exception {
        Member member=new Member(id,"Ram","987196254","25-dec-2023");
        mockMvc.perform(MockMvcRequestBuilders.post("/Member/add")
                        .content(objectMapper.writeValueAsString(member))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void updateMember() throws Exception {
        Member member=new Member(2,"Maxwell","996196254","22-Jan-2024");

        mockMvc.perform(MockMvcRequestBuilders.put("/Member/2")
                        .content(objectMapper.writeValueAsString(member))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Updated"));
    }

    @Test
    void readMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/Member/get/1"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    void deleteMember() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/Member/882"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("Member deleted successfully"));
    }
}