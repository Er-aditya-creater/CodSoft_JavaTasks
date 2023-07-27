import java.io.*;
import java.util.*;

class Contact {
    private String name;
    private String phoneNumber;
    private String emailAddress;

    public Contact(String name, String phoneNumber, String emailAddress) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Phone: " + phoneNumber + ", Email: " + emailAddress;
    }
}

class AddressBook {
    private List<Contact> contacts;

    public AddressBook() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null;
    }

    public void displayAllContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveContactsToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Contact contact : contacts) {
                writer.println(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadContactsFromFile(String fileName) {
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0];
                    String phoneNumber = parts[1];
                    String emailAddress = parts[2];
                    Contact contact = new Contact(name, phoneNumber, emailAddress);
                    contacts.add(contact);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}

public class AddressBookSystem_5 {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        Scanner scanner = new Scanner(System.in);

        addressBook.loadContactsFromFile("contacts.txt");

        int choice = 0;
        while (choice != 5) {
            System.out.println("Welcome to the Address Book System!");
            System.out.println("1. Add a new contact");
            System.out.println("2. Search for a contact");
            System.out.println("3. Display all contacts");
            System.out.println("4. Remove a contact");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter email address: ");
                    String emailAddress = scanner.nextLine();
                    Contact newContact = new Contact(name, phoneNumber, emailAddress);
                    addressBook.addContact(newContact);
                    System.out.println("Contact added successfully!");
                    break;
                case 2:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    Contact foundContact = addressBook.searchContact(searchName);
                    if (foundContact != null) {
                        System.out.println("Contact found: " + foundContact);
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 3:
                    System.out.println("All contacts:");
                    addressBook.displayAllContacts();
                    break;
                case 4:
                    System.out.print("Enter name to remove: ");
                    String removeName = scanner.nextLine();
                    Contact contactToRemove = addressBook.searchContact(removeName);
                    if (contactToRemove != null) {
                        addressBook.removeContact(contactToRemove);
                        System.out.println("Contact removed successfully!");
                    } else {
                        System.out.println("Contact not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting Address Book System. Saving contacts...");
                    addressBook.saveContactsToFile("contacts.txt");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}

