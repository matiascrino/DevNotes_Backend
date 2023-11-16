package com.example.backend_java.repositories;

import com.example.backend_java.dtos.PostDto;
import com.example.backend_java.entities.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends CrudRepository<PostEntity, Long> {

    List<PostEntity> getByUserIdOrderByCreatedAtDesc(long userId);

    PostEntity save(PostEntity postEntity);

    @Query("SELECT p FROM posts p WHERE p.expousure.expousureId = :expousureId ORDER BY p.createdAt DESC")
    List<PostEntity> getLastPublicPosts(@Param("expousureId") long expousureId);

    Optional<PostEntity> findByPostId(String postId);

    void delete(PostEntity postEntity);

}
