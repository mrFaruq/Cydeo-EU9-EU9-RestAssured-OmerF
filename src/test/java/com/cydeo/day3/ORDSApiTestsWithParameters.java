package com.cydeo.day3;


import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don't need to type each http method.
        baseURI = "http://18.212.234.31:1000/ords/hr";
    }

    @DisplayName("GET request to \"/countries with Query Param")
    @Test
    public void test1 (){

        /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

        Response response = given().accept(ContentType.JSON).
                and().queryParams("q", "{\"region_id\":2}").
                log().all().
                when().
                get("/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();


    }


    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){

        // Send a GET request to employees and get only employees who works as a IT_PROG

        Response response = given().accept(ContentType.JSON).
                and().queryParams("q", "{\"job_id\": \"IT_PROG\"}").
                log().all().
                when().
                get("/employees");

        assertEquals(200,response.statusCode());
        assertEquals("application/json", response.header("Content-Type"));
        assertTrue(response.body().asString().contains("IT_PROG"));

        response.prettyPrint();

    }


}
