package com.finland.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.MultiValueMap;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public abstract class AbstractControllerTest extends AbstractController {

    protected static final int INTERNAL_SERVER_ERROR = 500;
    protected static final int BAD_REQUEST = 400;
    protected static final String SUBSCRIBE_CATEGORIES_ENDPOINT = "/subscribe";
    protected static final String SHARE_CATEGORIES_ENDPOINT = "/share";
    @Autowired
    protected MockMvc mockMvc;

    protected MockHttpServletResponse performPostRequest(String url, byte[] request) throws Exception {
        MvcResult result = this.mockMvc.perform(post(url)
                .contentType(APPLICATION_JSON)
                .content(request))
                //.andDo(print())
                .andReturn();

        return result.getResponse();
    }

    protected MockHttpServletResponse performGetRequest(String url, MultiValueMap<String, String> requestParam) throws Exception {
        MvcResult result = this.mockMvc.perform(get(url)
                .contentType(APPLICATION_JSON)
                .headers(addHttpHeaders())
                .params(requestParam))
                //.andDo(print())
                .andReturn();

        return result.getResponse();
    }

    protected byte[] serializeObject(Object request) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsBytes(request);
    }


    protected <T> T deSerializeObject(String responseData, Class<T> valueType) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(responseData, valueType);
    }

    HttpHeaders addHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set(TOKEN_HEADER, "886e596c-6b47-4e28-960f-7caf7452d37e");
        return httpHeaders;
    }

}
