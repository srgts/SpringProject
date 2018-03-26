package ru.voskhod.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.voskhod.dao.interfaces.PhoneDao;
import ru.voskhod.entities.Phone;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class AppMain {
    static ApplicationContext context =
            new ClassPathXmlApplicationContext("context.xml");

    public static void main(String[] args) {
        System.out.println("<<<<ТЕЛЕФОННЫЙ СПРАВОЧНИК>>>>");
        info();
        getRequest();
    }

    private static void getRequest() {
        PhoneDao phoneDao;
        String input = reader();
        switch (input) {
            case "Показатьсправочник":
                phoneDao = (PhoneDao) context.getBean("phoneDaoImpl");
                phoneDao.getPhones();
                getRequest();
            case "Добавитьзапись":
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
                        Phone phone = (Phone) context.getBean("phone");
                        phone.setPersonName(name);
                        phone.setPhoneNumber(phoneNumber);
                        phoneDao = (PhoneDao) context.getBean("phoneDaoImpl");
                        phoneDao.addPhone(phone);
                        getRequest();
                    }
                }
            case "Удалитьзапись":
                System.out.println("Укажите id записи, которую необходимо удалить");
                int id = 0;
                try {
                    id = Integer.parseInt(reader());
                } catch (Exception e) {
                    System.out.println("Введен некоррекный id. Повторите команду");
                    getRequest();
                }
                phoneDao = (PhoneDao) context.getBean("phoneDaoImpl");
                phoneDao.removePhone(id);
                getRequest();
            case "Выйти":
                System.exit(0);
        }
        System.out.println("Команда введена некорректно");
        info();
        getRequest();

    }

    private static void info() {
        System.out.println("Чтобы получить данные телефонный справочник, введите команду 'Показать справочник'");
        System.out.println("Чтобы добавать запись в телефонный справочник, введите команду 'Добавить запись'");
        System.out.println("Чтобы удалить запись из телефонного справочник, введите команду 'Удалить запись'");
        System.out.println("Чтобы выйти из приложения, введите команду 'Выйти'");

    }

    private static String reader() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = br.readLine().replaceAll(" ", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return input;
    }
}