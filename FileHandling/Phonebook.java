package practice.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Phonebook {
    static Scanner sc = new Scanner(System.in);
    static Contact contact;
    public static void main(String[] args) {
        int input;
        
        while (true) {
        System.out.println("OPTIONS:");
        System.out.println("*************************");
        System.out.println("1. Add Contact\n2. View Contacts\n3. Exit");
        System.out.println("*************************");
        System.out.print("Enter choice (number): ");
        input = sc.nextInt();

        sc.nextLine();

        switch (input) {

            case 1:
                add();
                break;
            case 2:
                display();
                break;
            case 3:
                return;
        }
    }
}

    public static void add() {
        System.out.println();
        String in;
    
        do {
            System.out.print("Enter name of contact: ");
            String name = sc.nextLine();
        
            System.out.print("Enter phone number: ");
            String phoneNumber = sc.nextLine();

            Contact contact = new
            Contact(name, phoneNumber);
            Contact.addContact(contact);
    
            System.out.print("Add another contact? (y/n): ");
            in = sc.nextLine();

        } while (in.equalsIgnoreCase("y"));
            System.out.println();
    }

    public static void display() {
        System.out.println();
        contact.viewContacts();
        System.out.println();
    }
}


class Contact{
    String name;
    String phoneNumber;
    static File phonebook = new File("app/src/main/java/practice/FileHandling/phonebook.csv");
    
    public Contact(String name, String phoneNumber){
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public static void addContact(Contact contact){
        try {
            FileWriter myWriter = new FileWriter(phonebook, true);
            myWriter.write(contact.name+ "," + contact.phoneNumber+ ",");
            myWriter.close();
        } catch (IOException e){
            System.out.print("error");
        }
    }

    public static void viewContacts(){
        String data;
        String[] newData;

        try {
        Scanner viewFile = new Scanner(phonebook);

        while (viewFile.hasNext()){
            data = viewFile.nextLine();
            newData = data.split(",");
            
            for (String res : newData) {
            System.out.println(res);
            }

            System.out.println("First element for name:" + newData[0]);
            System.out.println("Second element for phone number: " + newData[1]);
        }
        
    } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
