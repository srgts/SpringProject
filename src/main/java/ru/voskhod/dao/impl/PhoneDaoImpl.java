package ru.voskhod.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.voskhod.dao.interfaces.PhoneDao;
import ru.voskhod.entities.Person;
import ru.voskhod.entities.Phone;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PhoneDaoImpl implements PhoneDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private Phone phone;
    @Autowired
    private Person person;

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Phone> getPhones() {
        List<Phone> phones = sessionFactory.getCurrentSession()
                .createQuery("from Phone ").list();
        for (Phone phone : phones) System.out.println(phone);
        return phones;
    }

    @Transactional
    public void addPhoneByPersonId(int personId, String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();
        person = session.get(Person.class, personId);
        phone.setPerson(person);
        phone.setPhoneNumber(phoneNumber);
        session.merge(phone);
    }
}
