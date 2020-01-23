package com.finland.controller;

import com.finland.controller.messages.CommonResponse;
import com.finland.controller.messages.ShareCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesResponse;
import com.finland.exceptions.UserNotFoundException;
import com.finland.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class SubscriptionController extends AbstractController {

    private static final String SUBSCRIBE_CATEGORIES_ENDPOINT = "/subscribe";
    private static final String SHARE_CATEGORIES_ENDPOINT = "/share";

    @Autowired
    private SubscriptionService subscriptionService;


    @RequestMapping(method = POST,
            value = SUBSCRIBE_CATEGORIES_ENDPOINT,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public SubscribeCategoriesResponse subscribeCategories(@RequestHeader(value = TOKEN_HEADER, required = false) final String token,
                                                           @RequestBody SubscribeCategoriesRequest subscribeCategoriesRequest) throws UserNotFoundException {
        //Log request
        //Auth token
        authClient.authenticateToken(token);
        //process request
        return subscriptionService.subscribeCategories(subscribeCategoriesRequest);
    }

    @RequestMapping(method = POST,
            value = SHARE_CATEGORIES_ENDPOINT,
            produces = APPLICATION_JSON_VALUE,
            consumes = APPLICATION_JSON_VALUE)
    @ResponseBody
    public CommonResponse shareCategories(@RequestHeader(value = TOKEN_HEADER, required = false) final String token,
                                          @RequestBody ShareCategoriesRequest shareCategoriesRequest) throws UserNotFoundException {
        //Log request
        //Auth token
        authClient.authenticateToken(token);
        //process request
        return subscriptionService.shareCategories(shareCategoriesRequest);
    }

}
