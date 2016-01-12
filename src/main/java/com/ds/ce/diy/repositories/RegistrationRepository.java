package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.Registration;
import com.ds.ce.diy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.util.Optional;

@PreAuthorize("hasAnyAuthority('MEMBER, ADMIN')")
public interface RegistrationRepository extends JpaRepository<Registration, Integer>, JpaSpecificationExecutor<Registration> {

    Optional<Registration> findByUserAndDate(User user, LocalDate date);

}
