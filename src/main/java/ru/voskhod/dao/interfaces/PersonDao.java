package ru.voskhod.dao.interfaces;

import ru.voskhod.entities.Person;

import java.util.List;

public interface PersonDao {
    void addPerson (Person person);
    void removePerson(int id);
}
