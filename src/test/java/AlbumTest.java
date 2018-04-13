import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;


import java.util.*;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;
import static org.junit.Assert.assertEquals;

public class AlbumTest {

    @BeforeClass
    public static void BaseURLs() {
        RestAssured.baseURI="https://jsonplaceholder.typicode.com";
    }

    @Test
    public void AlbumHas50Photos(){
        String albumIds= given()
        .when()
        .get("/photos?albumId=2")
        .then()
        .extract().body().asString();

       List <String> albumIdList = from(albumIds).getList("albumId");
       assertEquals(albumIdList.size(),50);
    }

    @Test
    public void EndpointHas5000Photos(){
        String photoIds= given()
                .when()
                .get("/photos")
                .then()
                .extract().body().asString();

        List <String> IdList = from(photoIds).getList("id");
        assertEquals(5000,IdList.size());
    }
    @Test
    public void IdsAreUnique(){
        String photoIds= given()
                .when()
                .get("/photos")
                .then()
                .extract().body().asString();

        List <String> IdList = from(photoIds).getList("id");

        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(IdList);
        assertEquals(5000,hashSet.size());
    }
    @Test
    public void TitlesAreUnique(){
        String photoIds= given()
                .when()
                .get("/photos")
                .then()
                .extract().body().asString();

        List <String> IdList = from(photoIds).getList("title");

        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(IdList);
        assertEquals(5000,hashSet.size());
    }
    @Test
    public void UrlsAreNotUniqueWith4Duplicates(){
        String photoIds= given()
                .when()
                .get("/photos")
                .then()
                .extract().body().asString();

        List <String> UrlList = from(photoIds).getList("url");
        Set<String> hashSet = new HashSet<>();
        hashSet.addAll(UrlList);

        assertEquals(4996,hashSet.size());

        final Set<String> setToReturn = new HashSet<>();
        final Set<String> set1 = new HashSet<>();

        for(String url : UrlList)
        {
            if (!set1.add(url))
            {
                setToReturn.add(url);
            }
        }
        System.out.println(setToReturn);
    }
}
