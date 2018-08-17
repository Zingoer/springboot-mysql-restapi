package com.zingoer.blog.controller;

import com.zingoer.blog.model.Blog;
import com.zingoer.blog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BlogController {

    @Autowired
    BlogRepository blogRepository;

    @RequestMapping("/")
    public String index() {
        return "Congratulations! -> !!!";
    }

    @GetMapping("/blog")
    public List<Blog> getAll() {
        return blogRepository.findAll();
    }

    @GetMapping("/blog/{id}")
    public Blog get(@PathVariable Integer id) {
        Optional<Blog> blog = blogRepository.findById(id);
        return blog.orElse(null);
    }

    @PostMapping("/blog/search")
    public List<Blog> search(@RequestBody Map<String, String> body) {
        return blogRepository.findByTitleContainingOrContentContaining(body.get("text"), body.get("text"));
    }

    @PostMapping("/blog")
    public void create(@RequestBody Map<String, String> body) {
        blogRepository.save(new Blog(body.get("title"), body.get("content")));
    }

    @PutMapping("/blog/{id}")
    public Blog update(@RequestParam Integer id, @RequestBody Map<String, String> body, HttpServletResponse response) {
        Optional<Blog> blogOptional = blogRepository.findById(id);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.setTitle(body.get("title"));
            blog.setContent(body.get("content"));
            return blogRepository.save(blog);
        }

        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return null;
    }

    @DeleteMapping("/blog/{id}")
    public void delete(@RequestParam Integer id) {
        blogRepository.deleteById(id);
    }
}
