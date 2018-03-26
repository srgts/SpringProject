package ru.voskhod.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.voskhod.dao.interfaces.PhoneDao;
import ru.voskhod.entities.Phone;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class PhoneDaoImpl implements PhoneDao {

    @Autowired
    private SessionFactory sessionFactory;

    private List<Phone> phones;

    @Transactional
    public void addPhone(Phone phone) {
        sessionFactory.getCurrentSession().persist(phone);
    }

    @Transactional
    @SuppressWarnings("unchecked")
    public List<Phone> getPhones() {
        phones = sessionFactory.getCurrentSession()
                .createQuery("from Phone ").list();
        for (Phone phone : phones) System.out.println(phone);
        return phones;
    }

    @Transactional
    public void removePhone(int id) {
        Session session = sessionFactory.getCurrentSession();
        Phone phone = session.get(Phone.class, id);
        if (phone != null) {
            session.delete(phone);
        } else {
            System.out.println("Запись с указанным id отсутствует");
        }
    }
}
