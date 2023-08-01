package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class UserAssertionTest {
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
    public void test1() {
        //1. Verify the if the total record is 20
        response.body("size", equalTo(20));
    }
    @Test
    public void test2() {
        //2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”
        response.body(".findAll{it.id == '4040717'}", hasItem(hasEntry("name","Shreyashi Kakkar Sr.")));
    }
    @Test
    public void test3(){
        //3. Check the single ‘Name’ in the Array list (Subhashini Talwar)
        response.body("name",hasItem("Mr. Preity Patel"));
    }
    @Test
    public void test4(){
        //4. Check the multiple ‘Names’ in the ArrayList (Mrs. Menaka Bharadwaj, Msgr. Bodhan
        response.body("name",hasItems("Mr. Preity Patel","Ms. Vaishvi Shukla","Malati Gandhi"));
    }
    @Test
    public void test5(){
        //5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
        response.body("find{it.id == 4040717}.email", equalTo("kakkar_shreyashi_sr@ratke.test"));
    }
    @Test
    public void test6(){
        //6. Verify the status is “Active” of user name is “Shanti Bhat V”
        response.body("find{it.name =='Ms. Vaishvi Shukla'}.status", equalTo("inactive"));
    }
    @Test
    public void test7(){
        //7. Verify the Gender = male of user name is “Niro Prajapat
        response.body("find{it.name == 'Malati Gandhi'}.gender", equalTo("male"));
    }
}
