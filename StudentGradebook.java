package dsapractice;

import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class StudentGradebook {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
    }
    
}

//Class for stored grades (Key: Student, Value: Grade)
class Student {
    private String name;
    //String (subject), Double (Grade)
    private HashMap<String, Double> grades;
    private TreeMap<String, HashMap> gradeID;

    //constructor 
    public Student(Scanner sc){
        System.out.print("Enter Student name: ");
        name = sc.nextLine().trim();
    }

    public void storeGrade (String name, Scanner sc) {
        System.out.print("Enter Subject: ");
        String sub = sc.nextLine().trim();

        System.out.print("Enter grade: ");
        Double grade = sc.nextDouble();

        grades.put(sub, grade);

        gradeID.put(name, grades);
    }
}
