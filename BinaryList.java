class BinaryList {
    WormNode root;

    void inject(int totalIndex) {
        if (root == null) {
            root = new WormNode(0, totalIndex - 1);
        } else {
            System.out.println("inject failed...");
        }
    }

    void doubling() {
        if (root == null) {
            System.out.println("nothing to double!");
        } else {
            WormNode pointer = root;
            while (pointer != null) {
                if (pointer.left < pointer.right) {
                    if (pointer.left <= pointer.target - 1) {
                        WormNode newBrother = new WormNode(pointer.left, pointer.target - 1);
                        if (pointer.prev == null) {
                            pointer.prev = newBrother;
                            newBrother.next = pointer;
                            root = newBrother;
                        } else {
                            newBrother.prev = pointer.prev;
                            newBrother.next = pointer;
                            newBrother.prev.next = newBrother;
                            pointer.prev = newBrother;
                        }
                    }
                    if (pointer.target + 1 <= pointer.right) {
                        pointer.left = pointer.target + 1;
                        pointer.updateTarget();
                    }
                    pointer = pointer.next;
                } else { // jika tidak bisa lagi mengganda, musnahkan
                    WormNode theRealNext = pointer.next;
                    if (pointer.prev == null) {
                        if (pointer.next == null) {
                            root = null;
                        } else {
                            root = pointer.next;
                            pointer.next = null;
                            root.prev = null;
                        }
                    } else {
                        if (pointer.next == null) {
                            pointer.prev.next = null;
                            pointer.prev = null;
                        } else {
                            pointer.prev.next = pointer.next;
                            pointer.next.prev = pointer.prev;
                            pointer.prev = null;
                            pointer.next = null;
                        }
                    }
                    pointer = theRealNext;
                }
            }
        }

    }

    void invade() {
        WormNode pointer = root;
        while (pointer != null) {
            // System.out.print(pointer.left + "-" + pointer.target + "-" + pointer.right +
            // " ");
            System.out.print(pointer.target + " ");
            pointer = pointer.next;
        }
        System.out.println();
    }

    void binaryWorm() {
        if (root == null || root.next != null) {
            System.out.println("Make sure BinaryList only have 1 node!");
        } else {
            int range = root.right + 1;
            boolean[] position;
            while (root != null) {
                position = new boolean[range];
                WormNode pointer = root;
                while (pointer != null) {
                    position[pointer.target] = true;
                    pointer = pointer.next;
                }
                for (int i = 0; i < range; i++) {
                    if (position[i]) {
                        System.out.print(i + " ");
                    } else {
                        for (int j = 0; j < String.valueOf(i).length(); j++) {
                            System.out.print(" ");
                        }
                        System.out.print(" ");
                    }
                }
                System.out.println("\n");
                doubling();
            }
        }
    }

    class WormNode {
        int left;
        int right;
        int target;
        WormNode next;
        WormNode prev;

        WormNode(int left, int right) {
            this.left = left;
            this.right = right;
            updateTarget();
        }

        void updateTarget() {
            target = left + (right - left) / 2;
        }
    }
}