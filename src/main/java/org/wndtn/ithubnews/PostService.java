package org.wndtn.ithubnews;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.wndtn.ithubnews.Category;
import org.wndtn.ithubnews.Post;
import org.wndtn.ithubnews.PostRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> getPublishedPosts() {
        return postRepository.findAllByPublishedTrueOrderByCreatedAtDesc();
    }

    public List<Post> getPostsByCategory(Category category) {
        return postRepository.findAllByCategoryAndPublishedTrueOrderByCreatedAtDesc(category);
    }

    public Optional<Post> getBySlug(String slug) {
        return postRepository.findBySlugAndPublishedTrue(slug);
    }

    public List<Post> searchByKeyword(String keyword) {
        return postRepository.findByTitleContainingIgnoreCaseAndPublishedTrue(keyword);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public void delete(UUID id) {
        postRepository.deleteById(id);
    }
    public Optional<Post> getById(UUID id) {
        return postRepository.findById(id);

    }

}
