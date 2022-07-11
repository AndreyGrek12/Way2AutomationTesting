package tests.aPItests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requests.CommentRequests;
import requests.PostRequests;

public class CommentTest {

    private Response createPostResponse;
    private Response createCommentResponse;

    @BeforeMethod
    public void setup() {
        createPostResponse = PostRequests.createPost("title","content");
        createCommentResponse = CommentRequests.createComment("content",
                createPostResponse.jsonPath().getInt("id"));
    }

    @AfterMethod
    public void deletePost() {
        PostRequests.deletePost(createPostResponse.jsonPath().getInt("id"));
    }

    @Test
    public void createCommentTest() {
        Assert.assertEquals(createCommentResponse.getStatusCode(),
                201,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(createCommentResponse.jsonPath().getString("content.raw"),
                "content",
                "Контентная часть не совпадает");
    }

    @Test
    public void updateCommentTest () {
        Response response = CommentRequests.updateComment("new Content",createCommentResponse.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("content.raw"),
                "new Content",
                "Контентная часть не совпадает");
    }

    @Test
    public void deleteCommentTest () {
        Response response = CommentRequests.deleteComment(createCommentResponse.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
    }
}
