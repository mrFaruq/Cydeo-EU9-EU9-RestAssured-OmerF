package com.cydeo.Day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class SimpleGetRequest {

    String url = "http://18.212.234.31:8000/api/spartans";

    @Test
    public void test1(){

        //send a request and save response inside the response object
        Response response = RestAssured.get(url);

        System.out.println(response.statusCode());

        response.prettyPrint();

    }

}


