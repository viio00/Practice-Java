package practice;

public class Search {
    static void binarySearch (int arr[], int val, int len) {
        int low = 0, high = len - 1;
        int res = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == val) res = mid;

            if (arr[mid] < val) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (res != -1) {
             System.out.println("found at index: " + res);
        } else {
            System.out.println("not found");
        }
    }

    static void linearSearch(int arr[], int val, int len) {
        int res = -1;
        for (int i = 0; i < len; i++) {
            if(arr[i] == val) {
                res = i;
                System.out.println("found at index: " + i);
            } 
        }

        if (res == -1) {
            System.out.println("not found");
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 9, 12, 33, 45, 67};
        int val1 = 45, val2 = 8, len = arr.length;

        System.out.print("Binary Search: ");
        binarySearch(arr, val1, len);
        System.out.println("========================");
        System.out.println("Linear Search: ");
        linearSearch(arr, val2, len);
    }
}
