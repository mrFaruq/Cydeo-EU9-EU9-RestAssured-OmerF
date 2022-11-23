package com.cydeo.day4;



import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDS_Api_Test_With_Path extends HRTestBase {

    @DisplayName("GET request to countries with Path Method")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).
                queryParams("q", "{\"region_id\":2}").

                when().
                get("/countries");

        assertEquals(200, response.statusCode());

        //print limit result

        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));


        //print first countryId
        String firstCountryId = response.path("items[0].country_id");
        System.out.println("firstCountryId = " + firstCountryId);

        //print second country name
        String secondCountry_name = response.path("items[1].country_name");
        System.out.println("secondCountry_name = " + secondCountry_name);

        //print http://18.212.234.31:1000/ords/hr/countries/CA
        String thirdHref= response.path("items[2].links[0].href");
        System.out.println("thirdHref = " + thirdHref);

        //get me all country names
        List<String> countryNames = response.path("items.country_name");
        System.out.println("countryNames = " + countryNames);

        //assert that all regions ids are equal to 2

        List<Integer> allRegionsIDs = response.path("items.region_id");
        for (Integer regionsID : allRegionsIDs) {
            System.out.println("regionsID = " + regionsID);
            assertEquals(2,regionsID);
        }

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

        //make sure we have only IT program
        List<String > allJobIDs = response.path("items.job_id");

        for (String jobID : allJobIDs) {
            System.out.println("jobID = " + jobID);
            assertEquals("IT_PROG", jobID);
        }

    }


}
