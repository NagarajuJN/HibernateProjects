package net.nagaraj.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.nagaraj.hibernate.model.Student;
import net.nagaraj.hibernate.util.HibernateUtil;

public class App {
    public static void main(String[] args) {

        Student student = new Student(01,"Nagaraj", "Jn", "nagarajuuppi3@gmail.com");
        Student student1 = new Student(02,"Anil", "Kumar", "kumar@gamil.com");
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // start a transaction
            transaction = session.beginTransaction();
            // save the student objects
            session.save(student);
            session.save(student1);
            // commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List < Student > students = session.createQuery("from Student", Student.class).list();
            students.forEach(s -> System.out.println(s.getFirstname()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
