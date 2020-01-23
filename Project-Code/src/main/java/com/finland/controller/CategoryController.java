package com.finland.controller;

import com.finland.controller.messages.CategoryResponse;
import com.finland.exceptions.UserNotFoundException;
import com.finland.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class CategoryController extends AbstractController {

    private static final String GET_CATEGORIES_ENDPOINT = "/categories";

    @Autowired
    private CategoryService categoryService;


    @RequestMapping(method = GET,
            value = GET_CATEGORIES_ENDPOINT,
            produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public CategoryResponse getCategories(@RequestHeader(value = TOKEN_HEADER, required = false) final String token,
                                          @RequestParam String email) throws UserNotFoundException {
        //Log request
        //Auth token
        authClient.authenticateToken(token);
        //process request
        return categoryService.getCategoryData(email);
    }

}
