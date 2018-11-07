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
        WormNode pointer = root;
        while (pointer != null) {
            if (pointer.left < pointer.right && pointer.target + 1 > pointer.right && pointer.left < pointer.target - 1) {
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
                pointer.left = pointer.target + 1;
                pointer.updateTarget();
                pointer = pointer.next;
            } else { // jika tidak bisa lagi mengganda, musnahkan
                WormNode theRealNext = pointer.next;
                if (pointer.prev == null) {
                    root = pointer.next;
                    pointer.next = null;
                    root.prev = null;
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

    void invade() {
        WormNode pointer = root;
        while (pointer != null) {
            System.out.print(pointer.left + "-" + pointer.target + "-" + pointer.right + " ");
            pointer = pointer.next;
        }
        System.out.println();
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