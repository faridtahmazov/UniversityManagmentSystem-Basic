package com.company.repository;

import com.company.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
//    List<Teacher> findTeachersByNameLikeOrSubjectLike(@Param("hint") String hint);

    @Query("SELECT t FROM Teacher t WHERE t.name LIKE %:hint% OR t.subject LIKE %:hint%")
    List<Teacher> findTeachersByNameLikeOrSubjectLike(@Param("hint") String hint);
}
