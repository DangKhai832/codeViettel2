package com.example.demoViettel2.Repository;

import com.example.demoViettel2.Entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class iStudentRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;
    @Transactional(readOnly = true)
    public List<Student> findStudentByFieldSql(Student studentSearch)  {
        String sql = "select s from Student s where " +
                "(:id is null or s.id = :id) and " +
                "(:firstName is null or s.firstName like concat('%', :firstName, '%')) and " +
                "(:lastName is null or s.lastName like concat('%', :lastName, '%')) and " +
                "(:email is null or s.email like concat('%', :email, '%')) and " +
                "(:className is null or s.className like concat('%', :className, '%'))";


        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery(sql);
        query.setParameter("id",  studentSearch.getId());
        query.setParameter("firstName",  studentSearch.getFirstName());
        query.setParameter("lastName", studentSearch.getLastName());
        query.setParameter("email",  studentSearch.getEmail());
        query.setParameter("className",  studentSearch.getClassName());

        return (List<Student>) query.getResultList();
    }
}
