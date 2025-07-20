package practice;

import java.util.ArrayList;
import java.util.List;

public class HashChaining {

    private int bucketCount = 0;
    private List<List<Integer>> hashTable;

    
    public  HashChaining(int bucketCount) {
       this.bucketCount = bucketCount;
        hashTable = new ArrayList<>();
        for (int i=0; i<this.bucketCount; i++) {
            hashTable.add(new ArrayList<>());
        }
    }

    private void insert(int key) {
        int index = getHashIndex(key);
        hashTable.get(index).add(key);
    }

    private void remove(int key) {
        int index = getHashIndex(key);
        hashTable.get(index).remove(Integer.valueOf(key));

    }

    private int getHashIndex(int key) {
        return key%bucketCount;
    }

    public void display() {
        for (int i=0; i<bucketCount; i++) {
            System.out.print(i);

            for(int key: hashTable.get(i)) {
                System.out.print("-->" + key);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] nums = {12, 13, 9, 10, 23, 14, 36, 28, 48};

        HashChaining ht = new HashChaining(10);

        for(int key: nums) {
            ht.insert(key);
        }
        
        System.out.println("Before removal: ");
        ht.display();

        ht.remove(28);
        System.out.println("\nAfter removal: ");
        ht.display();
    }
}
