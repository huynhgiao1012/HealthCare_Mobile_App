package com.healthcareapp.healthcareappbackend.repository;

import com.healthcareapp.healthcareappbackend.entity.AccountEntity;
import com.healthcareapp.healthcareappbackend.entity.SymptomEntity;
import com.healthcareapp.healthcareappbackend.entity.SymptomPatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface SymptomPatientRepository extends CrudRepository<SymptomPatientEntity, Long> {
    @Override
    SymptomPatientEntity save(SymptomPatientEntity entity);

    @Query(value = "select s from SymptomEntity s, SymptomPatientEntity sp where sp.account.idCard=?1 and s.id=sp.symptom.id")
    public List<SymptomEntity> getSymptoms(String idCard);
}
