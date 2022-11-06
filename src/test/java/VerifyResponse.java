import model.Post;
import model.PostSW;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VerifyResponse {

    @Test
    public void getPostContains() {
        given().log().all().when().get("https://swapi.dev/api/people/2")
                .then().log().all().body(Matchers.containsString("C-3PO"));
    }

    @Test
    public void checkSpecificField() {
        given().log().all().when().get("https://swapi.dev/api/people/2")
                .then().log().all().body("name", Matchers.equalTo("C-3PO"))
                .and().body("height", Matchers.equalTo("167"));
    }

    @Test
    public void getPostObject() {
        PostSW newPost = given().log().all().when().get("https://swapi.dev/api/people/2")
                .then().log().all().body("name", Matchers.equalTo("C-3PO"))
                .and().body("height", Matchers.equalTo("167")).extract().body().as(PostSW.class);

    }
}
