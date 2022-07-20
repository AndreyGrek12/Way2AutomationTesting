package tests.aPItests;

import helpers.DataBaseHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requests.CommentRequests;
import requests.PostRequests;

import java.sql.SQLException;

public class CommentTest {

    private Response createPostResponse;
    private Response createCommentResponse;
    String title = "title";
    String content = "content";
    String newContent = "New Content";

    @BeforeMethod
    public void setup() throws SQLException {
        DataBaseHelper.createConnection();
        createPostResponse = PostRequests.createPost(title,content);
        createCommentResponse = CommentRequests.createComment(content,
                createPostResponse.jsonPath().getInt("id"));
    }

    @AfterMethod
    public void deletePost() {
        PostRequests.deletePost(createPostResponse.jsonPath().getInt("id"));
    }

    @Test
    public void createCommentTest() throws SQLException {
        Assert.assertEquals(createCommentResponse.getStatusCode(),
                201,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(DataBaseHelper.selectDataFromComments(createCommentResponse.jsonPath().getInt("id"))
                        .getString("comment_content"),
                content,
                "Контентная часть не совпадает");
    }

    @Test
    public void updateCommentTest () throws SQLException {
        Response response = CommentRequests.updateComment(newContent,createCommentResponse.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(DataBaseHelper.selectDataFromComments(createCommentResponse.jsonPath().getInt("id"))
                        .getString("comment_content"),
                newContent,
                "Контентная часть не совпадает");
    }

    @Test
    public void deleteCommentTest () {
        Response response = CommentRequests.deleteComment(createCommentResponse.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertFalse(DataBaseHelper.isRawExist(
                DataBaseHelper.selectDataFromComments(createCommentResponse.jsonPath().getInt("id"))),
                "Комментарий не удален");
    }
}
