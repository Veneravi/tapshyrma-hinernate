package org.example;

import org.example.entity.Person;
import org.example.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class App {
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();
        Person person = new Person("Nurlan Mamatkasym uulu", 23);
        Person person2 = new Person("Elnura Tajibaeva", 22);
        App.create(person);
        App.create(person2);

        App.read();
        List<Person> personList = App.read();
        for (Person n : personList) {
            System.out.println(n);
        }
        App.getById(1);
        App.update(2, "Mirbek", 21);
        App.delete(1);
        System.out.println("----------------------------------------------------------------------------------------");


    }

    public static int create(Person person) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(e.getMessage());
        }
        return person.getId();
    }

    public static Person getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            Person person = session.get(Person.class, id);
            session.getTransaction().commit();
//            transaction.commit();
            System.out.println("Person" + person + "GetById");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return new Person();
    }

    public static void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            session.beginTransaction();
            Person person = session.get(Person.class, id);
            session.delete(person);
//
            session.getTransaction().commit();
            System.out.println("Person" + person + "DELETE!");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void update(int id, String name, int age) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Person person = session.get(Person.class, id);
            person.setId(id);
            person.setName(name);
            person.setAge(age);
            transaction.commit();
            System.out.println("Person" + person + " UPDATE!");
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<Person> read() {
        Transaction transaction = null;

        List<Person> personList = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            personList = session.createQuery("FROM Person ").list();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }

            System.out.println(e.getMessage());
        }
        return personList;
    }
}







