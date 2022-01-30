package expressionTree;

/**
 * ValueNode represents a node holding a value within a binary expression tree
 *
 * @author Gavin Bosman
 * @author Mekael Wasti
 */
public class ValueNode implements TreeNode{
    double value;
    public ValueNode(int value){ //constructor
        this.value = value;
    }

    /**
     * prints out the value held by this node after depth number of indents
     * @param depth represents the depth of the node within the tree
     */
    @Override
    public void print(int depth) {
        String output = "";
        for(int i = 0; i < depth; i++){
            output += "\t";
        }
        System.out.println(output + value);
    }

    /**
     * @return value held by the node
     */
    @Override
    public double evaluate() {
        return value;
    }
}
