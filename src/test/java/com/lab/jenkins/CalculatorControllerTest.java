package com.lab.jenkins;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthReportsUp() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }

    @Test
    void addEndpointCalculatesCorrectly() throws Exception {
        mockMvc.perform(get("/api/add").param("a", "40").param("b", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(42));
    }

    @Test
    void addEndpointRejectsNonNumbers() throws Exception {
        mockMvc.perform(get("/api/add").param("a", "hello").param("b", "2"))
                .andExpect(status().isBadRequest());
    }
}
