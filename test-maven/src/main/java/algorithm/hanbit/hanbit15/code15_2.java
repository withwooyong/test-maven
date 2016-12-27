#include <stdio.h>
#define MAX_NUM 100

int ticket[MAX_NUM][MAX_NUM];

int main(void)
{
	int tc, testCases;
	int num_Tickets;
	int num_Employees;
	int num_Problems;

	freopen("input.txt", "r", stdin);
	setbuf(stdout, NULL);
	scanf("%d", &testCases);

	for (tc = 0; tc < testCases; tc++) {
		int i, j, k;
		int Solutions[MAX_NUM] = { 0, };

		// �� ��, ���� ��, ���� ���� �Է¹���
		scanf("%d %d %d", &num_Tickets, &num_Employees, &num_Problems);

		// ���� ���� ���� ���� �°� �����͸� �Է¹���
		for (i = 0; i < num_Problems; i++) {
			for (j = 0; j < num_Tickets; j++) {
				scanf("%d", &ticket[i][j]);
				printf("%d", ticket[i][j]);
			}

			printf("\n");
		}

		printf("\n");

		for (i = 0; i < num_Problems; i++) {
			k = num_Employees;
			Solutions[i] = 1;

			for (j = 0; j < num_Tickets; j++) {
				if ((j < k) && (ticket[i][j] > k)) {
					Solutions[i] = 0;
					j = num_Tickets + 1;
					printf("i, j, k : %d, %d, %d\n", i, j, k);
				}

				k++;
			}
		}

		printf("Solutions : ");

		for (i = 0; i < num_Problems; i++) {
			printf("%d", Solutions[i]);
		}

		printf("\n\n");
	}

	return 0;
}