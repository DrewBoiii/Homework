package example.drew.homework.web.controller;

import example.drew.homework.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getResponseEntity_whenCorrectDto_thenStatusIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .accept(MediaType.APPLICATION_JSON)
                .param("username", "username")
                .param("email", "some@mail.ru")
                .param("password", "password")
                .param("confirmPassword", "password")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("saveUser"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getResponseEntity_whenIncorrectDtoUsernameField_thenStatusIsBadRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/registration")
                .accept(MediaType.APPLICATION_JSON)
                .param("username", "")
                .param("email", "some@mail.ru")
                .param("password", "password")
                .param("confirmPassword", "password")
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.handler().methodName("saveUser"))
                .andDo(MockMvcResultHandlers.print());
    }

}
