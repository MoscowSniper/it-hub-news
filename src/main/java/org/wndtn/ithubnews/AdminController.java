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

    /* =========================  ДАШБОРД  ========================= */

    @GetMapping
    public String dashboard(Model model) {
        model.addAttribute("posts", postService.getPublishedPosts());
        return "admin/dashboard";
    }

    /* =========================  КАТЕГОРИИ  ========================= */

    // список всех категорий
    @GetMapping("/categories")
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/category-list";
    }

    // форма создания новой категории
    @GetMapping("/category/new")
    public String createCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category-form";
    }

    // форма редактирования существующей категории
    @GetMapping("/category/edit/{id}")
    public String editCategoryForm(@PathVariable UUID id, Model model) {
        Category category = categoryService.getById(id).orElseThrow();
        model.addAttribute("category", category);
        return "admin/category-form";
    }

    // сохранение категории (новой или изменённой)
    @PostMapping("/category/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    // удаление категории
    @PostMapping("/category/delete/{id}")
    public String deleteCategory(@PathVariable UUID id) {
        categoryService.delete(id);
        return "redirect:/admin/categories";
    }

    /* =========================  ПОСТЫ  ========================= */

    // форма создания нового поста
    @GetMapping("/post/new")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/post-form";
    }

    // сохранение поста
    @PostMapping("/post/save")
    public String savePost(@ModelAttribute Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        postService.save(post);
        return "redirect:/admin";
    }

    // форма редактирования поста
    @GetMapping("/post/edit/{id}")
    public String editPost(@PathVariable UUID id, Model model) {
        Post post = postService.getById(id).orElseThrow();
        model.addAttribute("post", post);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/post-form";
    }

    // удаление поста
    @PostMapping("/post/delete/{id}")
    public String deletePost(@PathVariable UUID id) {
        postService.delete(id);
        return "redirect:/admin";
    }
}
