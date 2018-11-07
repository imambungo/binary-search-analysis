class Main {
    public static void main(String[] args) {
        BinaryList worm = new BinaryList();
        worm.inject(28);
        worm.binaryWorm();
        System.out.println("\n");
        BinaryList worm2 = new BinaryList();
        worm2.inject(31);
        worm2.binaryWorm();
        System.out.println("\n");
        BinaryList worm3 = new BinaryList();
        worm3.inject(32);
        worm3.binaryWorm();
    }
}