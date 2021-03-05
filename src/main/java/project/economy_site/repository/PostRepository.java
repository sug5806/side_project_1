package project.economy_site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.economy_site.entitiy.post.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
