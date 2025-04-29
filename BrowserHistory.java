package dsapractice;  

import java.util.Scanner;
import java.util.Stack;


public class BrowserHistory {

    static Stack<String> backStack = new Stack<>();
    static Stack<String> forwardStack = new Stack<>();
    static String currentPage = null;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;

        System.out.print("Enter site to visit: ");
        currentPage = sc.nextLine().toLowerCase();
        
        while (true) { 
            System.out.println("========================================================");
            System.out.println("CHOOSE ACTION: visit / back / forward / exit");
            System.out.println("========================================================");
            System.out.print("Enter: ");
            command = sc.nextLine().toLowerCase();

            switch (command) {
                case "visit":
                    System.out.println();
                    System.out.print("Enter new site: ");
                    String newPage = sc.nextLine().toLowerCase();
                    System.out.println();
                    visit(newPage);
                    break;
                case "back":
                    System.out.println();
                    back();
                    break;
                case "forward":
                    System.out.println();
                    forward();
                    break;
                case "exit":
                    System.out.println();
                    System.out.println("Exiting browser...");
                    return;
                default: 
                    System.out.println("Invalid command.");
            }
        }
    }
    public static void showCurrentPage() {
        System.out.println("Current page: " + currentPage);
    }
    public static void visit(String newPage) {
        backStack.push(currentPage);
        forwardStack.clear();
        currentPage = newPage;
        showCurrentPage();
    }
    public static void back() {
        if (backStack.isEmpty()) {
            System.out.println("No pages in back history.");
            return;
        }

        forwardStack.push(currentPage);
        currentPage = backStack.pop();
        showCurrentPage();
    }
    public static void forward() {
        if (forwardStack.isEmpty()) {
            System.out.println("No pages in forward history.");
            return;
        }
        backStack.push(currentPage);
        currentPage = forwardStack.pop();
        showCurrentPage();
    }
}