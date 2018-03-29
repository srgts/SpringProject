package ru.voskhod.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.voskhod.dao.interfaces.PersonDao;
import ru.voskhod.entities.Person;

import javax.transaction.Transactional;

@Component
public class PersonDaoImpl implements PersonDao{

    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public void addPerson(Person person) {
        sessionFactory.getCurrentSession().merge(person);
    }

    @Transactional
    public void removePerson(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.get(Person.class, id);
        if (person != null) {
            session.delete(person);
        } else {
            System.out.println("Запись с указанным id отсутствует");
        }
    }

}
