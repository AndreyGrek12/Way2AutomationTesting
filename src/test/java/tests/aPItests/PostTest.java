package tests.aPItests;

import helpers.PropertiesProvider;
import io.restassured.response.Response;

import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.*;
import requests.PostRequests;

public class PostTest {

    private Response response;

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = PropertiesProvider.getProperty("baseURI");
        response = PostRequests.createPost("title","content");
    }

    @AfterMethod
    public void deletePost() {
        PostRequests.deletePost(response.jsonPath().getInt("id"));
    }

    @Test
    public void createPostTest(){
        Assert.assertEquals(response.getStatusCode(),
                201,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("title.raw"),
                "title",
                "Заголовки не совпадают");
        Assert.assertEquals(response.jsonPath().getString("content.raw"),
                "content",
                "Контентная часть не совпадает");
        Assert.assertEquals(response.jsonPath().getString("status"),
                "publish",
                "Пост не опубликован");
    }

    @Test
    public void updatePostTest () {
        response = PostRequests.updatePost("new Title","new Content", response.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("title.raw"),
                "new Title",
                "Заголовки не совпадают");
        Assert.assertEquals(response.jsonPath().getString("content.raw"),
                "new Content",
                "Контентная часть не совпадает");
    }

    @Test
    public void deletePostTest() {
        response = PostRequests.deletePost(response.jsonPath().getInt("id"));
        Assert.assertEquals(response.getStatusCode(),
                200,
                "Код ответа на запрос не совпадает");
        Assert.assertEquals(response.jsonPath().getString("status"),
                "trash",
                "Пост не удален");
    }
}
