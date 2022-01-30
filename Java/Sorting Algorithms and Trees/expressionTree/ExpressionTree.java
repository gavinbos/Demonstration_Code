package expressionTree;

/**
 * class representing the binary tree as a whole
 * @author Gavin Bosman
 * @auhtor Mekael Wasti
 */
public class ExpressionTree {
    OpNode root;
    public ExpressionTree(OpNode root){ //constructor
        this.root = root;
    }

    public double evaluate(){
        return root.evaluate();
    }

    public void print(){
        root.print(0);
    }
}
