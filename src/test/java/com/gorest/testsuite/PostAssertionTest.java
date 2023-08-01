package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.basePath = "/public/v2";
        response = given()
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    @Test
    public void test001() {
        //1. Verify the if the total record is 25
        response.body("size", equalTo(10));
    }

    @Test
    public void test002() {
        //2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
        response.body("findAll{it.id == 56979}", hasItem(hasEntry("title", "Summa abduco quae blanditiis deorsum.")));

    }

    @Test
    public void test003() {
        //3. Check the single user_id in the Array list (5522)
        response.body("user_id", hasItem(4040702));

    }

    @Test
    public void test004() {
        //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
        response.body("id", hasItems(56973, 56969, 56965));
    }

    @Test
    public void test005() {
        //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcar
        //spectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
        //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
        //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
        //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
        //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
        //adflicto. Assentator umquam pel."”
        response.body("findAll{user_id == '4040719'}", hasItem("Sonitus arca coniecto. Aestivus vae suppono. Vel id voluptatem. Subito bis clam. Administratio denego usitas. Ter cubicularis adulatio. Vomica ascit degenero. Tenax absque ager. Verbera vaco termes. Porro allatus depono. Audacia volo cupiditate. Conservo textor crinis. Nemo acies texo. Coniuratio conicio ulciscor. Tot fugit aegre. Demulceo circumvenio claustrum. Curvus ut comptus. Ut crustulum libero."));
    }


}
