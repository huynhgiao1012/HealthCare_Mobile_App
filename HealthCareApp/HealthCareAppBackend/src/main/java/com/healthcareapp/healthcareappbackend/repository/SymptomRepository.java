package com.healthcareapp.healthcareappbackend.repository;

import com.healthcareapp.healthcareappbackend.entity.AccountEntity;
import com.healthcareapp.healthcareappbackend.entity.SymptomEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface SymptomRepository extends CrudRepository<SymptomEntity, Long> {
    @Override
    SymptomEntity save(SymptomEntity entity);
}
