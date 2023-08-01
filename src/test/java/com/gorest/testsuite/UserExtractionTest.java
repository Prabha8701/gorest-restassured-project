package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    @Test
    public void test01() {
        //1. Extract the All Ids
        List<Integer> allIds = response.extract().path("id");
        System.out.println(allIds);
    }

    @Test
    public void test002() {
        //2. Extract the all Names
        List<String> allNames = response.extract().path("name");
        System.out.println(allNames);
    }

    @Test
    public void test003() {
        //3. Extract the name of 5th object
        String name = response.extract().path("[4].name");
        System.out.println(name);
    }

    @Test
    public void test004() {
        //4. Extract the names of all object whose status = inactive
        List<String> inactivesNames = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println(inactivesNames);
    }

    @Test
    public void test005() {
        //5. Extract the gender of all the object whose status = active
        List<String> activesNames = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println(activesNames);
    }

    @Test
    public void test006() {
        //6. Print the names of the object whose gender = female
        List<String> names = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println(names);

    }

    @Test
    public void test007() {
        //7. Get all the emails of the object where status = inactive
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println(email);

    }

    @Test
    public void test008() {
        //8. Get the ids of the object where gender = male
        List<String> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println(ids);

    }

    @Test
    public void test009() {
        //9. Get all the status
        List<String> status = response.extract().path("status");
        System.out.println(status);

    }

    @Test
    public void test010() {
        //10. Get email of the object where name = Karthik Dubashi IV
        List<?> email = response.extract().path("findAll{it.name == 'Anish Reddy Sr.'}.email");
        System.out.println(email);

    }

    @Test
    public void test011() {
        //11. Get gender of id = 5471
        List<String> gender = response.extract().path("findAll{it.id == 4040709}.gender");
        System.out.println(gender);

    }

}

