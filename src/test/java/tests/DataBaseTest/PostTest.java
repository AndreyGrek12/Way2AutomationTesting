package tests.DataBaseTest;

import helpers.DataBaseHelper;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import requests.PostRequests;

import java.sql.SQLException;
import java.util.UUID;

public class PostTest {

    private Response response;
    String content = "content" + UUID.randomUUID();
    String title = "title" + UUID.randomUUID();
    String newContent = "New Content" + UUID.randomUUID();
    String newTitle = "New Title" + UUID.randomUUID();

    @BeforeMethod
    public void setup() throws SQLException {
        DataBaseHelper.setSQLMode();
        DataBaseHelper.createPost(title,content);
    }

    @Test
    public void createPostTest() {
        response = PostRequests.getPostInfo(DataBaseHelper.getPostID(title));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("title.rendered"),
                title,
                "Заголовок не совпадает");
        Assert.assertEquals(response.jsonPath().getString("content.rendered"),
                "<p>" + content + "</p>\n",
                "Текст поста не совпадает");
        DataBaseHelper.deletePost(DataBaseHelper.getPostID(title));
    }

    @Test
    public void updatePostTest() {
        PostRequests.updatePost(newTitle, newContent, DataBaseHelper.getPostID(title));
        response = PostRequests.getPostInfo(DataBaseHelper.getPostID(newTitle));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("title.rendered"),
                newTitle,
                "Заголовок не совпадает");
        Assert.assertEquals(response.jsonPath().getString("content.rendered"),
                "<p>" + newContent + "</p>\n",
                "Текст поста не совпадает");
        DataBaseHelper.deletePost(DataBaseHelper.getPostID(newTitle));
    }

    @Test
    public void deletePostTest () {
        Integer id = DataBaseHelper.getPostID(title);
        DataBaseHelper.deletePost(DataBaseHelper.getPostID(title));
        response = PostRequests.getPostInfo(id);
        Assert.assertEquals(response.getStatusCode(),
                404,
                "Код ответа на запрос не совпадает");
    }
}
