package codingdojang;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 문제는 다음과 같다:
	6 6
	  0   1   2   3   4   5
	 19  20  21  22  23   6
	 18  31  32  33  24   7
	 17  30  35  34  25   8
	 16  29  28  27  26   9
	 15  14  13  12  11  10
	위처럼 6 6이라는 입력을 주면 6 X 6 매트릭스에 나선형 회전을 한 값을 출력해야 한다.
 * @author user
 *
 */
public class SpiralArray {
	
	public static void main(String[] args) {		
		// 출력될 숫자
	    int output_Number=0; 

	    // 값을 두 개 입력받습니다.
	    Scanner in = new Scanner(System.in);
	    int n1 = in.nextInt();
	    int n2 = in.nextInt();

	    // 숫자가 들어갈 매트릭스를 생성합니다.
	    int[][] Matrix = new int[n1][n2];

	    // 배열 값들을 다 -1로 초기화 (2D)
	    for (int[] row: Matrix)
	        Arrays.fill(row, -1);

	    //위치 선택용 변수
	    int col=0, row=0, t_col=0, t_row=1, angle = 0;

	    // row가 늘고 col가 늘고 row가 줄고 col가 줄고 row가 늘고 col가 늘고 ... 계속 반복함
	    for(int i=0; i<(n1*n2); i++)
	    {
	        Matrix[col][row]=output_Number;
	        output_Number++;
	        row = row + t_row;
	        col = col + t_col;

	        if(row==n2){ // 1번째 코너
	            row--;
	            col++;
	            t_col = t_row;
	            t_row = 0;
	        }
	        if(col==n1){ // 2번째 코너
	            col--;
	            row--;
	            t_row = -t_col;
	            t_col = 0;
	        }
	        if(row<0){ // 3번째 코너
	            row++;
	            col--;
	            t_col = t_row;
	            t_row = 0;
	        }

	        if(Matrix[col][row] != -1)  // 다른 수가 있다면(3번째 이후 모서리 봉착)
	        {
	            angle++; 
	            if(angle == 1)// ┌자 구간에 진입했을때
	            {
	                row++;
	                col++;
	                t_col = 0;
	                t_row = 1;
	            }
	            else if(angle == 2) // ┐자 구간에 진입했을때
	            {
	                col++;
	                row--;
	                t_col = 1;
	                t_row = 0; 
	            }

	            else if(angle == 3) // ┘자 구간에 진입했을때
	            {
	                col--;
	                row--;
	                t_col = 0;
	                t_row = -1;
	            }
	            else if(angle == 4) // └자 구간에 진입했을때
	            {
	                col--;
	                row++;
	                t_col = -1;
	                t_row = 0;

	                angle = 0; // 다시 초기화
	            }
	        }
	    }

	    // 출력
	    for(int a=0; a<n1; a++){
	        for(int b=0; b<n2; b++){
	            System.out.print(Matrix[a][b] + "\t");
	        }
	        System.out.println();
	    }
	} // main
	
}
