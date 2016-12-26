#include <stdio.h>
#define MAX_SIZE 100000

int foundFlag[MAX_SIZE];
int calculatedNumber[MAX_SIZE];

int main(void)
{
	int tc, testCases, index, initNumber, powerNumber, Solution, tempNumber;
	int i;

	freopen("input.txt", "r", stdin);
	setbuf(stdout, NULL);
	scanf("%d", &testCases);

	for (tc = 0; tc < testCases; tc++) {
		Solution = 0;

		// �ʱ갪�� ���갪�� �Է¹޴´�
		scanf("%d %d", &initNumber, &powerNumber);

		calculatedNumber[Solution++] = initNumber;

		while (1) {
			tempNumber = 0;

			while (initNumber) {
				int digit = (initNumber % 10);
				initNumber = (initNumber / 10);

				// �־��� powerNumber ����ŭ ����
				if (powerNumber >= 2) {
					for (i = 2; i <= powerNumber; i++)
						digit *= digit;
				}

				tempNumber += digit;
				printf("digit : %d, tempNumber : %d\n", digit, tempNumber);
			}

			initNumber = tempNumber;

			if (foundFlag[initNumber] == 1)
				break;

			foundFlag[initNumber] = 1;
			// printf("foundFlag initNumber : %d\n", initNumber);
			calculatedNumber[Solution++] = initNumber;
			// printf("calculatedNumber[%d] : %d, initNumber : %d\n", Solution,
			// calculatedNumber[Solution], initNumber);
		}

		for (i = 0; i < Solution; i++) {
			// �������� ���� ���ڰ� �ݺ��Ǹ� for���� ���� ����
			if (initNumber == calculatedNumber[i])
				break;
		}

		printf("%d\n", i);

		// foundFlag �迭 �ʱ�ȭ
		for (i; i < Solution; i++)
			foundFlag[calculatedNumber[i]] = 0;
	}

	return 0;
}