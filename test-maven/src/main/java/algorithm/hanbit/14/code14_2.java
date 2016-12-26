#include <stdio.h>

int widthFrame, maxNumber;
int Solutions;

int main(void) {
	int tc;
	int tesetCases;
	int i;
	int localSum_1, localSum_2, localSum_3;

	freopen("input.txt", "r", stdin);
	setbuf(stdout, NULL);

	// �׽�Ʈ ���̽��� �Է¹޴´�
	scanf("%d", &tesetCases);

	for (tc = 0; tc < tesetCases; tc++) {
		// Ÿ�ϸ��� �� ���� ���̸� �Է¹޴´�
		scanf("%d", &widthFrame);

		// ���� ���ڸ� �Է¹޴´�
		scanf("%d", &maxNumber);

		// �� ���� ���̰� 1�� ���
		if (widthFrame == 1)
			Solutions = 2 % maxNumber;

		// �� ���� ���̰� 2�� ���
		else if (widthFrame == 2)
			Solutions = 3 % maxNumber;

		// �� ���� ���̰� 3 �̻��� ���
		else {
			localSum_1 = 1;
			localSum_2 = 3;

			for (i = 2; i < widthFrame; i++) {
				localSum_3 = (localSum_2 + 2 * localSum_1) % maxNumber;
				localSum_1 = localSum_2;
				localSum_2 = localSum_3;
				printf("localSum_1 : %d, localSum_2 : %d, localSum_3 : %d\n",
					localSum_1, localSum_2, localSum_3);
			}

			Solutions = localSum_3;
		}

		printf("%d\n", Solutions);
	}

	return 0;
}