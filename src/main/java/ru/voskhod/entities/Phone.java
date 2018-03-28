package ru.voskhod.entities;

import javax.persistence.*;

@Entity
@Table(name = "phone", schema = "phonebook")
public class Phone {
    private int id;
    private String phoneNumber;

    private Person person;
    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
    @Column(name = "phoneNumber", length = 55)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNuber) {
        this.phoneNumber = phoneNuber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone phone = (Phone) o;

        if (id != phone.id) return false;
        if (phoneNumber != null ? !phoneNumber.equals(phone.phoneNumber) : phone.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", phoneNuber='" + phoneNumber + '\'' +
                ", person=" + person +
                '}';
    }
}
