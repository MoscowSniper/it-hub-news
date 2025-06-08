package org.wndtn.ithubnews;


import org.springframework.data.jpa.repository.JpaRepository;
import org.wndtn.ithubnews.Category;

import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findBySlug(String slug);
}
