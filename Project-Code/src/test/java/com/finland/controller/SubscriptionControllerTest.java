package com.finland.controller;

import com.finland.controller.messages.ShareCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesRequest;
import com.finland.controller.messages.SubscribeCategoriesResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.finland.controller.messages.CommonMessages.DUPLICATE;
import static com.finland.controller.messages.CommonMessages.SUCCESS;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = TestConfiguration.class)
public class SubscriptionControllerTest extends AbstractControllerTest {


    @Test
    @Order(1)
    public void testSubscribeCategoriesRequestHappyFlow() throws Exception {
        //give
        SubscribeCategoriesRequest request = getSubscribeCategoriesRequest();

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        SubscribeCategoriesResponse actualResponse = deSerializeObject(response.getContentAsString(), SubscribeCategoriesResponse.class);
        assertEquals(SUCCESS, actualResponse.getStatus());
    }


    @Test
    @Order(2)
    public void testSubscribeCategoriesDuplicateRequest() throws Exception {
        //give
        SubscribeCategoriesRequest request = getSubscribeCategoriesRequest();

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        SubscribeCategoriesResponse actualResponse = deSerializeObject(response.getContentAsString(), SubscribeCategoriesResponse.class);
        assertEquals(DUPLICATE, actualResponse.getStatus());
    }

    @Test
    public void testSubscribeCategoriesRequestNullCategory() throws Exception {
        //give
        SubscribeCategoriesRequest request = getSubscribeCategoriesRequestNullCategory();

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testSubscribeCategoriesRequestNullEmail() throws Exception {
        //give
        SubscribeCategoriesRequest request = getSubscribeCategoriesRequestNullEmail();

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testSubscribeCategoriesRequestNullAttributes() throws Exception {
        //give
        SubscribeCategoriesRequest request = getSubscribeCategoriesRequestNullAttributes();

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testSubscribeCategoriesRequestWrongEmail() throws Exception {
        //give
        SubscribeCategoriesRequest request = getSubscribeCategoriesRequestWrongEmail();

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }


    @Test
    public void testSubscribeCategoriesRequestWrongCategory() throws Exception {
        //give
        SubscribeCategoriesRequest request = getSubscribeCategoriesRequestWrongCategory();

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }


    @Test
    public void testSubscribeCategoriesRequestNoParameter() throws Exception {
        //give
        SubscribeCategoriesRequest request = null;

        //when
        MockHttpServletResponse response = performPostRequest(SUBSCRIBE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }

    @Test
    @Order
    public void testShareCategoriesRequestHappyFlow() throws Exception {
        //give
        ShareCategoriesRequest request = testShareCategoriesRequest();

        //when
        MockHttpServletResponse response = performPostRequest(SHARE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }


    @Test
    public void testShareCategoriesRequestEmailNull() throws Exception {
        //give
        ShareCategoriesRequest request = testShareRequestEmailNull();

        //when
        MockHttpServletResponse response = performPostRequest(SHARE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testShareCategoriesRequestCustomerNull() throws Exception {
        //give
        ShareCategoriesRequest request = testShareRequestCustomerNull();

        //when
        MockHttpServletResponse response = performPostRequest(SHARE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testShareCategoriesRequestCategoryNull() throws Exception {
        //give
        ShareCategoriesRequest request = testShareRequestCategoryNull();

        //when
        MockHttpServletResponse response = performPostRequest(SHARE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }


    @Test
    public void testShareCategoriesRequestParameterNull() throws Exception {
        //give
        ShareCategoriesRequest request = testShareRequestParameterNull();

        //when
        MockHttpServletResponse response = performPostRequest(SHARE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }


    @Test
    public void testShareCategoriesRequestNull() throws Exception {
        //give
        //when
        MockHttpServletResponse response = performPostRequest(SHARE_CATEGORIES_ENDPOINT, serializeObject(null));

        //then
        assertEquals(BAD_REQUEST, response.getStatus());
    }

    @Test
    public void testShareCategoriesRequestUserNotSubscribed() throws Exception {
        //give
        ShareCategoriesRequest request = testShareRequestUserNotSubscribed();

        //when
        MockHttpServletResponse response = performPostRequest(SHARE_CATEGORIES_ENDPOINT, serializeObject(request));

        //then
        assertEquals(200, response.getStatus());
    }

    // User not available
    private SubscribeCategoriesRequest getSubscribeCategoriesRequestNoParameter() {
        SubscribeCategoriesRequest request = new SubscribeCategoriesRequest();
        request.setEmail("john@sogeti.com");
        request.setAvailableCategory("Dutch Test Series");
        return request;
    }


    private SubscribeCategoriesRequest getSubscribeCategoriesRequestWrongCategory() {
        SubscribeCategoriesRequest request = new SubscribeCategoriesRequest();
        request.setEmail("john@sogeti.com");
        request.setAvailableCategory("Dutch Test Series");
        return request;
    }

    private SubscribeCategoriesRequest getSubscribeCategoriesRequestWrongEmail() {
        SubscribeCategoriesRequest request = new SubscribeCategoriesRequest();
        request.setEmail("abc@sogeti.com");
        request.setAvailableCategory("Dutch Series");
        return request;
    }

    private SubscribeCategoriesRequest getSubscribeCategoriesRequestNullAttributes() {
        SubscribeCategoriesRequest request = new SubscribeCategoriesRequest();
        request.setEmail(null);
        request.setAvailableCategory(null);
        return request;
    }

    private SubscribeCategoriesRequest getSubscribeCategoriesRequestNullEmail() {
        SubscribeCategoriesRequest request = new SubscribeCategoriesRequest();
        request.setEmail(null);
        request.setAvailableCategory("Dutch Series");
        return request;
    }

    private SubscribeCategoriesRequest getSubscribeCategoriesRequestNullCategory() {
        SubscribeCategoriesRequest request = new SubscribeCategoriesRequest();
        request.setEmail("john@sogeti.com");
        request.setAvailableCategory(null);
        return request;
    }

    private SubscribeCategoriesRequest getSubscribeCategoriesRequest() {
        SubscribeCategoriesRequest request = new SubscribeCategoriesRequest();
        request.setEmail("john@sogeti.com");
        request.setAvailableCategory("Dutch Series");
        return request;
    }


    // User not available
    private ShareCategoriesRequest testShareRequestParameterNull() {
        ShareCategoriesRequest request = new ShareCategoriesRequest();
        request.setEmail(null);
        request.setCustomer(null);
        request.setSubscribedCategory(null);
        return request;
    }

    private ShareCategoriesRequest testShareRequestCategoryNull() {
        ShareCategoriesRequest request = new ShareCategoriesRequest();
        request.setEmail("john@sogeti.com");
        request.setCustomer("bill@sogeti.com");
        request.setSubscribedCategory(null);
        return request;
    }

    private ShareCategoriesRequest testShareRequestCustomerNull() {
        ShareCategoriesRequest request = new ShareCategoriesRequest();
        request.setEmail("john@sogeti.com");
        request.setCustomer(null);
        request.setSubscribedCategory("International Films");
        return request;
    }

    private ShareCategoriesRequest testShareRequestEmailNull() {
        ShareCategoriesRequest request = new ShareCategoriesRequest();
        request.setEmail(null);
        request.setCustomer("bill@sogeti.com");
        request.setSubscribedCategory("International Films");
        return request;
    }


    private ShareCategoriesRequest testShareCategoriesRequest() {
        ShareCategoriesRequest request = new ShareCategoriesRequest();
        request.setEmail("john1@sogeti.com");
        request.setCustomer("bill@sogeti.com");
        request.setSubscribedCategory("International Films");
        return request;
    }

    private ShareCategoriesRequest testShareRequestUserNotSubscribed() {
        ShareCategoriesRequest request = new ShareCategoriesRequest();
        request.setEmail("john@sogeti.com");
        request.setCustomer("bill@sogeti.com");
        request.setSubscribedCategory("International Films");
        return request;
    }

}
