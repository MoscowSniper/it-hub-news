package org.wndtn.ithubnews;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.wndtn.ithubnews.Category;
import org.wndtn.ithubnews.Post;
import org.wndtn.ithubnews.CategoryService;
import org.wndtn.ithubnews.PostService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PublicController {

    private final PostService postService;
    private final CategoryService categoryService;

    // Главная страница — все опубликованные посты
    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postService.getPublishedPosts();
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "index";
    }

    // Новости по категории
    @GetMapping("/category/{slug}")
    public String byCategory(@PathVariable String slug, Model model) {
        Category category = categoryService.getBySlug(slug).orElseThrow();
        List<Post> posts = postService.getPostsByCategory(category);
        model.addAttribute("posts", posts);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("selectedCategory", category.getName());
        return "index";
    }

    // Поиск по ключевому слову
    @GetMapping("/search")
    public String search(@RequestParam String q, Model model) {
        List<Post> results = postService.searchByKeyword(q);
        model.addAttribute("posts", results);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("searchQuery", q);
        return "index";
    }

    // Детальная страница новости
    @GetMapping("/post/{slug}")
    public String viewPost(@PathVariable String slug, Model model) {
        Post post = postService.getBySlug(slug).orElseThrow();
        model.addAttribute("post", post);
        return "post";
    }

}
