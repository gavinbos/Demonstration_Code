import sorting.*;
import expressionTree.*;

public class SortAlgos_andTrees {
	public static double[] generateRandomArray(int size) {
		double[] array = new double[size];

		for (int i = 0; i < array.length; i++) {
			array[i] = Math.random() * 100.0;
		}

		return array;
	}
	public static void main(String[] args) {

		double[] array1 = generateRandomArray(100000);
		double[] array2 = array1.clone();

// Insertion Sort
		Sorter iSorter = new InsertionSorter();
		iSorter.sort(array1);
		System.out.println("Operations: " + iSorter.getOpCount());

// Merge Sort
		Sorter mSorter = new MergeSorter();
		mSorter.sort(array2);
		System.out.println("Operations: " + mSorter.getOpCount());

		// Expression tree demo 1
		// 2 + (8 * 3)
		ValueNode two = new ValueNode(2);
		ValueNode three = new ValueNode(3);
		ValueNode eight = new ValueNode(8);
		OpNode eightTimes3 = new OpNode(eight, "*", three);
		OpNode twoPlusEightTimesThree = new OpNode(two, "+", eightTimes3);
		ExpressionTree tree = new ExpressionTree(twoPlusEightTimesThree);
		System.out.println("print():");
		tree.print();
		System.out.println("evaluate():");
		System.out.println("" + tree.evaluate());

		System.out.println("");

		// Expression tree demo 2
		// (13 / 4) - (2 + (8 * 3))
		ValueNode thirteen = new ValueNode(13);
		ValueNode four = new ValueNode(4);
		OpNode thirteenOverFour = new OpNode(thirteen, "/", four);
		OpNode bigExpression = new OpNode(thirteenOverFour, "+", twoPlusEightTimesThree);
		ExpressionTree tree2 = new ExpressionTree(bigExpression);
		System.out.println("print():");
		tree2.print();
		System.out.println("evaluate():");
		System.out.println("" + tree2.evaluate());
	}
}
