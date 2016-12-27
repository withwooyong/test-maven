#include <stdio.h>

#define MAX_INVEST_MONEY 301
#define MAX_STARTUP 21

int investmentTable[MAX_INVEST_MONEY][MAX_STARTUP];
int Solutions[MAX_INVEST_MONEY];

int getMaxBenefit(int, int);

int getMaxBenefit(int p, int q)
{
	if (p > q)
		return p;

	else
		return q;
}

int main(void)
{
	int tc, testCases;
	int totalMoney, num_Startup;
	int maxBenefit;

	freopen("input.txt", "r", stdin);

	// �� �׽�Ʈ ���� �Է¹޴´�.
	scanf("%d", &testCases);

	for (tc = 0; tc < testCases; tc++) {
		int i, j, k;

		// ��ü ���� �ݾװ� ������ ��ŸƮ���� ���� �Է¹޴´�.
		scanf("%d %d", &totalMoney, &num_Startup);

		// Solutions �迭 �ʱ�ȭ
		int Solutions[MAX_INVEST_MONEY] = { 0, };

		// ���� ���̺� �Է¹ޱ�
		for (i = 0; i < totalMoney; i++)
			for (j = 0; j <= num_Startup; j++)
				scanf("%d", &investmentTable[i][j]);

		for (i = 0; i < num_Startup; i++) {
			for (j = totalMoney - 1; j >= 0; j--) {
				int maxBenefit = -1;

				for (k = 0; k < totalMoney; k++) {
					if (j - k >= 0) {
						printf("maxBenefit : %d, Solutions[%d] : %d, total : %d\n",
							maxBenefit, (j - k), Solutions[j - k], (Solutions[j - k] +
							investmentTable[k][i + 1]));
						maxBenefit = getMaxBenefit(maxBenefit, (Solutions[j - k] +
							investmentTable[k][i + 1]));
					}

					Solutions[j] = getMaxBenefit(Solutions[j], maxBenefit);
					printf("Solutions : %d, %d, %d, %d\n", Solutions[0], Solutions[1],
						Solutions[2], Solutions[3]);
				}

				printf("\n");
			}

			printf("\n");
		}

		printf("�ִ� ���� ���ͱ� : %d\n\n", Solutions[totalMoney - 1]);
	}

	return 0;
}