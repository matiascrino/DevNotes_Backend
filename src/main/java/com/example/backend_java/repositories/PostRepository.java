package com.example.backend_java.repositories;

import com.example.backend_java.entities.PostEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity, Long> {

    List<PostEntity> getByUserIdOrderByCreatedAtDesc(long userId);

    PostEntity save(PostEntity postEntity);

    @Query(value = "SELECT * FROM posts p WHERE p.expousure_id = :expousure and p.expires_at > :now ORDER BY created_at DESC LIMIT 20", nativeQuery = true)
    List<PostEntity> getLastPublicPosts(@Param("expousure") long expousureId, @Param("now") Date now);

    Optional<PostEntity> findByPostId(String postId);

    void delete(PostEntity postEntity);

}
