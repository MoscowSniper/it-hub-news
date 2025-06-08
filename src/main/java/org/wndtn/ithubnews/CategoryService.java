package org.wndtn.ithubnews;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wndtn.ithubnews.Category;
import org.wndtn.ithubnews.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
