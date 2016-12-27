#include<stdio.h>
#define MAX_STEPS 3012

int value_Steps[MAX_STEPS];
int Solutions[MAX_STEPS];

int main() {
	int i, j;
	int oneJumpSteps, noJumpSteps;
	int num_Steps;
	int testCases;

	freopen("input.txt", "r", stdin);

	// �� �׽�Ʈ ������ �Է¹޴´�
	scanf("%d", &testCases);

	for (i = 0; i < testCases; i++) {

		// ��� ������ �Է¹޴´�
		scanf("%d", &num_Steps);

		// �� ����� ���� �Է¹޴´�
		for (j = 1; j <= num_Steps; j++) {
			scanf("%d", &value_Steps[j]);
		}

		// ó������ �� ��° ��ܱ��� ���� ����
		Solutions[0] = 0;
		Solutions[1] = value_Steps[1];
		Solutions[2] = value_Steps[1] + value_Steps[2];

		// �� ��° ��ܺ��� �������� �����
		for (j = 3; j <= num_Steps; j++) {
			noJumpSteps = value_Steps[j] + value_Steps[j - 1] + Solutions[j - 3];
			oneJumpSteps = value_Steps[j] + Solutions[j - 2];

			// �� ���� ��� �߿��� ū ���� ����
			if (noJumpSteps > oneJumpSteps)
				Solutions[j] = noJumpSteps;

			else
				Solutions[j] = oneJumpSteps;

			printf("Solutions[%d] : %d\n", j, Solutions[j]);
		}

		printf("%d\n", Solutions[num_Steps]);
	}

	return 0;
}