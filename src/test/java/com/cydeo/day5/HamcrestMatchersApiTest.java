package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {
/*
    Given accept type is json
    And path param id is 10
    When user sends a get request to "api/spartans/{id}"
    Then status code is 200
    And content-type is "application/json"
    And response payload values match the following:
    id is 10,
    name is "Lorenza",
    gender is "Female",
    phone is 3312820936

*/

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

            given().log().all().
                    accept(ContentType.JSON)
                    .and().pathParam("id",15)
            .when()
                    .get("http://18.212.234.31:8000/api/spartans/{id}")
            .then()
                    .statusCode(200)
                    .and().assertThat()
                    .contentType("application/json")
                    .and()
                    .body("id",equalTo(15),
           "name",is("Meta"),
                               "gender",is("Female"),
                               "phone",is(1938695106))
                    .log().all();

    }


    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        //REQUEST START FROM HERE
        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",3)
                .and()
        .when()
                .get("https://api.training.cydeo.com/teacher/{id}")
        .then()
                //RESPONSE START FROM HERE
                //Assertion start from here, VERIFY what ever you need
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Date",notNullValue())
                .and().assertThat()
                .body("teachers[0].firstName",is("Tet"))
                .body("teachers[0].lastName",is("DS"))
                .body("teachers[0].gender",equalTo("Male"));

    }

    @DisplayName("GET request teacher/all and chaining")
    @Test
    public void teachersTest(){

        //verify Ron, Tet, Valter inside the all teachers
        given()
                .accept(ContentType.JSON)
        .when()
                .get("https://api.training.cydeo.com/teacher/all")
        .then()
                .statusCode(200)
                .and()
                .body("teachers.firstName",hasItems("Ron","Tet","Valter"));
}


}
