package com.example.demoViettel2.Repository;

import com.example.demoViettel2.Entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface iStudentRepository extends JpaRepository<Student, Long> {

    boolean existsByEmailAndClassName(String email, String className);
    @org.springframework.data.jpa.repository.Query(value = "SELECT s FROM Student s WHERE " +
            "(CONCAT(s.id, '') LIKE %:searchText%) OR " +
            "(s.firstName LIKE %:searchText%) OR " +
            "(s.lastName LIKE %:searchText%) OR " +
            "(s.email LIKE %:searchText%) OR " +
            "(s.className LIKE %:searchText%)")
    List<Student> findStudentsByMultipleFields(@Param("searchText") String searchText);


    @org.springframework.data.jpa.repository.Query(value = " SELECT s FROM Student s ORDER BY s.id DESC")
    List<Student> findAllByOrderByidDesc();

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO classes (class_name) SELECT :className WHERE NOT EXISTS (SELECT 1 FROM classes WHERE class_name = :className)", nativeQuery = true)
    void addNewClassName(@Param("className") String className);

}

