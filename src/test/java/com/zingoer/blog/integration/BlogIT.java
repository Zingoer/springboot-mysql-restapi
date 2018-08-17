package com.zingoer.blog.integration;

import com.zingoer.blog.model.Blog;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.get;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BlogIT {

    @Test
    public void shouldGetAllBlogs() {
        get("blog/")
                .then()
                .body("size", equalTo(5))
                .root("[0]")
                .body("id", equalTo(1))
                .body("title", equalTo("Go up, up and away with your Google Assistant"))
        ;
    }

    @Test
    public void shouldGetOneBlog() {
        get("blog/2")
                .then()
                .body("id", equalTo(2))
                .body("title", equalTo("Get local help with your Google Assistant"))
        ;
    }

    @Test
    public void shouldCreateOneBlog() {
        Blog newBlog = new Blog("hello", "world");
        given()
                .body(newBlog)
                .contentType("application/json")
                .when()
                .post("blog/")
                .then()
                .statusCode(200);

        get("blog/1")
                .then()
                .body("title", equalTo("hello"))
                .body("content", equalTo("world"))
        ;
    }

    @Test
    public void shouldSearchBlogWithText() {
        given()
                .param("text", "helpful")
                .when()
                .post("blog/search")
                .then()
                .statusCode(200)
                .body("id", is(4))
        ;
    }
}
