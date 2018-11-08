class Main {
    public static void main(String[] args) {
        int size = 35;
        BinaryList worm = new BinaryList();
        worm.inject(size);
        worm.oldWorm();
        BinaryList worm2 = new BinaryList();
        worm2.inject(size);
        worm2.binaryWorm();


        int[] arr = { '┌', '┐', '┴', '┘', '└', '─' };
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}