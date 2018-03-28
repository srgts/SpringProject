package ru.voskhod.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "person", schema = "phonebook")
public class Person {
    private int id;
    private String personName;

    private Set<Phone> phones = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    public Set<Phone> getPhones() {
        return this.phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void addPhone(Phone phone) {
        phone.setPerson(this);
        this.phones.add(phone);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "person_name", length = 55)
    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        if (personName != null ? !personName.equals(person.personName) : person.personName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (personName != null ? personName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", personName='" + personName + '\'' +
                '}';
    }
}
