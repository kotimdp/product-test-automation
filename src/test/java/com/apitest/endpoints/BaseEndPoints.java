package com.apitest.endpoints;

import org.json.JSONObject;
import org.junit.Assert;

import com.apitest.env.ApplicationProperties;
import com.apitest.env.Environment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.annotations.Step;
import com.apitest.utils.Constants;


public class BaseEndPoints {
    ApplicationProperties appProps = Environment.INSTANCE.getApplicationProperties();

    /**
     *  common specification for request
     */
    public RequestSpecification getCommonSpec() {
        RequestSpecification rSpec = SerenityRest.given();
        rSpec.contentType(ContentType.JSON).baseUri(appProps.getBaseURL());
        return rSpec;
    }

    /**
     * Convert POJO to JSON
     */
    protected JSONObject createJSONPayload(Object pojo) {
        return new JSONObject(pojo);
    }

    /**
     * Verify that the response code is the same as expected code by comparing the
     * provided expected code and the response code from the response received by
     * sending the request
     */
    public void verifyResponseStatusCode(Response response, int expectedCode) {
        System.out.println("status code:"+response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), expectedCode);
    }

    /**
     * Send request
     *
     * @param request     details for sending the request
     * @param requestType of the request. i.e GET, POST, PUT, DELETE, UPDATE
     * @param pojo        if provided will be added to the body of request as JSON
     *                    payload
     * @return response received from the service by sending the request
     */
    @Step
    public Response sendRequest(RequestSpecification request, int requestType, Object pojo) {
        Response response;

        // Add the Json to the body of the request
        if (pojo != null) {
            String payload = createJSONPayload(pojo).toString();
            request.body(payload);
        }

        // need to add a switch based on request type
        switch (requestType) {
            case Constants.RequestType.POST_REQUEST:
                    response = request.log().all().post();
                break;
            case Constants.RequestType.DELETE_REQUEST:
                    response = request.log().all().delete();
                break;
            case Constants.RequestType.GET_REQUEST:
            default:
                    response = request.log().all().get();
                break;
        }
        return response;
    }

}
