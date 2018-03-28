package ru.voskhod.dao.interfaces;

import ru.voskhod.entities.Person;
import ru.voskhod.entities.Phone;

import java.util.List;

public interface PhoneDao {
    List<Phone> getPhones();
    void addPhoneByPersonId(int personId, String phoneNumber);
}
