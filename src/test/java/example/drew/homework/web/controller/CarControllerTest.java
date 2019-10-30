package example.drew.homework.web.controller;

import example.drew.homework.service.CarService;
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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;
import java.util.Objects;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CarControllerTest {

//    @Mock
//    private UserService userService;
//
//    @Mock
//    private CarService carService;
//
//    @InjectMocks
//    private CarController carController;

    @Autowired
    private Filter springSecurityFilterChain;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    // TODO: 28.10.2019 google test securing urls

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

//        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        final MockHttpServletRequestBuilder defaultRequestBuilder = MockMvcRequestBuilders.get("/submit");
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .defaultRequest(defaultRequestBuilder)
                .alwaysDo(result -> setSessionBackOnRequestBuilder(defaultRequestBuilder, result.getRequest()))
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain))
                .build();
    }

    private void setSessionBackOnRequestBuilder(final MockHttpServletRequestBuilder requestBuilder,
                                                final MockHttpServletRequest request) {
        requestBuilder.session((MockHttpSession) Objects.requireNonNull(request.getSession()));
    }

//    @Test
//    public void getCarController_whenCarControllerInjected_thenNotNull() {
//        Assert.assertNotNull(carController);
//    }

    @Test
    @WithMockUser(username = "Drew", password = "ppasswworrdd", roles = {"ROLE_USER"})
    public void getResponseEntity_whenSaveCorrectDto_thenStatusIsOk() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/submit")
//                .accept(MediaType.APPLICATION_JSON)
//                .param("brand", "Skoda")
//                .param("model", "Octavia")
//                .param("build", "12.01.2018")
//                .param("kilometers", "53200")
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.handler().methodName("saveCar"))
                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    public void getResponseEntity_whenSaveIncorrectDtoBrandField_thenStatusIsBadRequest() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.post("/submit")
//                .accept(MediaType.APPLICATION_JSON)
//                .param("brand", "S")
//                .param("model", "Octavia")
//                .param("build", "12.01.2018")
//                .param("kilometers", "53200")
//        )
//                .andExpect(MockMvcResultMatchers.status().isBadRequest())
//                .andExpect(MockMvcResultMatchers.handler().methodName("saveCar"))
//                .andDo(MockMvcResultHandlers.print());
//    }

}
