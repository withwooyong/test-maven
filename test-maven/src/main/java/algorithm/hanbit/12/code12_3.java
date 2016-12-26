#include <stdio.h>

int GCD(int, int);

// ��Ŭ���� ȣ������ �̿��� �ִ����� ���ϱ�
int GCD(int p, int q)
{
	if (q == 0)
		return p;

	return GCD(q, p % q);
}

int main()
{
	int testCases, maxNumber, Solutions;
	int i, j, k;
	int flag = 0;

	freopen("input.txt", "r", stdin);

	// �� �׽�Ʈ ������ �Է¹���
	scanf("%d", &testCases);

	for (i = 0; i < testCases; i++) {
		Solutions = 0;

		// ���Ϸ��� ��� �м��� �ִ��� �Է¹���
		scanf("%d", &maxNumber);

		for (j = 1; j <= maxNumber; j++) {
			for (k = 2; k <= maxNumber; k++) {
				// �ִ������� 1�̸� ��� �м��� ������ ����
				if (j < k && GCD(j, k) == 1) {
					if (flag == 0) {
						printf("ã�� ��� �м� : %d/%d\n", 0, 1); // 0/1 ��� �м� �߰�
						flag = 1;
					}

					printf("ã�� ��� �м� : %d/%d\n", j, k);
					Solutions++;
				}
			}
		}

		printf("ã�� ��� �м� : %d/%d\n", 1, 1); // 1/1 ��� �м� �߰�
		printf("ã�� ��� �м��� �� ���� : %d\n\n", Solutions + 2);
	}

	return 0;
}