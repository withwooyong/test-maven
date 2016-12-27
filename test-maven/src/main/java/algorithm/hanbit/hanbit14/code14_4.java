#include <stdio.h>
#include <stdlib.h>
#define MAX_DAY 10
#define MAX_PAYDAY 10000

int daypay[MAX_DAY];
int OptimalPay[MAX_PAYDAY];

int main(int argc, char *argv[]) {
	int T, Period;
	int i, j, workingSet;

	freopen("input.txt", "r", stdin);

	// ��ü �׽�Ʈ ���̽� ��
	scanf("%d", &T);

	workingSet = 0;

	while (workingSet < T) {

		// �� �Ⱓ�� ����� �Է¹���(��: 1�ϱ�, 2�ϱ�, ...... , 10�ϱ�)
		for (i = 0; i < MAX_DAY; i++)
			scanf("%d", &daypay[i]);

		// ����ڰ� ���ϴ� �Ⱓ
		scanf("%d", &Period);

		// ����ڰ� ���ϴ� �Ⱓ��ŭ 1�ϱ��� ������ ���� �ּ� ������� ����
		for (i = 0; i < Period; i++)
			OptimalPay[i] = (i + 1) * daypay[0];

		// 2�ϱǺ��� �ִ� 10�ϱǱ��� �ݺ��� ����ڰ� ���ϴ� �Ⱓ ������
		// ����Ʈ���� �������� �� �ּ� ����� ����
		for (i = 1; i < MAX_DAY; i++) {
			int fixedDaypay = daypay[i];
			if (OptimalPay[i] > fixedDaypay)
				OptimalPay[i] = fixedDaypay;

			for (j = i + 1; j < Period; j++) {
				if (OptimalPay[j] >(fixedDaypay + OptimalPay[j - i - 1]))
					OptimalPay[j] = (fixedDaypay + OptimalPay[j - i - 1]);
			}
		}
		printf("%d", ++workingSet);
		printf("��° �ּ� ��� : ");
		printf("%d\n", OptimalPay[Period - 1]);
	}
	return 0;
}