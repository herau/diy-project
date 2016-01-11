package com.ds.ce.diy.repositories;

import com.ds.ce.diy.domain.OfficeHours;
import com.ds.ce.diy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.LocalDate;
import java.util.Optional;

@PreAuthorize("hasAnyAuthority('MEMBER, ADMIN')")
public interface OfficeHoursRepository extends JpaRepository<OfficeHours, Integer> {

    Optional<OfficeHours> findByUserAndDate(User user, LocalDate date);
}
