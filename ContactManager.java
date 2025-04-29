package dsaPractices;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class ContactManager {
        static LinkedList<Contact> contacts = new LinkedList<>();
        static HashMap<String, Contact> nameMap = new HashMap<>();
        static HashMap<String, LinkedList<Contact>> categoryMap = new HashMap<>();
        static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        while (true) { 
            System.out.println("====================");
            System.out.println("CHOOSE ACTION");
            System.out.println("====================");
            System.out.println("1. Add new contact\n2. Search contact\n3. Edit Contact\n4. Delete contact\n5. Display contacts\n6. Exit");
            System.out.println("====================");
            System.out.print("Enter input: ");
            int input = sc.nextInt();

            sc.nextLine();

            switch (input) {
                case 1:
                    add();
                    break;
                case 2:
                    search();
                    break;
                case 3: 
                    edit();
                    break;
                case 4:
                    delete();
                    break;
                case 5:
                    display();
                    break;
                case 6:
                    System.out.println();
                    System.out.println("Exiting browser...");
                    return;
                default:
                    System.out.println("Not a valid input.");
            }
        }
    }  

    public static void search() {
        System.out.println();
        System.out.print("Search (name/category): ");
        String searchChoice = sc.nextLine();

        if (searchChoice.equalsIgnoreCase("name")) {
            System.out.println();
            System.out.print("Search contact by name: ");
            String searchName = sc.nextLine();
    
            if(nameMap.containsKey(searchName)) {
                System.out.println("Found: " + nameMap.get(searchName) );
            } else {
                System.out.println("Contact not found.");
            }

        } else {
            System.out.print("Search contact by category (friends/family/others): ");
            String searchCate = sc.nextLine();

            if(categoryMap.containsKey(searchCate)){
                System.out.println();
                System.out.println("Contacts in " + searchCate + ":");
                for (Contact c : categoryMap.get(searchCate)) {
                    System.out.println(c);
                }
            } else {
                System.out.println("No contacts in this category/\nCategory does not exist.");
            }
        }

        System.out.println();
    }
    public static void add() {
        System.out.println();
        String choice;

        do { 
            Contact newContact = new Contact(sc);
            contacts.add(newContact);
            nameMap.put(newContact.getName(), newContact);
            categoryMap.putIfAbsent(newContact.getCategory(), new LinkedList<>());
            categoryMap.get(newContact.getCategory()).add(newContact);

            sc.nextLine(); 

            System.out.print("\nEnter new contact? (y/n): ");
            choice = sc.nextLine();
            System.out.println();

        } while (choice.equalsIgnoreCase("y")); 
    }
    public static void delete(){
        display();
        System.out.println();

        System.out.println("Enter index of the contact you want to delete: ");
        int index = sc.nextInt();

        Contact contactDel = contacts.remove(index-1);
        nameMap.remove(contactDel.getName());
        categoryMap.get(contactDel.getCategory().toLowerCase()).remove(contactDel);
    
    }
    public static void edit() {
        display();
        System.out.println();
        System.out.print("Enter index of the contact you want to edit: ");
        int index = sc.nextInt();

        System.out.println("\n=====================");
        System.out.println("Enter Operation ");
        System.out.println("=====================");
        System.out.println("1. Change name\n2. Change number\n3. Change category");
        System.out.println("=====================");
        System.out.print("Enter: ");
        int opt = sc.nextInt();

        switch (opt) {
            case 1: 
                sc.nextLine();
                System.out.println();
                System.out.print("Enter new name: ");
                String newName = sc.nextLine().toLowerCase().trim();

                Contact nameToChange = contacts.get(index-1);
                nameToChange.setName(newName);

                nameMap.remove(nameToChange.getName());
                nameMap.put(newName, nameToChange);

                if (categoryMap.containsKey(nameToChange.getCategory())) {
                    categoryMap.get(nameToChange.getCategory()).remove(nameToChange);
                    nameToChange.setName(newName); 
                    categoryMap.get(nameToChange.getCategory()).add(nameToChange);
                }
                break;
            case 2:
                System.out.println();
                System.out.print("Enter new number: ");
                long newNum = sc.nextInt();

                Contact numberToChange = contacts.get(index-1); 
                numberToChange.setPhoneNumber(newNum);

                nameMap.put(numberToChange.getName(), numberToChange);

                if (categoryMap.containsKey(numberToChange.getCategory())) {
                    categoryMap.get(numberToChange.getCategory()).remove(numberToChange);
                    numberToChange.setPhoneNumber(newNum);
                    categoryMap.get(numberToChange.getCategory()).add(numberToChange);
                }
                
                System.out.println();
                
                break;
            case 3:
                sc.nextLine();
                System.out.println();
                System.out.print("Enter updated category (family/friends/others): ");
                String updCat = sc.nextLine().toLowerCase().trim();

                Contact categoryUpdate = contacts.get(index-1);
                categoryMap.get(categoryUpdate.getCategory()).remove(categoryUpdate);
                
                categoryUpdate.setCategory(updCat);

                nameMap.put(categoryUpdate.getName(), categoryUpdate);


                categoryMap.putIfAbsent(updCat, new LinkedList<>());
                categoryMap.get(categoryUpdate.getCategory()).add(contacts.get(index-1));
        }

    }

    public static void display() {
        int n = 1;            
        System.out.println();
        System.out.println("All contacts: ");
        for (Contact c: contacts){
            System.out.println(n + ". " + c);
            n++;
        }
        System.out.println();
    }

}

class Contact {
    private String name;
    private long phoneNumber;
    private String category;

    public Contact(Scanner sc) {
        System.out.print("Enter name: ");
        this.name = sc.nextLine().toLowerCase().trim();

        System.out.print("Enter category(family/friends/others): ");
        this.category= sc.nextLine().toLowerCase().trim();
        
        
        System.out.print("Enter phone number: ");
        this.phoneNumber = sc.nextLong();
    }

    public String getName(){
        return name.toLowerCase().trim();
    }
    public void setName(String name) {
        this.name = name.toLowerCase().trim();
    }

    public String getCategory() {
        return category.toLowerCase().trim();
    }
    public void setCategory(String category) {
        this.category = category.toLowerCase().trim();
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    @Override
    public String toString() {
        return capitalize(name) + " | " + phoneNumber + " | " + capitalize(category) ;
    }
}
