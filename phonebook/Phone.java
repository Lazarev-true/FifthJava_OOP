package phonebook;

import java.util.ArrayList;

public class Phone {
    private final ArrayList<Contact> contactList = new ArrayList<>();

    public boolean addNewContact(Contact contact) {
        if (findContact(contact.getName()) >= 0) {
            System.out.println("Контакт уже существует");
            return false;
        }
        this.contactList.add(contact);
        System.out.println("Контакт добавлен");
        return true;
    }

    public void printContacts() {
        System.out.println("Список");
        for (int i = 0; i < this.contactList.size(); i++) {
            System.out.println((i + 1) + ". " +
                    this.contactList.get(i).getName() + " -> " +
                    this.contactList.get(i).getPhoneNumber());
        }
    }

    public boolean updateContact(Contact oldContact, Contact newContact) {
        int foundPosition = findContact(oldContact);
        if (foundPosition < 0) {
            System.out.println(oldContact.getName() + " не был найден");
            System.exit(0);

        } else if (findContact(newContact.getName()) != -1) {
            System.out.println("Контакт с именем " + newContact.getName() +
                    " уже существует");
            return false;
        }
        this.contactList.set(foundPosition, newContact);
        System.out.println(oldContact.getName() + " был заменен на " + newContact.getName());
        return true;
    }

    public boolean removeContact(Contact contact) {
        int foundPosition = findContact(contact);
        if (foundPosition < 0) {
            System.out.println(contact.getName() + " не был найден");
            return false;
        } else {
            this.contactList.remove(foundPosition);
            System.out.println(contact.getName() + " контакт удален");
            return true;
        }
    }

    private int findContact(Contact contact) {
        return this.contactList.indexOf(contact);
    }

    private int findContact(String contactName) {
        for (int i = 0; i < this.contactList.size(); i++) {
            Contact contact = this.contactList.get(i);
            if (contact.getName().equals(contactName)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if (position >= 0) {
            return this.contactList.get(position);
        }
        return null;
    }
}
