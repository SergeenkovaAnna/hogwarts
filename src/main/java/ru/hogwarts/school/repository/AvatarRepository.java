package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import ru.hogwarts.school.model.Avatar;

import java.util.Optional;

public interface AvatarRepository extends PagingAndSortingRepository<Avatar, Long> {

    @Override
    Optional<Avatar> findById(Long studentId);
}
