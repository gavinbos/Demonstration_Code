package expressionTree;

/**
 * OpNode represents a node holding an operator in a binary expression tree
 *
 * @author Gavin Bosman
 * @author Mekael Wasti
 */
public class OpNode implements TreeNode {
    TreeNode left;
    TreeNode right;
    String op;

    public OpNode(TreeNode left, String op, TreeNode right){ //constructor
        this.left = left;
        this.op = op;
        this. right = right;
    }

    /**
     * Prints out the node and its left/right child
     * @param depth represents the depth of the node within the tree
     */
    @Override
    public void print(int depth) {
        right.print(depth+1);

        String output = "";
        for(int i = 0; i < depth; i++){
            output += "\t";
        }
        output += op;
        System.out.println(output);

        left.print(depth+1);
    }

    /**
     * calculates the resulting value of the expression represented by this OpNode
     * @return the result of the operation
     */
    @Override
    public double evaluate() {
        double result = 0.0;
        switch (op){
            case "+":
                result = left.evaluate() + right.evaluate();
                break;

            case "-":
                result = left.evaluate() - right.evaluate();
                break;

            case "/":
                result = left.evaluate()/right.evaluate();
                break;

            case "*":
                result = left.evaluate()*right.evaluate();
                break;

            default:
                break;
        }
        return result;
    }
}
