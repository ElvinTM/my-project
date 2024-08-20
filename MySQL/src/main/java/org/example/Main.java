package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        String hql = "from " + PurchaseList.class.getSimpleName();
        List<PurchaseList> purchaseList = session.createQuery(hql).getResultList();

        for (PurchaseList purchase : purchaseList) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Student> studentCriteria = builder.createQuery(Student.class);
            Root<Student> studentRoot = studentCriteria.from(Student.class);
            studentCriteria.select(studentRoot)
                    .where(builder.equal(studentRoot.get("name"), purchase.getStudentName()));
            Student student = session.createQuery(studentCriteria).getSingleResult();

            CriteriaQuery<Course> courseCriteria = builder.createQuery(Course.class);
            Root<Course> courseRoot = courseCriteria.from(Course.class);
            courseCriteria.select(courseRoot)
                    .where(builder.equal(courseRoot.get("name"), purchase.getCourseName()));
            Course course = session.createQuery(courseCriteria).getSingleResult();

            LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList();
            linkedPurchaseList
                    .setId(new LinkedPurchaseList.LinkedPurchaseListKey(student.getId(), course.getId()));
            linkedPurchaseList.setStudentId(student.getId());
            linkedPurchaseList.setCourseId(course.getId());
            session.save(linkedPurchaseList);
        }

        transaction.commit();
    }
}