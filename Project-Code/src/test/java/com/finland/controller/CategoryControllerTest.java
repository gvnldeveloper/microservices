package com.finland.controller;

import com.finland.controller.messages.CategoryResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class CategoryControllerTest extends AbstractControllerTest {

    private static final String GET_CATEGORIES_ENDPOINT = "/categories";

    private static final String REQUEST_PARAM_EMAIL = "email";

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSubscribeCategoriesRequestHappyFlow() throws Exception {
        //give
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.add(REQUEST_PARAM_EMAIL, "john@sogeti.com");

        //when
        MockHttpServletResponse response = performGetRequest(GET_CATEGORIES_ENDPOINT, requestParam);

        //then
        CategoryResponse actualResponse = deSerializeObject(response.getContentAsString(), CategoryResponse.class);
        assertEquals(2, actualResponse.getAvailableCategories().size());
    }


    @Test
    public void testSubscribeCategoriesByUser() throws Exception {
        //give
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.add(REQUEST_PARAM_EMAIL, "john@sogeti.com");

        //when
        MockHttpServletResponse response = performGetRequest(GET_CATEGORIES_ENDPOINT, requestParam);

        //then
        CategoryResponse actualResponse = deSerializeObject(response.getContentAsString(), CategoryResponse.class);
        assertEquals(1, actualResponse.getSubscribedCategories().size());
    }

    @Test
    public void testSubscribeCategoriesInputNull() throws Exception {
        //give
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.add(REQUEST_PARAM_EMAIL, null);

        //when
        MockHttpServletResponse response = performGetRequest(GET_CATEGORIES_ENDPOINT, requestParam);

        //then
        assertEquals(INTERNAL_SERVER_ERROR, response.getStatus());
    }

    @Test
    public void testSubscribeCategoriesWrongEmail() throws Exception {
        //give
        MultiValueMap<String, String> requestParam = new LinkedMultiValueMap<>();
        requestParam.add(REQUEST_PARAM_EMAIL, "abc@sogeti.com");

        //when
        MockHttpServletResponse response = performGetRequest(GET_CATEGORIES_ENDPOINT, requestParam);

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }


}
