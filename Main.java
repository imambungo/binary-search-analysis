class Main {
    public static void main(String[] args) {
        BinaryList worm = new BinaryList();
        worm.inject(38);
        worm.oldWorm();
        BinaryList worm2 = new BinaryList();
        worm2.inject(38);
        worm2.binaryWorm();
        // System.out.println("\n");
        int[] arr = { '┌', '┐', '┴', '┘', '└', '─' };
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}