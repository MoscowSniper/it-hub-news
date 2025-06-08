package org.wndtn.ithubnews;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /** Все категории */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /** Найти по ID (используется при редактировании) */
    public Optional<Category> getById(UUID id) {
        return categoryRepository.findById(id);
    }

    /** Найти по slug (для публичной части сайта) */
    public Optional<Category> getBySlug(String slug) {
        return categoryRepository.findBySlug(slug);
    }

    /** Создать или обновить категорию */
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    /** Удаление по ID (используется в AdminController) */
    public void delete(UUID id) {
        categoryRepository.deleteById(id);
    }

    /** Удаление по объекту (если пригодится) */
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
}
