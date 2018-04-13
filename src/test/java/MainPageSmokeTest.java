import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;


public class MainPageSmokeTest {

    @BeforeClass
    public static void BaseURLs() {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
    }

    @Test
    public void PhotosEndpointResponds200() {
        given()
        .when()
        .get("/photos/")
        .then()
        .statusCode(200);

    }

    @Test
    public void OnePhotoJSONResponds200(){
        given()
        .when()
        .get("/photos/1087")
        .then()
        .statusCode(200);
    }
    @Test
    public void AlbumFiltering(){
        given()
        .when()
        .get("https://jsonplaceholder.typicode.com/photos/?albumId=1")
        .then()
        .statusCode(200)
        .body(("albumId[41]"),equalTo(1))
        .body("albumId.size()",equalTo(50));
    }


}
