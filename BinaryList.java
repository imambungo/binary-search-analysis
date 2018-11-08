class BinaryList {
    WormNode root;

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

    void binaryWorm(int totalIndex) {
        root = new WormNode(0, totalIndex - 1);
        int range = root.right + 1;
        boolean[] position;
        char[] path;
        while (root != null) {
            position = new boolean[range];
            path = emptyChar(range);
            WormNode pointer = root;
            while (pointer != null) {
                position[pointer.target] = true;
                if (pointer.canProduceLeftChild() && pointer.canProduceRightChild()) {
                    for (int i = pointer.getLeftChild(); i <= pointer.getRightChild(); i++) {
                        if (i == pointer.target) {
                            path[i] = '┴';
                        } else if (i == pointer.getRightChild() && i < range) {
                            path[i] = '┐';
                        } else if (i == pointer.getLeftChild()) {
                            path[i] = '┌';
                        } else if (i < range) {
                            path[i] = '─';
                        }
                    }
                } else if (pointer.canProduceLeftChild()) {
                    for (int i = pointer.getLeftChild(); i <= pointer.target; i++) {
                        if (i == pointer.target) {
                            path[i] = '┘';
                        } else if (i == pointer.getLeftChild()) {
                            path[i] = '┌';
                        } else if (i < range) {
                            path[i] = '─';
                        }
                    }
                } else if (pointer.canProduceRightChild()) {
                    for (int i = pointer.target; i <= pointer.getRightChild(); i++) {
                        if (i == pointer.target) {
                            path[i] = '└';
                        } else if (i == pointer.getRightChild() && i < range) {
                            path[i] = '┐';
                        } else if (i < range) {
                            path[i] = '─';
                        }
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
                    } else if (path[i] == '┴' && j != 0) {
                        System.out.print('─');
                    } else if (String.valueOf(i).length() > 1 && j != String.valueOf(i).length() - 1
                            && path[i] == '└') {
                        System.out.print(' ');
                    } else {
                        System.out.print(path[i]);
                    }
                }

                if (path[i] == ' ') {
                    System.out.print(' ');
                } else if (i < range - 1 && path[i + 1] == ' ') {
                    System.out.print(' ');
                } else if (i == range - 1) {
                    System.out.print(' ');
                } else {
                    System.out.print('─');
                }
            }
            System.out.println();
            doubling();
        }
    }

    void binaryGrow(int size) {
        for (int i = 1; i <= size; i++) {
            binaryWorm(i);
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

        boolean canProduceLeftChild() {
            return left <= target - 1;
        }

        boolean canProduceRightChild() {
            return target + 1 <= right;
        }
    }
}