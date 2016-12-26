#include<stdio.h>

#define MAX_POCKETS 32000

int eachPocket[MAX_POCKETS];
int num_Moves, num_Pockets;

int main()
{
	int testCases, p, totalBeans, averBeans;
	int i, j;

	freopen("input.txt", "r", stdin);
	scanf("%d", &testCases);

	for (i = 0; i < testCases; i++) {
		num_Moves = 0;
		totalBeans = 0;

		// �ָӴ� ������ �Է¹޴´� 
		scanf("%d", &num_Pockets);

		// �� �ָӴϿ� ��� �ִ� ���� ������ �Է¹ް� ��ü ���� ������ ���Ѵ�
		for (j = 0; j < num_Pockets; j++) {
			scanf("%d", &eachPocket[j]);
			totalBeans = totalBeans + eachPocket[j];
		}

		// �ָӴ� ������ ��ü ���� ������ 1���� ���̳��� ���
		if ((totalBeans % num_Pockets) > num_Pockets) {
			num_Moves = -1;
		}

		else {
			// �� �ָӴ��� ��� ���� ������ ���Ѵ� 
			averBeans = totalBeans / num_Pockets;

			// �� �ָӴϿ� ��� ���� ������ ���̸� ���Ѵ�
			for (j = 0; j < num_Pockets; j++) {
				if (eachPocket[j] < averBeans) {
					num_Moves = num_Moves + (averBeans - eachPocket[j]);
					printf("eachPocket[%d]�� �̵� Ƚ�� : %d\n", j, (averBeans - eachPocket[j]));
				}
			}
		}

		printf("���� �̵� Ƚ�� : %d\n", num_Moves);
	}

	return 0;
}