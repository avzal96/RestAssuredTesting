import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

public class ImageTest {
    @BeforeClass
    public static void BaseURLs() {
        RestAssured.baseURI="https://placehold.it";
    }

    @Test
    public void PhotoThumbnailUrlReturnsPNG(){
        given()
        .when()
        .get("/150/f66b97")
        .then()
        .statusCode(200)
        .contentType("image/png");
    }

    @Test
    public void TheSmallestImageUrlReturnsPNG(){
        given()
        .when()
        .get("/1/f66b97")
        .then()
        .statusCode(200)
        .contentType("image/png");
    }

    @Test
    public void TheLargestImageUrlReturnsPNG(){
        given()
        .when()
        .get("/3999/f66b97")
        .then()
        .statusCode(200)
        .contentType("image/png");
    }

    @Test
    public void ZeroPxImageUrlReturnsError(){
        given()
        .when()
        .get("/0/f66b97")
        .then()
        .statusCode(400);
    }

    @Test
    public void MoreThanTheLargestImageUrlReturnsError(){
        given()
        .when()
        .get("/4000/f66b97")
        .then()
        .statusCode(200)
        .body(equalTo("Too big of an image!"));
    }

}
