package tests.DataBaseTest;

import helpers.DataBaseHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requests.CommentRequests;
import requests.PostRequests;

import java.sql.SQLException;
import java.util.UUID;

public class CommentTest {

    private Response response;
    String title = "title" + UUID.randomUUID();
    String content = "content" + UUID.randomUUID();
    String newContent = "New Content" + UUID.randomUUID();

    @BeforeMethod
    public void setup() throws SQLException {
        DataBaseHelper.setSQLMode();
        DataBaseHelper.createPost(title,content);
        DataBaseHelper.createComment(DataBaseHelper.getPostID(title), content);
    }

    @AfterMethod
    public void deletePost() {
        DataBaseHelper.deletePost(DataBaseHelper.getPostID(title));
    }
    @Test
    public void createCommentTest() {
        response = CommentRequests.getCommentInfo(DataBaseHelper.getCommentID(content));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("content.rendered"),
                "<p>" + content + "</p>\n",
                "Содержимое не совпадает");
        DataBaseHelper.deleteComment(DataBaseHelper.getCommentID(content));
    }

    @Test
    public void updateCommentTest() {
        DataBaseHelper.updateComment(newContent, DataBaseHelper.getCommentID(content));
        response = CommentRequests.getCommentInfo(DataBaseHelper.getCommentID(newContent));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("content.rendered"),
                "<p>" + newContent + "</p>\n",
                "Содержимое не совпадает");
        DataBaseHelper.deleteComment(DataBaseHelper.getCommentID(newContent));
    }

    @Test
    public void deleteCommentTest() {
        Integer id = DataBaseHelper.getCommentID(content);
        DataBaseHelper.deleteComment(DataBaseHelper.getCommentID(content));
        response = CommentRequests.getCommentInfo(id);
        Assert.assertEquals(response.getStatusCode(),
                404,
                "Код ответа на запрос не совпадает");
    }
}
