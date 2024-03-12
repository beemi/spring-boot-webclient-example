package com.jaitechltd.webclientsspringbootexample.repository;

import com.jaitechltd.webclientsspringbootexample.domain.PostCodeIoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostCodeIoRepository extends JpaRepository<PostCodeIoEntity, Long> {

    @Query("SELECT p FROM PostCodeIoEntity p WHERE p.postcode = ?1")
    Optional<List<PostCodeIoEntity>> findByPostcode(final String postcode);
}
