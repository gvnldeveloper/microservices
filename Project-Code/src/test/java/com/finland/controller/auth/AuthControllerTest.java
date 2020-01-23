package com.finland.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finland.controller.TestConfiguration;
import com.finland.controller.messages.UserRequest;
import com.finland.controller.messages.UserResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.finland.controller.messages.CommonMessages.FAILED;
import static com.finland.controller.messages.CommonMessages.SUCCESS;
import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class AuthControllerTest {
    private static final String LOGIN_ENDPOINT = "/login";


    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUserRequestHappyFlow() throws Exception {
        //given
        UserRequest request = getUserRequest();
        //when
        MockHttpServletResponse response = performPostRequest(LOGIN_ENDPOINT, serializeObject(request));
        //then
        UserResponse actualResponse = deSerializeObject(response.getContentAsString(), UserResponse.class);
        assertEquals(SUCCESS, actualResponse.getStatus());
    }

    @Test
    public void testUserRequestPasswordNull() throws Exception {
        //given
        UserRequest request = getUserRequestPasswordNull();
        //when
        MockHttpServletResponse response = performPostRequest(LOGIN_ENDPOINT, serializeObject(request));
        //then
        UserResponse actualResponse = deSerializeObject(response.getContentAsString(), UserResponse.class);
        assertEquals(FAILED, actualResponse.getStatus());
    }

    @Test
    public void testUserRequestUserNull() throws Exception {
        //given
        UserRequest request = getUserRequestUserNull();
        //when
        MockHttpServletResponse response = performPostRequest(LOGIN_ENDPOINT, serializeObject(request));
        //then
        UserResponse actualResponse = deSerializeObject(response.getContentAsString(), UserResponse.class);
        assertEquals(FAILED, actualResponse.getStatus());
    }

    @Test
    public void testUserRequestParameterNull() throws Exception {
        //given
        UserRequest request = getUserRequestParameterNull();
        //when
        MockHttpServletResponse response = performPostRequest(LOGIN_ENDPOINT, serializeObject(request));
        //then
        UserResponse actualResponse = deSerializeObject(response.getContentAsString(), UserResponse.class);
        assertEquals(FAILED, actualResponse.getStatus());
    }

    @Test
    public void testUserRequestWrongUserName() throws Exception {
        //given
        UserRequest request = getUserRequestWrongUserName();
        MockHttpServletResponse response = performPostRequest(LOGIN_ENDPOINT, serializeObject(request));
        //then
        UserResponse actualResponse = deSerializeObject(response.getContentAsString(), UserResponse.class);
        assertEquals(FAILED, actualResponse.getStatus());
    }

    @Test
    public void testUserRequestWrongPassword() throws Exception {
        //given
        UserRequest request = getUserRequestWrongPassword();
        //when
        MockHttpServletResponse response = performPostRequest(LOGIN_ENDPOINT, serializeObject(request));
        //then
        UserResponse actualResponse = deSerializeObject(response.getContentAsString(), UserResponse.class);
        assertEquals(FAILED, actualResponse.getStatus());
    }

// User not available

    private UserRequest getUserRequestWrongPassword() {
        UserRequest request = new UserRequest();
        request.setEmail("john@sogeti.com");
        request.setPassword("Wrong@123");
        return request;
    }


    private UserRequest getUserRequestWrongUserName() {
        UserRequest request = new UserRequest();
        request.setEmail("abc@sogeti.com");
        request.setPassword("Password@123");
        return request;
    }

    private UserRequest getUserRequestParameterNull() {
        UserRequest request = new UserRequest();
        request.setEmail(null);
        request.setPassword(null);
        return request;
    }

    private UserRequest getUserRequestUserNull() {
        UserRequest request = new UserRequest();
        request.setEmail(null);
        request.setPassword("Password@123");
        return request;
    }

    private UserRequest getUserRequestPasswordNull() {
        UserRequest request = new UserRequest();
        request.setEmail("john@sogeti.com");
        request.setPassword(null);
        return request;
    }

    private UserRequest getUserRequest() {
        UserRequest request = new UserRequest();
        request.setEmail("john@sogeti.com");
        request.setPassword("Password@123");
        return request;
    }


    MockHttpServletResponse performPostRequest(String url, byte[] request) throws Exception {
        MvcResult result = this.mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(request))
                .andDo(print())
                //.andExpect(status().isOk())
                .andReturn();

        return result.getResponse();
    }

    private byte[] serializeObject(Object request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(request);
    }


    private <T> T deSerializeObject(String responseData, Class<T> valueType) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseData, valueType);
    }
}
