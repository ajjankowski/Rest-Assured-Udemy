import io.restassured.http.ContentType;
import model.Post;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class BasicPostsTest {

    @Test
    public void getPosts() {
        // given = preconditions
        // when  = action
        // then  = expected result

        // body = logs body
        when().get("https://swapi.dev/api/").then().log().body();
        // all  = logs body and parameters
        when().get("https://swapi.dev/api/").then().log().all();

        // expects to have status code xxx -> if not xxx, assertion will fail
        when().get("https://swapi.dev/api/").then().log().all().statusCode(200);

        // expects to have status code > xxx -> if not > xxx, assertion will fail (many Matcher. methods)
        when().get("https://swapi.dev/api/").then().log().all().statusCode(Matchers.greaterThanOrEqualTo(200));

        // expects to have status code xxx -> if other, logs error, if 200 -> logs nothing
        when().get("https://swapi.dev/api/").then().log().ifValidationFails().statusCode(200);
    }

    @Test
    public void getPostsByParam() {
        given().pathParam("PostId", 1)
                .when().get("https://swapi.dev/api/people/{PostId}").then().log().all();
        given().log().all().pathParam("PostId", 1)
                .when().get("https://swapi.dev/api/people/{PostId}").then().log().all();
    }

    @Test
    public void addPosts() {
        String newPost = """
                {
                "title": "First post",\s
                "author": "Name",\s
                }""";
        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("https://httpbin.org/post")
                .then().log().all();
    }

    @Test
    public void addPostsFromFile() {
        File newPost = new File("src/test/resources/post.json");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("https://httpbin.org/post")
                .then().log().all();
    }

    @Test
    public void addPostMap() {
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "name");
        newPost.put("author", "anonymous");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("https://httpbin.org/post")
                .then().log().all();
    }

    @Test
    public void addPostObject() {
        Post newPost = new Post();
        newPost.setTitle("Title by object");
        newPost.setAuthor("Author by object");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().post("https://httpbin.org/post")
                .then().log().all();
    }

    @Test
    public void updatePostByPut() {
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "name after update");
        newPost.put("author", "anonymous after update");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().put("https://httpbin.org/post")
                .then().log().all();
    }

    @Test
    public void updatePostObjectByPut() {
        Post newPost = new Post();
        newPost.setTitle("Title by updated object");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().put("https://httpbin.org/put")
                .then().log().all();
    }

    @Test
    public void updatePostObjectByPatch() {
        Post newPost = new Post();
        newPost.setTitle("Title by updated object by patch");

        given().log().all().contentType(ContentType.JSON).body(newPost)
                .when().patch("https://httpbin.org/patch")
                .then().log().all();
    }

    @Test
    public void deletePost() {
        when().delete("https://httpbin.org/delete").then().log().all();
    }
}