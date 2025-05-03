package practice.FileHandling;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class OutputFile {
    public static void main(String[] args) {
        File sampleTxt = new File("app/src/main/java/practice/FileHandling/SampleFile.txt");
        File sampleOutput;
        Scanner file;
        
        try {
            file = new Scanner(sampleTxt);
            sampleOutput = new File("app/src/main/java/practice/FileHandling/SampleOutputText.txt");

            sampleOutput.createNewFile();

            int count = 0;
            String data = null;
            String dataOp = null;
            String len = "length: " + sampleTxt.length();
            String path = "\npath: " + sampleTxt.getPath();
            System.out.println("SampleText file:");

            while (file.hasNext()){
                data = file.nextLine();
                for (String s : data.split(" ")) {
                    count++;
                }
                System.out.println(data);
            }

            String words = "\nNumber of words: " + count;

            try {
                FileWriter myWrtr = new FileWriter(sampleOutput);
                String newTxt = len + path + words;
                myWrtr.write(newTxt);
                myWrtr.close();

                Scanner file2 = new Scanner(sampleOutput);

                System.out.println("SampleOutputTextfile: ");
                while(file2.hasNext()){
                    dataOp = file2.nextLine();
                    System.out.println(dataOp);
                }
            } catch (IOException e) {
                System.out.println("An error occured.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println();

        try {
            FileWriter newTxt = new FileWriter(sampleTxt, true);
            String textData = "\nThis little light of mine, I'm gonna let it shine";
            newTxt.write(textData);
            newTxt.close();
            file = new Scanner(sampleTxt);

            while (file.hasNext()){
                String data = file.nextLine();
                System.out.println(data);
            }

        } catch (IOException e) {
            System.out.println("An error occured.");
        }
    }
}
