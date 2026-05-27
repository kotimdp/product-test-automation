package com.apitest.steps;


import com.apitest.endpoints.BaseEndPoints;
import com.apitest.endpoints.ProductEndPoints;
import com.apitest.model.ProductDetails;
import com.apitest.utils.Constants;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import com.apitest.model.Product;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ProductInformationStepDefs {

    List<Response> responseList = new ArrayList<>();
    ProductEndPoints productEndPoints = new ProductEndPoints();

    BaseEndPoints baseEndpoint = new BaseEndPoints();

    private Product request;

    @When("I create a product with the following details:")
    public void i_create_a_product_with_the_following_details(DataTable dt) {
        Map<String, String> productData = dt.asMaps().get(0);
        request = new Product();
        request.setName(productData.get("product_name"));
        ProductDetails data =
                new ProductDetails();

        data.setYear(Integer.parseInt(productData.get("product_year")));
        data.setPrice(Double.parseDouble(productData.get("product_price")));
        data.setCpuModel(productData.get("product_cpu_model"));
        data.setHardDiskSize(productData.get("product_hard_disk_size"));

        request.setData(data);
        RequestSpecification rSpec = baseEndpoint.getCommonSpec();
        responseList.add(productEndPoints.addProduct(rSpec, request));
    }

    @Then("the Product API should return status code {int}")
    public void theProductAPIShouldReturnStatusCode(int statusCode) {
        productEndPoints.verifyResponseStatusCode(responseList.get(0), statusCode);
    }

    @And("the response should match the expected product model")
    public void theResponseShouldMatchTheExpectedProductModel() {

        assertThat(responseList.get(0).jsonPath().getString("name"),
                equalTo(request.getName()));

        assertThat(responseList.get(0).jsonPath().getInt("data.year"),
                equalTo(request.getData().getYear()));

        assertThat(responseList.get(0).jsonPath().getDouble("data.price"),
                equalTo(request.getData().getPrice()));

        assertThat(responseList.get(0).jsonPath().getString("data.cpuModel"),
                equalTo(request.getData().getCpuModel()));

        assertThat(responseList.get(0).jsonPath().getString("data.hardDiskSize"),
                equalTo(request.getData().getHardDiskSize()));
    }

    @When("I delete a product by its ID")
    public void iDeleteAProductByItsID() {
        RequestSpecification rSpec = baseEndpoint.getCommonSpec();
        String id = responseList.get(0).jsonPath().getString("id");
        responseList.add(productEndPoints.deleteProduct(id, rSpec));
    }

    @When("I retrieve the same product by ID")
    public void iRetrieveTheSameProductByID() {
        RequestSpecification rSpec = baseEndpoint.getCommonSpec();
        String id = responseList.get(0).jsonPath().getString("id");
        responseList.add(productEndPoints.getProductById(id, rSpec));
    }

    @When("I request the list of all product items")
    public void i_request_the_list_of_all_product_items() {
        RequestSpecification rSpec = baseEndpoint.getCommonSpec();
        responseList.add(productEndPoints.getProducts(rSpec));
    }

    @When("I request a product item by its ID")
    public void iRequestAProductItemByItsID() {
        RequestSpecification rSpec = baseEndpoint.getCommonSpec();
        responseList.add(productEndPoints.getProductById("7", rSpec));
    }

    @When("I request the list of all product items with POST request")
    public void iRequestTheListOfAllProductItemsWithPOSTRequest() {
        RequestSpecification rSpec = baseEndpoint.getCommonSpec();
        responseList.add(productEndPoints.getProductsWithMethod(rSpec, Constants.RequestType.POST_REQUEST));
    }

    @When("I request the list of all")
    public void iRequestTheListOfAll() {
        System.out.println("login successful");
    }

    @When("I retrieve the product by wrong ID")
    public void iRetrieveTheProductByWrongID() {
        RequestSpecification rSpec = baseEndpoint.getCommonSpec();
        responseList.add(productEndPoints.getProductById("40", rSpec));
    }
}

