package tests.aPItests;

import helpers.DataBaseHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.*;
import requests.PostRequests;

import java.sql.SQLException;

public class PostTest {

    private Response response;
    String content = "content";
    String title = "title";
    String newContent = "New Content";
    String newTitle = "New Title";

    @BeforeMethod
    public void setup() throws SQLException {
        response = PostRequests.createPost(title,content);
    }

    @AfterMethod
    public void deletePost() {
        PostRequests.deletePost(response.jsonPath().getInt("id"));
    }

    @Test
    public void createPostTest() throws SQLException {
        Assert.assertEquals(response.getStatusCode(),
                201,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(DataBaseHelper.selectDataFromPosts(response.jsonPath().getInt("id"),
                                "post_title"),
                title,
                "Заголовки не совпадают");
        Assert.assertEquals(DataBaseHelper.selectDataFromPosts(response.jsonPath().getInt("id"),
                        "post_content"),
                content,
                "Контентная часть не совпадает");
        Assert.assertEquals(DataBaseHelper.selectDataFromPosts(response.jsonPath().getInt("id"),
                        "post_status"),
                "publish",
                "Пост не опубликован");
    }

    @Test
    public void updatePostTest () throws SQLException {
        response = PostRequests.updatePost(newTitle,newContent, response.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(DataBaseHelper.selectDataFromPosts(response.jsonPath().getInt("id"),
                        "post_title"),
                newTitle,
                "Заголовки не совпадают");
        Assert.assertEquals(DataBaseHelper.selectDataFromPosts(response.jsonPath().getInt("id"),
                        "post_content"),
                newContent,
                "Контентная часть не совпадает");
    }

    @Test
    public void deletePostTest() throws SQLException {
        response = PostRequests.deletePost(response.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(DataBaseHelper.selectDataFromPosts(response.jsonPath().getInt("id"),
                        "post_status"),
                "trash",
                "Пост не удален");
    }
}
