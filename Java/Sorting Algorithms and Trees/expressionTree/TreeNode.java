package expressionTree;

/**
 * template of a generic node of a binary tree
 * @author Gavin Bosman
 * @author Mekael Wasti
 */
public interface TreeNode {

    public abstract void print(int depth);
    public abstract double evaluate();
}
