package phonebook;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Phone Phone = new Phone();

    public static void main(String[] args) {
        printActions();
        while (true) {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> Phone.printContacts();
                case 2 -> addNewContact();
                case 3 -> updateContact();
                case 4 -> removeContact();
                case 5 -> queryContact();
            }
        }
    }

    private static void addNewContact() {
        System.out.println("Введите имя: ");
        String name = scanner.nextLine();
        System.out.println("Введите номер телефона: ");
        String phone = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phone);
        if (Phone.addNewContact(newContact)) {
            System.out.println("Новый контакт: имя = " + name + ", номер телефона = " + phone);
        } else {
            System.out.println("Не удается добавить, " + name + " уже есть в файле");
        }
    }

    private static void updateContact() {
        System.out.println("Введите имя");
        String name1 = scanner.nextLine();
        Contact existingContactRecord = Phone.queryContact(name1);
        if (existingContactRecord == null) {
            System.out.println("Контакт не найден");
            System.exit(0);
        }

        System.out.println("Введите новое имя ");
        String newName = scanner.nextLine();
        System.out.println("Введите новый номер телефона ");
        String newNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(newName, newNumber);
        if (Phone.updateContact(existingContactRecord, newContact)) {
            System.out.println("Контакт обнавлён");

        } else {
            System.out.println("Произошла ошибка");
        }
    }

    private static void removeContact() {
        System.out.println("Введите имя ");
        String name1 = scanner.nextLine();
        Contact existingRecord = Phone.queryContact(name1);
        if (existingRecord == null) {
            System.out.println("Контакт не найден");
            System.exit(0);
        }
        if (Phone.removeContact(existingRecord)) {
            System.out.println("Контакт удалён");
        } else {
            System.out.println("Произошла ошибка");
        }
    }

    private static void queryContact() {
        System.out.println("Введите имя");
        String name1 = scanner.nextLine();
        Contact existingContactRecord = Phone.queryContact(name1);
        if (existingContactRecord == null) {
            System.out.println("Контакт не найден");
            System.exit(0);
        }
        System.out.println("Контакт найден!");
        System.out.println("Имя: " + existingContactRecord.getName() + " | Номер телефона: " + existingContactRecord.getPhoneNumber());
    }

    private static void printActions() {
        System.out.println("Меню");
        System.out.println("""
                1  - показать контакты
                2  - добавить новый контакт
                3  - изменить существующий контакт
                4  - удалить существующий контакт
                5  - поиск контакта""");
        System.out.println("Выберите действие:");
    }
}
