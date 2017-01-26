package algorithm.baekjoon;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task2 {

	private static Logger log = LoggerFactory.getLogger(Task2.class);
	/*
	 * In this problem we consider binary trees, represented by pointer data structures.
A binary tree is either an empty tree or a tree(called the root)
consisting of a single integer value and two further binary trees, called the l subtree and the r subtree.
For example, the figure below shows a binary tree consisting of six nodes.
Its root contains the value 5, and the roots of its l and r subtrees have the values 3 and 10, respectively.
The r subtree of the tree containing the value 10, as well as the l and r subtrees of the nodes containing the values 1, 20 and 21, are empty trees.
5
3,      10
20, 21, 1
A binary tree can be given using a pointer data structure.
Assume that the following declarations are given:

class Tree {
    public int x;
    public Tree l;
    public Tree r;
}

An empty tree is represented by an empty pointer(denoted by null).
A non-empty tree is represented by a pointer to an object representing its root.
The attribute x holds the integer containte in the root, whereas attributes l and r hold the l and r subtrees of the binary tree, respectively

A path in a binary tree is a non-empty sequence of nodes that one can traverse by following the pointers.
The length of a path is the number of pointers it traverses.
More formally, a path of length K is a sequence of tree P[0], P[1], ...,P[K], such that tree P[l + 1] is the root of the l or r subtree of P[l], for 0 <= l < k.
For example, the sequence of nodes with values 5, 3, 21 is a path of length 2 in the tree from the above figure.
The sequence of nodes with values 10, 1, is a path of length 1.
The sequence of nodes with values 20, 2, 21 is not a valid path.
The height of a binary tree is defined as the length of the longest possible path in the tree.
In particular, a tree consisting of only one tree has height 0 and, conventionally, an empty tree has height -1.
For example, the tree shown in the above figure is of height 2.

A binary tree T is given.
A tree of tree T containing value V is described as visible if the path from the root of the tree to that tree does not contain a tree with any value exceding V.
In particular, the root is always visible and nodes with values lower than that of the root are never visible.

For example, the tree shown in the above figure has four visible nodes:
namely, those with values, 5, 10, 20 and 21. 
The tree with value 1 is not visible because there is a tree with value 10 on the path from the root to that tree.
The tree with value 3 is not visible because its value is lower than that of the root, which has value 5.

Write function:
class Solution { public int solution(Tree T); }

that, given a binary tree T consisting of N nodes, returns its number of visible nodes, 
For example, given the tree shown in the figure above, the function should return 4, as explained above.

Given tree T with the following structure:
8
2, 6
8, 7
the function should return 2, because the only visible nodes are those with value 8.

For the purpose of entering your own test cases, you can denote a tree recursively in the following way.
An empty binary tree is denoted by None.
A non-empty tree is denoted as (X, L, R), where X is the value contained in the root and L and R denote the l and r subtrees, respectively.
The trees from the above two figures can be denoted as:
(5, (3, (20, None, None), (21, None, None)), (10, (1, None, None), None))
and :
(8, (2, (8, None, None), (7, None, None)), (6, None, None))

	 */
	public static void main(String[] args) {
		// 8
		// 2, 6
		// 8, 7
		
		// 5
		// 3,      10
		// 20, 21, 1
		
//		Tree root = new Tree();
//		root.x = 8;
//		Tree leftone = new Tree();
//		leftone.x = 2;
//		Tree rightone = new Tree();
//		rightone.x = 6; 
//		
//		root.l = leftone;
//		root.r = rightone;
//		
//		Tree lefttwo = new Tree();
//		lefttwo.x = 8;
//		Tree righttwo = new Tree();
//		righttwo.x = 7;
//		
//		leftone.l = lefttwo;
//		rightone.l = righttwo;
		
		Tree root = new Tree();
		root.x = 5;
		Tree leftone = new Tree();
		leftone.x = 3;
		Tree rightone = new Tree();
		rightone.x = 10;
		root.l = leftone;
		root.r = rightone;
		
		Tree lefttwo = new Tree();
		lefttwo.x = 20;		
		
		Tree righttwo = new Tree();
		righttwo.x = 21;
		
		leftone.l = lefttwo;
		leftone.r = righttwo;
		
		Tree rightthree = new Tree();
		rightthree.x = 1;
		
		rightone.l = rightthree;
		int min = Integer.MIN_VALUE;		
		log.debug("{}", min);
		
		log.debug("{}", solution(root, min));
	}
	
	private static int solution(Tree root, int min) {
		return visible(root, min);
	}

	private static int visible(Tree tree, int beforeValue) {
	    if (tree == null) {
	        return 0;
	    }
	    int newX = Math.max(beforeValue, tree.x);
	    log.debug("newX={} tree.x={}", newX, tree.x);
	    if (tree.x >= beforeValue) {
	        return 1 + visible(tree.l, newX) + visible(tree.r, newX);
	    } else {
	        return visible(tree.l, newX) + visible(tree.r, newX);
	    }
	}
	
}
