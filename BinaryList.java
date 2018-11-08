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
                if (pointer.left < pointer.right) { // hanya yang bisa mengganda
                    if (pointer.left <= pointer.target - 1) { // jika bisa membuat anak kiri
                        WormNode newBrother = new WormNode(pointer.left, pointer.target - 1);
                        if (pointer.prev == null) { // jika di ujung kiri
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
                    if (pointer.target + 1 <= pointer.right) { // jika bisa membuat anak kanan
                        pointer.left = pointer.target + 1;
                        pointer.updateTarget();
                    }
                    pointer = pointer.next;
                } else { // jika tidak bisa lagi mengganda, musnahkan
                    WormNode theRealNext = pointer.next;
                    if (pointer.prev == null) { // jika di ujung kiri
                        if (pointer.next == null) { // jika cuma 1 node
                            root = null;
                        } else {
                            root = pointer.next;
                            pointer.next = null;
                            root.prev = null;
                        }
                    } else { // jika bukan di ujung kiri
                        if (pointer.next == null) { // jika di ujung kanan
                            pointer.prev.next = null;
                            pointer.prev = null;
                        } else { // jika bukan di tepi/ujung
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
            char[] path;
            while (root != null) {
                position = new boolean[range];
                path = emptyChar(range);
                WormNode pointer = root;
                while (pointer != null) {
                    position[pointer.target] = true;
                    for (int i = pointer.getLeftChild(); i <= pointer.getRightChild(); i++) {
                        if (i == pointer.target) {
                            path[i] = '┴';
                        } else if (i == pointer.getRightChild()) {
                            path[i] = '┐';
                        } else if (i == pointer.getLeftChild()) {
                            path[i] = '┌';
                        } else {
                            path[i] = '─';
                        }
                    }
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
                System.out.println();
                for (int i = 0; i < range; i++) {
                    for (int j = 0; j < String.valueOf(i).length(); j++) {

                        if (path[i] == '┌' && j != String.valueOf(i).length() - 1 || path[i] == '┐' && j != 0) {
                            System.out.print(' ');
                        }

                        else {
                            System.out.print(path[i]);
                        }
                    }

                    if (path[i] == ' ') {
                        System.out.print(' ');
                    } else if (path[i + 1] == ' ') {
                        System.out.print(' ');
                    } else {
                        System.out.print('─');
                    }
                }
                System.out.println();
                doubling();
            }
        }
    }

    void oldWorm() {
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
                System.out.println();

                System.out.println();
                doubling();
            }
        }
    }

    char[] emptyChar(int range) {
        char[] arr = new char[range];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ' ';
        }
        return arr;
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

        int getLeftChild() {
            return left + ((target - 1) - left) / 2;
        }

        int getRightChild() {
            return (target + 1) + (right - (target + 1)) / 2;
        }
    }
}