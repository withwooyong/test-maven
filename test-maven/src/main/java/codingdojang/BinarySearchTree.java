package codingdojang;

/**
 * http://marobiana.tistory.com/81
 * 1. 이진트리, 이진탐색트리란?
 * 각 노드의 자식노드 수가 최대 2개까지만 존재하는 트리이다.
 * 위의 트리는 맨 밑 노드(리프노드 - Leaf Node)를 제외한 모든 각 노드가 자식노드를 2개씩 갖고있는데, 이런 트리를 완전트리 라고 한다.
 * 완전트리가 아니더라도 자식노드가 2개 이하이면 이진트리이다.
 * @author user
 *
 */
public class BinarySearchTree {

	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.addNode(24);
		tree.addNode(15);
		tree.addNode(19);
		tree.addNode(2);
		tree.addNode(28);
		tree.addNode(27);
		tree.addNode(30);
		
		System.out.print("PreOrder : ");
		tree.preOrder(tree.root);
		System.out.print("InOrder : ");
		tree.inOrder(tree.root);
		System.out.print("PostOrder : ");
		tree.postOrder(tree.root);
	}
}
