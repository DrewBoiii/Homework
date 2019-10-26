package example.drew.homework.web.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getIndexPage_whenAvailableUrlValueIsSlashHome_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getIndexPage_whenAvailableUrlValueIsSlashHomeArgLangEn_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home?lang=en").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getIndexPage_whenAvailableUrlValueIsSlashHomeArgLangRu_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home?lang=ru").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getIndexPage_whenAvailableUrlValueIsSlash_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getRegistrationPage_whenAvailableUrl_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/registration").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("registration"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getLoginPage_whenAvailableUrl_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("login"))
                .andDo(MockMvcResultHandlers.print());
    }

}
