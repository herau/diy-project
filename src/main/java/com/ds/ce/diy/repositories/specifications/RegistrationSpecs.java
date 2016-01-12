package com.ds.ce.diy.repositories.specifications;

import com.ds.ce.diy.domain.Registration;
import com.ds.ce.diy.domain.Registration_;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public class RegistrationSpecs {

    public static Specification<Registration> isFuture() {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .greaterThan(root.get(Registration_.date), LocalDate.now());
    }

    //    public static Specification<Registration> isOnCurrentWeek() {
    //        TemporalField weekOfWeekBasedYear = WeekFields.of(Locale.FRANCE).weekOfWeekBasedYear();
    //        int weekNumber = LocalDate.now().get(weekOfWeekBasedYear);
    //        return (root, criteriaQuery, criteriaBuilder) -> {
    //            return criteriaBuilder.equal(weekNumber, root.get(Registration.MetaModel.date).)
    //        };
    //    }
}
