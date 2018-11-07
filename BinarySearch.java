// Java implementation of iterative Binary Search 
class BinarySearch {
    // Returns index of x if it is present in arr[],
    // else return -1
    int binarySearch(int arr[], int x) {
        System.out.println("Mencari " + x);
        int kiri = 0, kanan = arr.length - 1;
        int m = -1;
        while (kiri <= kanan) {
            cetakPembatas(arr);
            System.out.println("m = " + m + ";   kiri = " + kiri + ";   kanan = " + kanan + ".\n");
            m = kiri + (kanan - kiri) / 2;
            System.out.println("m = kiri + (kanan - kiri) / 2");
            System.out.println("m = " + kiri + " + (" + kanan + " - " + kiri + ") / 2");
            System.out.println("m = " + kiri + " + " + (kanan - kiri) + " / 2");
            System.out.println("m = " + (kiri) + " + " + ((kanan - kiri) / 2) + " = " + m + "\n");

            cetakJejak(arr, m, kiri, kanan);

            // Check if x is present at mid
            if (arr[m] == x)
                return m;

            // If x greater, ignore left half
            if (arr[m] < x) {
                kiri = m + 1;
                System.out.println(x + " lebih besar dari " + arr[m]);
                System.out.println("kiri = m + 1");
                System.out.println("kiri = " + m + " + 1 = " + kiri + "\n");
                cetakJejak(arr, m, kiri, kanan);
            }

            // If x is smaller, ignore right half
            else {
                kanan = m - 1;
                System.out.println(x + " lebih kecil dari  " + arr[m]);
                System.out.println("kanan = m - 1");
                System.out.println("kanan = " + m + " - 1 = " + kanan + "\n");
                cetakJejak(arr, m, kiri, kanan);
            }
        }

        System.out.println("Karena nilai kiri (" + kiri + ") sudah lebih besar dari kanan (" + kanan
                + "), maka elemen yang dicari dinyatakan tidak ada.");
        // if we reach here, then element was
        // not present
        return -1;
    }

    static void cetakJejak(int[] arr, int m, int l, int r) {
        int n = 0;
        for (int i : arr) {
            for (int j = 0; j < String.valueOf(i).length(); j++) {
                if (j == 0 && n == m)
                    System.out.print("v");
                else
                    System.out.print(" ");
            }
            System.out.print(" ");
            n++;
        }
        System.out.println();

        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        n = 0;
        for (int i : arr) {
            for (int j = 0; j < String.valueOf(i).length(); j++) {
                if (j == 0 && n == l && n == r)
                    System.out.print("&");
                else if (j == 0 && n == l)
                    System.out.print("l");
                else if (j == 0 && n == r)
                    System.out.print("r");
                else
                    System.out.print(" ");
            }
            System.out.print(" ");
            n++;
        }

        System.out.println("\n");
    }

    static void cetakPembatas(int[] arr) {
        for (int i : arr) {
            for (int j = 0; j < String.valueOf(i).length(); j++) {
                System.out.print("-");
            }
            System.out.print("-");
        }
        System.out.println("\n");
    }

    static int[] generateArray(int totalElement) {
        int[] arr = new int[totalElement];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        return arr;
    }

    // Driver method to test above
    public static void main(String args[]) {
        BinarySearch ob = new BinarySearch();
        int arr[] = generateArray(20);
        int result = ob.binarySearch(arr, 61);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at " + "index " + result);
    }
}
