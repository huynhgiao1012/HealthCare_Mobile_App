package com.healthcareapp.healthcareappbackend.repository;

import com.healthcareapp.healthcareappbackend.entity.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

    @Query(value = "select acc from AccountEntity acc where acc.idCard=?1", nativeQuery = false)
    Optional<AccountEntity> findByIdCard(String idCard);

    @Override
    AccountEntity save(AccountEntity entity);
}
