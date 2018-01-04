package tests;


import buissnes_object.jsons_objects.UserJSon;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class WebServerTest {

    @BeforeTest
    public void iniTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/users";
    }

    @Test
    public void checkStatusCode() {
        io.restassured.response.Response sc = given().get("/").andReturn();
        int actualStatusCode = sc.getStatusCode();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkHeaderExist() {
        io.restassured.response.Response sc = given().get("/").andReturn();
        String valueOfContentTypeHeader = sc.getHeader("content-type");
        Assert.assertEquals(valueOfContentTypeHeader, "application/json; charset=utf-8");

    }

    @Test
    public void checkQuantityUsers() {
        io.restassured.response.Response sc = given().get("/").andReturn();
        UserJSon[] userJSons = sc.as(UserJSon[].class);
        Assert.assertEquals(userJSons.length, 10);
    }


}
