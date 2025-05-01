package practice;

import java.io.File;
import java.io.FileWriter; //Input/Output
import java.io.IOException;
import java.util.Scanner;


public class FileHandlingPractice {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        File firstObj = new File("/Users/chynnacardona/VsCode/my-java-project/app/src/main/java/practice/ExampleFile.txt");
 
        try {
             //Creating a new file

            if (firstObj.createNewFile()) {
                System.out.println("File created: " + firstObj.getName());
                System.out.println(firstObj.getAbsolutePath());

                try ( //writing in a file
                    FileWriter myWrtr = new FileWriter(firstObj)) {
                    System.out.println("\nEnter File text: ");
                    String input = sc.nextLine();
                    myWrtr.write(input);
                }

            } else {
                System.out.println("File already exists: ");
                Scanner reader = new Scanner(firstObj);
                
                while (reader.hasNextLine()){
                    String data = reader.nextLine();
                    System.out.println(data);
                }
                reader.close();

                System.out.println("\nUpdate file? (y/n/clear)");
                String choice = sc.nextLine();

                if (choice.equals("y")) {
                    try ( //"true" means new text input will be appended and existing text will be untouched.
                        FileWriter myWrtr = new FileWriter(firstObj, true)) {
                        System.out.println("Update text:");
                        String input = sc.nextLine();
                        myWrtr.write("\n" + input);
                    }
                } else if (choice.equals("clear")) {
                    //append is false, file will be overwritten
                    FileWriter myWrtr = new FileWriter(firstObj);
                    System.out.println("Enter new text:");
                    String input = sc.nextLine();
                    myWrtr.write(input);
                    myWrtr.close();
                } 
            } 
        } catch (IOException e) {
            System.err.println("An error occured.");
        }

    }
}