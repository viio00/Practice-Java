package practice;

public class GenericMethods {
    public static void main(String[] args) {
        //Use wrapper types (Generics does not work for data types)
        Integer[] numbers = {2,4,6,8,10};
        Character[] characters = {'d', 'e', 'f', 'y', 'x'};
        String[] strs = {"this", "is", "a", "string"};

        printMe(numbers);
        printMe(characters);
        printMe(strs);
    }

    //Generic method: allows an operation to accept any data type
    static <T> void printMe(T[] input) {
        for (T output: input) 
        System.out.print(output + " ");
        System.out.println();
    }
    //If we have both generic and specific overload method, the latter will be called
    static void printMe(Character[] c) {
        System.out.print("Specific overload method: ");
        for (Character x : c)
        System.out.print(x + " ");
        System.out.println();
    }

}
