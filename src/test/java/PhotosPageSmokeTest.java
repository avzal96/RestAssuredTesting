import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PhotosPageSmokeTest {

    @BeforeClass
    public static void BaseURLs() {
        RestAssured.baseURI="https://placehold.it";
    }

    @Test
    public void PhotoUrlIsImageAndResponds200(){
        given()
        .when()
        .get("/600/92c952")
        .then()
        .statusCode(200)
        .contentType("image/png");
    }

    @Test
    public void PhotoThumbnailUrlIsImageAndResponds200(){
        given()
        .when()
        .get("/150/f66b97")
        .then()
        .statusCode(200)
        .contentType("image/png");
    }
}
