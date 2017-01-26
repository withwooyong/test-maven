package algorithm.baekjoon;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task3 {

	private static Logger log = LoggerFactory.getLogger(Task2.class);
	
	/*
	 * People are waiting for an elevator in a hotel.
The elevator has limited capacity and you would like to analyse its movement.
The hotel has floors numbered from 0(ground floor) to M.
The elevator has a maximum capacity of X people and a weight limit of Y.
There are N people gathered at the ground floor, standing in a queue for the elevator.
You are given every person's weight A[K] and target floor B[K]. (That is, A[0] and B[0] represent the first person in the queue.)

People continue to enter the elevator, in the order of their position in.
the queue(and push the buttons for their target floors), for as long as there is room for them. 
(The queue order cannot be changed even if thereis room in the elevator for a particular person from the middle of the queue.)
Then elevator goes up and stops at every selected floor, and finally returns to the ground floor.
This process is repeated until there are no more people in the queue.
The goal is to count the total number of times that the elevator stops.

For example, consider a hotel with floors numbered from 0 to M = 5, with an elevator with a maximum capacity of X = 2 people and a weight limit of Y = 200.
the weights A and target floors B are:
A[0] = 60    B[0] = 2
A[1] = 80    B[1] = 3
A[2] = 40    B[2] = 5

The elevator will take the first two passengers together, stop at the 2nd and 3rd floors, then return to the ground floor.
Then, it will take the last passenger, stop at the 5th floor and return to the ground floor.
In total, the elevator will stop five times.
Note that this number includes the last stop at the ground floor.

Write a function:
class Solution { public int solution(int[] A, int[] B, int M, int X, int Y); }
that, given zero-indexed arrays A and B consisting of N integers, and numbers X, Y and M as described above, returns the total number of times the elevator stops.

For example, given the above data, the function should return 5, as explained above.

For example, given M = 3, X = 5, Y = 200 and the following arrays:
A[0] = 40    B[0] = 3
A[1] = 40    B[1] = 3 
A[2] = 100   B[2] = 2
A[3] = 80    B[3] = 2
A[4] = 20    B[4] = 3

the function should return 6, as the elevator will move in two stages:
with the first three people and then with the two remaining people.

 
	 */
	public static void main(String[] args) {
		int[] A = { 40, 40, 100, 80, 20 };
		int[] B = { 3, 3, 2, 2, 3 };
		int M = 3;
		int X = 5;
		int Y = 200;
		
		log.debug("{}", solution(A, B, M, X, Y));
	}
	/*
	 * 
	 * A weight of people
	 * B floors they need to get down
	 * M total floors in the building
	 * X Max people to carry at a time
	 * Y max weight to carry at a time
	 */
	// 체중 A[K]
	// 목표 층 B[K]
	// 수용 인원 X 
	// 중량 제한 Y
	public static int solution(int[] A, int[] B, int M, int X, int Y) {
		int totalStops = 0;
		long totalWeightPerRound = 0;
		int maxPersonsCount = 0;
		List<Integer> lstFloors = new ArrayList<Integer>();
		int currPerson = 0;
		boolean startLift = false;

		while (currPerson < A.length) {
			// 수용 인원은 X 명이고 중량 제한은 Y
			if ((totalWeightPerRound + A[currPerson]) <= Y && (maxPersonsCount + 1) <= X) {
				totalWeightPerRound += A[currPerson];
				maxPersonsCount++;
				lstFloors.add(B[currPerson]);

				if (currPerson == A.length - 1) { // 마지막 수용인원
					startLift = true;
				}
				currPerson++;
			} else {
				startLift = true;
			}

			if (startLift) {
				log.debug("totalStops={} lstFloors={}", totalStops, lstFloors.toString());
				totalStops += new HashSet(lstFloors).size() + 1; // 1층으로 되돌아가는 +1
				
				lstFloors.clear();
				maxPersonsCount = 0;
				totalWeightPerRound = 0;
				startLift = false;
			}
		}
		return totalStops;
	}
}
