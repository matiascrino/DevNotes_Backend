package com.example.backend_java.repositories;

import com.example.backend_java.entities.ExpousureEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpousureRepository extends CrudRepository<ExpousureEntity, Long> {

}
