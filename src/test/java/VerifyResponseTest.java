import model.PostSW;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class VerifyResponseTest {

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
        System.out.println(newPost);
    }

    @Test
    public void verifyResponseTime() {
        long time = when().get("https://swapi.dev/api/people/2").timeIn(TimeUnit.MICROSECONDS);
        System.out.println(time);

        when()
                .get("https://swapi.dev/api/people/2").
        then()
                .time(Matchers.lessThan(2000L), TimeUnit.MICROSECONDS);
    }
}