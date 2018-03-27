package ru.voskhod.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.voskhod.dao.interfaces.PersonDao;
import ru.voskhod.dao.interfaces.PhoneDao;
import ru.voskhod.entities.Person;
import ru.voskhod.entities.Phone;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppMain {
    private static ApplicationContext context =
            new ClassPathXmlApplicationContext("context.xml");

    public static void main(String[] args) {
        System.out.println("<<<<ТЕЛЕФОННЫЙ СПРАВОЧНИК>>>>");
        info();
        getRequest();
    }

    private static void getRequest() {
        PhoneDao phoneDao;
        PersonDao personDao;
        int id = 0;
        String input = reader();
        switch (input) {
            case "показатьсправочник":
                phoneDao = (PhoneDao) context.getBean("phoneDaoImpl");
                phoneDao.getPhones();
                getRequest();
            case "добавитьзапись":
                System.out.println("Укажите имя");
                String name;
                String phoneNumber;
                name = reader();
                if (name.equals("")) {
                    System.out.println("Имя не введено. Повторите команду");
                    getRequest();
                } else {
                    System.out.println("Введите новер телефона");
                    phoneNumber = reader();
                    if (phoneNumber.equals("")) {
                        System.out.println("Номер телефона на введен. Повторите команду");
                        getRequest();
                    } else {
                        Person person = (Person) context.getBean("person");
                        person.setPersonName(name);
                        Phone phone = (Phone) context.getBean("phone");
                        phone.setPhoneNumber(phoneNumber);
                        person.addPhone(phone);
                        personDao = (PersonDao) context.getBean("personDaoImpl");
                        personDao.addPerson(person);
                        getRequest();
                    }
                }
            case "удалитьзапись":
                System.out.println("Укажите id записи, которую необходимо удалить");
                try {
                    id = Integer.parseInt(reader());
                } catch (Exception e) {
                    System.out.println("Введен некоррекный id. Повторите команду");
                    getRequest();
                }
                personDao = (PersonDao) context.getBean("personDaoImpl");
                personDao.removePerson(id);
                getRequest();
            case "добавитьномертелефона":
                System.out.println("Укажите id записи, в которую необходимо добавить еще один телефон");
                try {
                    id = Integer.parseInt(reader());
                } catch (Exception e) {
                    System.out.println("Введен некоррекный id. Повторите команду");
                    getRequest();
                }
                System.out.println("Введите номер телефона");
                phoneNumber = reader();
                if (phoneNumber.equals("")) {
                    System.out.println("Номер телефона на введен. Повторите команду");
                    getRequest();
                }
                phoneDao = (PhoneDao) context.getBean("phoneDaoImpl");
                phoneDao.addPhoneByPersonId(id, phoneNumber);
                getRequest();
            case "выйти":
                System.exit(0);
        }
        System.out.println("Команда введена некорректно");
        info();
        getRequest();

    }

    private static void info() {
        System.out.println("-Чтобы получить данные телефонного справочника, введите команду 'Показать справочник"+"\n"
                +"-Чтобы добавать запись в телефонный справочник, введите команду 'Добавить запись'"+"\n"
                +"-Чтобы удалить запись из телефонного справочник, введите команду 'Удалить запись'"+"\n"
                +"-Чтобы добавить номер телефона к имеющейся записи, введите команду 'Добавить номер телефона'"+"\n"
                +"-Чтобы выйти из приложения, введите команду 'Выйти'");
    }

    private static String reader() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = br.readLine().replaceAll(" ", "").toLowerCase();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }
}