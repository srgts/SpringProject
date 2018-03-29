package ru.voskhod.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.voskhod.dao.interfaces.PhoneDao;
import ru.voskhod.entities.Person;
import ru.voskhod.entities.Phone;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class PhoneDaoImpl implements PhoneDao {

    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private Phone phone;
    @Autowired
    private Person person;

    private List<String[]> notes = new ArrayList<>();

    @Transactional
    @SuppressWarnings("unchecked")
    public void getPhones() {
        List<Phone> phones = sessionFactory.getCurrentSession()
                .createQuery("from Phone ").list();
        for (Phone phone : phones) {
            String[] fields = (String.valueOf(phone).replaceAll("person=", "")
                    .replaceAll("[},']", "")
                    .replaceAll("[{=]", " ")
                    .split(" "));
            notes.add(fields);
            for (int i = 0; i < fields.length; i++) {
                System.out.print(fields[i]+ "  ");
            }
            System.out.println();
        }
    }

    @Transactional
    public void addPhoneByPersonId(int personId, String phoneNumber) {
        Session session = sessionFactory.getCurrentSession();
        person = session.get(Person.class, personId);
        phone.setPerson(person);
        phone.setPhoneNumber(phoneNumber);
        session.merge(phone);
    }

    public List<String[]> getNotes(){
        return notes;
    }
}
