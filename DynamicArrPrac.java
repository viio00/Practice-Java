package practice;

public class DynamicArrPrac {
    public static void main(String[] args){
        BuildDynamicArray arr = new BuildDynamicArray();
        
        arr.add(10);
        arr.add(20);
        arr.add(30); //this will call resize()
        arr.add(20);
        arr.add(10); //this will call resize()

        System.out.print("List: ");
        for (int i=0; i<arr.getSize();i++) {
            System.out.print(arr.getValue(i) + " ");
        }
        System.out.println("\nSize: " + arr.getSize());
    }

    static void reverseArray(){

    }

    static void rotateArray() {

    }

    static void findDuplicate() {

    }

    static void checkPalindrome() {

    }
}

//Build dynamic Array
class BuildDynamicArray {

    private int[] data; // internal array to store data
    private int size; // keep track of size

    //initialize
    public BuildDynamicArray() {
        data = new int[2];
        size = 0;
    }

    public void add(int val) {
        if (size == data.length) {
            resize();
        }

        data[size++] = val;
    }

    public void resize() {
        //double the size of array if full
        int[] newData = new int[data.length * 2];
        for (int i=0; i<data.length; i++) {
            newData[i] = data[i];
        }
        //System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public int getValue(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException();
        return data[index];
    }

    public int getSize() {
        return size;
    }
}


