public class AVLNode {
    public AVLNode left = null;
    public AVLNode right = null;
    public int value = 0;
    public AVLNode parent = null;

    public AVLNode insert(int newValue) {
        // perform binary-search style insertion
        if (newValue < this.value) {
            // insert the value to the left sub-tree
            if (this.left == null) {
                AVLNode newNode = new AVLNode();
                newNode.value = newValue;
                newNode.parent = this;
                this.left = newNode;
            } else {
                this.left.insert(newValue);
            }
        } else {
            // insert the value into the right sub-tree
            if (this.right == null) {
                AVLNode newNode = new AVLNode();
                newNode.value = newValue;
                newNode.parent = this;
                this.right = newNode;
            } else {
                this.right.insert(newValue);
            }
        }
        return rebalance();
    }

    public AVLNode rebalance() {
        boolean print1 = false;
        if (this.parent == null ){
            if(this.getBalance() <= -2){ //determine if left subtree is unbalanced
                if(this.left.getBalance() >= 0){ //determine if left subtree is right heavy
                    this.left = leftRotation(this.left); // if above condition is true, perform double right rotation
                    System.out.println("Double rotation, left");
                    print1 = true;
                }
                if (print1 == false){
                    System.out.println("Single rotation, right");
                }
                return rightRotation(this);

            }
            if (this.getBalance() >= 2){ //determine if right subtree is unbalanced
                if(this.right.getBalance() <= 0){ //determine if right subtree is left heavy
                    this.right = rightRotation(this.right); // if above condition is true, perform double left rotation
                    System.out.println("Double rotation, right");
                    print1 = true;
                }
                if(print1 ==  false){
                    System.out.println("Single rotation, left");
                }
                return leftRotation(this);
            }

        }
        return null;
    }

    public AVLNode leftRotation(AVLNode x){
        AVLNode temp = x.right;
        x.right = temp.left;
        temp.left = x;
        return temp;
    }

    public AVLNode rightRotation(AVLNode x){
        AVLNode temp = x.left;
        x.left = temp.right;
        temp.right = x;
        return temp;
    }

    public int getBalance() {
        int rightHeight = 0;
        if (this.right != null) {
            rightHeight = this.right.getHeight();
        }

        int leftHeight = 0;
        if (this.left != null) {
            leftHeight = this.left.getHeight();
        }

        return rightHeight - leftHeight;
    }

    public int getHeight() {
        int leftHeight = 1;
        if (left != null) {
            leftHeight = left.getHeight() + 1;
        }

        int rightHeight = 0;
        if (right != null) {
            rightHeight = right.getHeight() + 1;
        }

        return (leftHeight > rightHeight) ? leftHeight : rightHeight;
    }

    public String nodeToString(int depth) {
        String result = "";

        if (this.right != null) {
            result += this.right.nodeToString(depth + 1);
        }

        for (int i = 0; i < depth; i++) {
            result += "\t";
        }
        result += this.value + "\n";

        if (this.left != null) {
            result += this.left.nodeToString(depth + 1);
        }

        return result;
    }
}
