package com.formulaone.championship.controller;

import com.formulaone.championship.BaseFormulaOneChampionshipApplicationTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class ChampionShipControllerIntegrationTest extends BaseFormulaOneChampionshipApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldTest() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().is(200))
                .andExpect(content().string(containsString("html")))
                .andExpect(content().string(containsString("Lewis Hamilton")))
                .andExpect(content().string(containsString("Mercedes")));

    }

}