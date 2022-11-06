import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class FilterPosts {

    @Test
    public void filterPosts() {
        given().log().all().queryParam("name", "C-3PO")
                .when().get("https://httpbin.org/get")
                .then().log().all();
    }

    @Test
    public void filterPostsById() {
        given().log().all().queryParam("id", "1", "2")
                .when().get("https://httpbin.org/get")
                .then().log().all();
    }

    @Test
    public void filterPostsAuthorTitle() {
        Map<String,Object> params = new HashMap<>();
        params.put("author", "John");
        params.put("title", "new title");


        given().log().all().queryParams(params)
                .when().get("https://httpbin.org/get")
                .then().log().all();
    }
}
