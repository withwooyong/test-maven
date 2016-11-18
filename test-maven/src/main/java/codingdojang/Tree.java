package codingdojang;

/*
 * http://marobiana.tistory.com/82
 * 트리에 넣을 숫자들을 입력 받아 이진탐색트리를 만들어라.
 * 
 * 1) Root에 값이 없으면 Root에 값을 넣는다.
 * 2) Root에 값이 있으면, 입력된 값이 Root보다 크면 오른쪽에 넣고, 작으면 왼쪽에 넣는다.
 * 3) 오른쪽 혹은 왼쪽에 값이 이미 들어있으면, 입력된 값과 비교해서 크면 오른쪽에 넣고, 작으면 왼쪽에 넣는다.
 * 4) 오른쪽 혹은 왼쪽에 값이 이미 들어있으면, 입력된 값과 비교해서 크면 오른쪽에 넣고, 작으면 왼쪽에 넣는다.
....

 */
public class Tree {

	public Node root;

	public void addNode(int value) {
		if (root == null) {
			Node node = new Node();
			node.setValue(value);
			root = node; // root에 값이 없으면, root에 값을 넣는다.
		} else {
			// root가 존재할 경우, root 변경하기 위한 메소드 호출
			addNode(value, root);
		}
	}

	public void addNode(int value, Node root) {
		if (value <= root.getValue()) {
			if (root.getLeft() == null) {
				Node node = new Node();
				node.setValue(value);
				root.setLeft(node);
			} else {
				addNode(value, root.getLeft()); // 재귀
			}
		} else {
			if (root.getRight() == null) {
				Node node = new Node();
				node.setValue(value);
				root.setRight(node);
			} else {
				addNode(value, root.getRight()); // 재귀
			}
		}
	}
	
	public void preOrder(Node root) {
		if (root == null)
			return;

		System.out.print(root.getValue() + " ");
		preOrder(root.getLeft());
		preOrder(root.getRight());
	}

	public void inOrder(Node root) {
		if (root == null)
			return;

		inOrder(root.getLeft());
		System.out.print(root.getValue() + " ");
		inOrder(root.getRight());
	}

	public void postOrder(Node root) {
		if (root == null)
			return;

		postOrder(root.getLeft());
		postOrder(root.getRight());
		System.out.print(root.getValue() + " ");
	}
}
