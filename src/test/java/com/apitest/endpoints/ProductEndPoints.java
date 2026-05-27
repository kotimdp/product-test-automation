package com.apitest.endpoints;
import com.apitest.model.Product;
import org.json.JSONException;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import com.apitest.utils.Constants;

public class ProductEndPoints extends BaseEndPoints {


    public Response addProduct(RequestSpecification rSpec, Product product) throws JSONException {

        return sendRequest(rSpec, Constants.RequestType.POST_REQUEST, product);
    }

    public Response getProducts(RequestSpecification rSpec) {
        return sendRequest(rSpec, Constants.RequestType.GET_REQUEST, null);
    }

    public Response getProductsWithMethod(RequestSpecification rSpec, int requestType) {
        return sendRequest(rSpec, requestType, null);
    }

    public Response deleteProduct(String productId, RequestSpecification rSpec) {
        rSpec = getCommonSpec().basePath(productId);

        return sendRequest(rSpec, Constants.RequestType.DELETE_REQUEST, null);
    }

    public Response getProductById(String productId, RequestSpecification rSpec) {
        rSpec = getCommonSpec().basePath(productId);

        return sendRequest(rSpec, Constants.RequestType.GET_REQUEST, null);
    }


}

