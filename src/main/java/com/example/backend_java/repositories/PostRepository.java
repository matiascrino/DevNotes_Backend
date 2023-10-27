package com.example.backend_java.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends CrudRepository<PostRepository, Long> {


}
