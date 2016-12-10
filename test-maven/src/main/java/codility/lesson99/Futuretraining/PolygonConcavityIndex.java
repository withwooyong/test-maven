package codility.lesson99.Futuretraining;

/*
 * An array A of points in a 2D plane is given. These points represent a polygon: every two consecutive points describe an edge of the polygon, and there is an edge connecting the last point and the first point in the array.

A set of points in a 2D plane, whose boundary is a straight line, is called a semiplane. More precisely, any set of the form {(x, y) : ax + by ≥ c} is a semiplane. The semiplane contains its boundary.

A polygon is convex if and only if, for any edge of the polygon, all vertices belong to the semiplane whose boundary contains the edge.

For example, the polygon consisting of vertices whose Cartesian coordinates are consecutively:

  (-1, 3)   (3, 1)   (0, -1)   (-2, 1)
is convex.

The convex hull of a finite set of points in a 2D plane is the smallest convex polygon that contains all points in this set. For example, the convex hull of a set consisting of six points whose Cartesian coordinates are:

  (-1, 3)   (3, 1)   (1, 1)
  (0, -1)   (-2, 1)  (-1, 2)
is a polygon that has four vertices. When traversed clockwise, its vertices are:

  (-1, 3)   (3, 1)   (0, -1)   (-2, 1)
Assume that the following declarations are given:

class Point2D {
  public int x;
  public int y;
}
Write a function:

class Solution { public int solution(Point2D[] A); }
that, given a non-empty zero-indexed array A consisting of N elements describing a polygon, returns −1 if the polygon is convex. Otherwise, the function should return the index of any point that doesn't belong to the convex hull.

To access the coordinates of the K-th point (where 0 ≤ K < N) use the following syntax:

A[K].x and A[K].y in all languages except PHP and Perl,
$A[$K]->x and $A[$K]->y in PHP,
$A[$K]->{x} and $A[$K]->{y} in Perl.
For example, given array A such that:

  A[0].x = -1  A[0].y =  3
  A[1].x =  3  A[1].y =  1
  A[2].x =  0  A[2].y = -1
  A[3].x = -2  A[3].y =  1
the function should return −1, as explained in the example above.

Assume that:

N is an integer within the range [3..10,000];
the coordinates of each point in array A are integers within the range [−1,000,000,000..1,000,000,000];
no two edges of the polygon A intersect, other than meeting at their endpoints;
array A does not contain duplicate points.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
 */
public class PolygonConcavityIndex {

	/*
	 * Check whether a given polygon in a 2D plane is convex; if not, return the index of a vertex that doesn't belong to the convex hull.
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
