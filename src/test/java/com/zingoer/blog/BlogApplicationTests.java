package com.zingoer.blog;

import com.zingoer.blog.controller.BlogController;
import com.zingoer.blog.model.Blog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    BlogController blogController;

    @Test
    public void shouldGetAllBlogs() {
        List<Blog> blogs = blogController.getAll();
        assertThat(blogs.get(0).getId()).as("Blog's id is wrong").isEqualTo(1);
    }

    @Test
    public void shouldCreateBlog() {
        Map<String, String> body = new HashMap<>();
        body.put("title", "hello");
        body.put("content", "world");
        blogController.create(body);

        Blog blog = blogController.get(1);
        assertThat(blog.getId()).as("Newly created blog's id is wrong:").isEqualTo(1);
    }

}
