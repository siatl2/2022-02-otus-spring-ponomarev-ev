package ru.otus.homework18.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(IndexController.class)
class IndexControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    void getIndexPage() throws Exception {
        mvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("list"));

    }
}