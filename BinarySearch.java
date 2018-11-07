// Java implementation of iterative Binary Search 
class BinarySearch {
    // Returns index of x if it is present in arr[],
    // else return -1
    int binarySearch(int arr[], int x) {
        System.out.println("Mencari " + x);
        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;

            cetakJejak(arr, m);

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x)
                l = m + 1;

            // If x is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    static void cetakJejak(int[] arr, int m) {
        for (int i : arr) {
            System.out.print(i + " ");
        }

        System.out.println();
        int n = 0;

        for (int i : arr) {
            for (int j = 0; j < String.valueOf(i).length(); j++) {
                if (j == 0 && n == m)
                    System.out.print("^");
                else
                    System.out.print(" ");
            }
            System.out.print(" ");
            n++;
        }

        System.out.println();
    }

    static int[] generateArray(int totalElement) {
        int[] arr = new int[totalElement];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    // Driver method to test above
    public static void main(String args[]) {
        BinarySearch ob = new BinarySearch();
        int arr[] = generateArray(20);
        int result = ob.binarySearch(arr, 11);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at " + "index " + result);
    }
}
