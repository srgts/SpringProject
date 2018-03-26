package ru.voskhod.dao.interfaces;

import ru.voskhod.entities.Phone;

import java.util.List;

public interface PhoneDao {
    void addPhone (Phone phone);
    List<Phone> getPhones();
    void removePhone(int id);
}
