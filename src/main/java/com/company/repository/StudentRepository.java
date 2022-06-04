package com.company.repository;

import com.company.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
//    List<Student> findStudentsByNameLikeOrProfessionLike(@Param("hint")String hint);

    @Query("SELECT s FROM Student s WHERE s.name LIKE %:hint% OR s.profession LIKE %:hint%")
    List<Student> findStudentsByNameLikeOrProfessionLike(@Param("hint") String hint);
}
