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
                .andExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getIndexPage_whenAvailableUrlValueIsSlashHomeArgLangEn_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home?lang=en").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getIndexPage_whenAvailableUrlValueIsSlashHomeArgLangRu_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/home?lang=ru").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
                .andExpect(MockMvcResultMatchers.view().name("index"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getIndexPage_whenAvailableUrlValueIsSlash_statusOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().encoding("UTF-8"))
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

    @Test
    public void getCarsPage_whenUnavailableUrl_statusIsFoundAndRedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/cars").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getEditorPage_whenUnavailableUrl_statusIsFoundAndRedirectToLoginPage() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/editor").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.redirectedUrl("http://localhost/login"))
                .andDo(MockMvcResultHandlers.print());
    }

    // TODO: 26.10.2019 google how to test dynamic urls 
//    @Test
//    public void test() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.get("/cars/{car_id}/details")
//                .accept(MediaType.TEXT_HTML).param("car_id", "1")
//        )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("details"))
//                .andDo(MockMvcResultHandlers.print());
//    }

}
