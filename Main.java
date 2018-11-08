class Main {
    public static void main(String[] args) {
        BinaryList worm = new BinaryList();
        worm.inject(38);
        worm.binaryWorm();
        // System.out.println("\n");
        int[] arr = { '┌', '┐', '┴', '┘', '└', '─'};
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}