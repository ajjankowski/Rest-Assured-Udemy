import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class TestRestAssured {

    @Test
    public void getPosts() {
        // given = preconditions
        // when  = action
        // then  = expected result

        // body = logs body
        // all  = logs body and parameters
        when().get("https://swapi.dev/api/").then().log().all();

        // expects to have status code xxx -> if other, logs error
        when().get("https://swapi.dev/api/").then().log().ifValidationFails().statusCode(200);
    }

    @Test
    public void addPosts() {
        String newPost = "{\n" +
                "\"title\": \"First post\", \n" +
                "\"author\": \"Name\", \n" +
                "}";

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("https://swapi.dev/api/")
                .then().log().all();

    }
}