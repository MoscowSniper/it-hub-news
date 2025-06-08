package org.wndtn.ithubnews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.wndtn.ithubnews.Category;
import org.wndtn.ithubnews.Post;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<Post, UUID> {

    List<Post> findAllByPublishedTrueOrderByCreatedAtDesc();

    List<Post> findAllByCategoryAndPublishedTrueOrderByCreatedAtDesc(Category category);

    Optional<Post> findBySlugAndPublishedTrue(String slug);

    List<Post> findByTitleContainingIgnoreCaseAndPublishedTrue(String keyword);

}
