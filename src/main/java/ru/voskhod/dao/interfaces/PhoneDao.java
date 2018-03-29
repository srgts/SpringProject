package ru.voskhod.dao.interfaces;

import java.util.List;

public interface PhoneDao {
    void getPhones();
    void addPhoneByPersonId(int personId, String phoneNumber);
    List<String[]> getNotes();
}
