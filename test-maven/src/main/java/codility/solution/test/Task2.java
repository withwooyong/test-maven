package codility.solution.test;

/*
In this porble we consider binary trees, represented by pointer data structures.

 */
public class Task2 {
	
	public static void main(String[] args) {
		
		Tree T = new Tree();
		System.out.println(solution(T));

	}
	
	public static int solution(Tree T) {
		return visible(T, Integer.MIN_VALUE);
	}
	
//	public int countVisible(Tree root) {
//	    return helper(root, Integer.MIN_VALUE);
//	}

	/*
	 * def CountVisibleNodeRec(root, maxSoFar = None):
        ''' Recursive method to count the visible nodes
        '''
        if root == None:        # Empty tree
            return 0
 
        assert isinstance(root, TreeNode)
 
        if maxSoFar == None:    maxSoFar = root.val
 
        if maxSoFar <= root.val:
            # This is a visible node
            return TreeNode.CountVisibleNodeRec(root.left, root.val) +
                   TreeNode.CountVisibleNodeRec(root.right, root.val) +
                   1
        else:
            # Current node is not visible
            return TreeNode.CountVisibleNodeRec(root.left, maxSoFar) +
                   TreeNode.CountVisibleNodeRec(root.right, maxSoFar)
 
	 */
	private static int visible(Tree T, int max) {
	    if (T == null) {
	        return 0;
	    }
	    int newMax = Math.max(max, T.x);
	    if (T.x >= newMax) {
	        return 1 + visible(T.l, newMax) + visible(T.r, newMax);
	    } else {
	        return visible(T.l, newMax) + visible(T.r, newMax);
	    }
	}

}
