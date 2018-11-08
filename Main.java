class Main {
    public static void main(String[] args) {
        BinaryList worm = new BinaryList();
        worm.inject(28);
        worm.binaryWorm();
        // System.out.println("\n");
        int a = '┐';
        int b = '└';
        int c = '┴';
        int d = '┘';
        int e = '┌';
        int[] arr = { '┌', '┐', '┴', '┘', '└' };
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println(a + " " + b);
    }
}