package practice;

public class DynamicArrPrac {

    static BuildDynamicArray arr = new BuildDynamicArray();
    static BuildDynamicArray arr2 = new BuildDynamicArray();

    public static void main(String[] args){
        
        arr.add(10);
        arr.add(20);
        arr.add(30); //this will call resize()
        arr.add(40);
        arr.add(50); //this will call resize()

        System.out.print("List: ");
        for (int i=0; i<arr.getSize();i++) {
            System.out.print(arr.getValue(i) + " ");
        }
        System.out.println("\nSize: " + arr.getSize());

        int[] copy = arr.toArray();
        reverseArray(copy);

        System.out.print("Reversed Array: ");
        for (int val : copy) {
            System.out.print(val + " ");
        }

        rotateArray(2, copy);
        System.out.print("\nRotated Array: ");
         for (int val : copy) {
            System.out.print(val + " ");
        }

        System.out.println();
        System.out.println();

        arr2.add(9);
        arr2.add(4);
        arr2.add(3);
        arr2.add(4);
        arr2.add(5);

        System.out.print("List: ");
        for (int i=0; i<arr2.getSize();i++) {
            System.out.print(arr2.getValue(i) + " ");
        }

        int[] copy2 = arr2.toArray();

        System.out.print("\nDuplicated value(s): " );
        findDuplicate(copy2);

        System.out.print("is Palindrome: " + checkPalindrome(copy2));

    }

     static void reverseArray(int[] data){
        int left = 0;
        int right = arr.getSize() - 1;
        
        while (left<right) {
            int temp = arr.getValue(left);
            data[left++] = arr.getValue(right);
            data[right--] = temp;
        }

    }
        
    static void rotateArray(int distance, int[] data) {
        distance = distance % data.length;
        int[] newData = new int[data.length];

        for (int i=0; i< data.length; i++) {
            newData[i] = data[(i + distance) % data.length]; 
        }

        for (int i = 0; i < data.length; i++) {
        data[i] = newData[i];
    }
    }

    static void findDuplicate(int[] data) {
        int j = 1;
        int duplicate = 0;

        for (int i=0; i<arr2.getSize(); i++) {   
             if (data[i] == data[j]) {
                duplicate = data[i];
            }
        }

        System.out.println(duplicate);

    }

    static boolean checkPalindrome(int[] data) {
        int j = data.length -1;
        int i = 0;

        while (i < j) {
            if (data[i] != data[j]) {
                    return false;
            }
            i++;
            j--;
        }
        return true;
    }
}

//Build dynamic Array (acts like an Arraylist)
class BuildDynamicArray {

    private int[] data; 
    private int size; 

    //initialize
    public BuildDynamicArray() {
        data = new int[2];
        size = 0;
    }

    public void add(int val) {
        if (size == data.length) {
            resize(); // doubles the size 
        }

        data[size++] = val; //tracks the size
    }

    public void resize() {
        //double the size of array if full
        int[] newData = new int[data.length * 2];
        for (int i=0; i<data.length; i++) {
            newData[i] = data[i];
        }
        //System.arraycopy(data, 0, newData, 0, data.length); -- another way of doing it (copy)
        data = newData;
    }

    public int getValue(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        return data[index];
    }

    public int getSize() {
        return size;
    }

    //converting dynamic array to a normal array since there are methods with int[] parameters
    //Note: ArrayList does not hold primitive data types (unlike arrays), they hold objects(wrapper)
    public int[] toArray() {
        int[] res = new int[size];
        for (int i=0; i<size; i++) {
            res[i] = data[i];
        }
        return res;
    }

}



