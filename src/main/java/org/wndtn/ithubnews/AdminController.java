package org.wndtn.ithubnews;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.wndtn.ithubnews.Category;
import org.wndtn.ithubnews.Post;
import org.wndtn.ithubnews.CategoryService;
import org.wndtn.ithubnews.PostService;

import java.time.LocalDateTime;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final CategoryService categoryService;
    private final PostService postService;


    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("posts", postService.getPublishedPosts());
        return "admin/dashboard";
    }

    // --- Новости ---
    @GetMapping("/post/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/post-form";
    }

    @PostMapping("/post/save")
    public String savePost(@ModelAttribute Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postService.save(post);
        return "redirect:/admin";
    }

    @GetMapping("/post/edit/{id}")
    public String editPost(@PathVariable UUID id, Model model) {
        Post post = postService.getById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/post-form";
    }

    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable UUID id) {
        postService.delete(id);
        return "redirect:/admin";
    }

}
