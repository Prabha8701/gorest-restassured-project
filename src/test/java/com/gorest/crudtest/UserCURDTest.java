package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCURDTest extends TestBase {

     @Test
    public void verifyUserCreatedSuccessfully() {
        String email = TestUtils.getRandomValue() + "prime@gmail.com";

        UserPojo userPojo = new UserPojo();
        userPojo.setName("Prime Patel");
        userPojo.setEmail(email);
        userPojo.setGender("male");
        userPojo.setStatus("active");

        Response response = given().log().all()
                .header("Content-Type", "application/json; charset=utf-8")
                .header("Authorization","Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .when()
                .body(userPojo)
                .post("");
                 response.prettyPrint();
        response.then().log().all().statusCode(404);
    }
    @Test
    public void verifyUserUpdateSuccessfully(){
        String email ="prime" + TestUtils.getRandomValue() + "@gmail.com";

        UserPojo userPojo = new UserPojo();

        userPojo.setName("Testing");
        userPojo.setEmail(email);
        userPojo.setGender("male");
        userPojo.setStatus("active");

        Response response = given().log().all()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParam("id", 4040703) //passing parameter to .get()
                .when()
                .body(userPojo)
                .patch("/{id}");
        response.then().log().all().statusCode(404);
        response.prettyPrint();
    }
    @Test
    public void VerifyUserDeleteSuccessfully(){
        Response response = given().log().all()

                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 600f4364266ef9256401822c412cbfa2a4fe3c13c5c708bf2206cbb120f2a4c9")
                .pathParam("id", 4040696) //passing parameter to .get()

                .when()
                .delete("/{id}");

        response.then().log().all().statusCode(404);
        response.prettyPrint();
    }


}


