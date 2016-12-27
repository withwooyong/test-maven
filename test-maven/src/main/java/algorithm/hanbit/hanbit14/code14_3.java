#include <stdio.h>
#define INIT_MAX_RATIO (-1000000)

int main(void) {
	int tc, testCases;

	freopen("input.txt", "r", stdin);
	setbuf(stdout, NULL);

	// �׽�Ʈ ���̽��� �Է� �޴´�.
	scanf("%d", &testCases);

	for (tc = 0; tc < testCases; tc++) {
		float xpos[500], heights[500];
		int numofStake, i, j, pos, Solutions;
		float ratio, maxRatio;

		// ���� ������ �Է� ���� 
		scanf("%d", &numofStake);

		// �� ������ x ��ġ�� �ش� ������ ���̸� �Է¹���
		for (i = 0; i < numofStake; i++) {
			scanf("%f", &xpos[i]);
			scanf("%f", &heights[i]);
		}

		i = 1;
		printf("%d ", i);

		while (i < numofStake) {
			maxRatio = INIT_MAX_RATIO;

			for (j = i + 1; j <= numofStake; j++) {
				// i ��° ���Ұ� j ��° ������ �������� �� ������ ����
				ratio = (heights[j - 1] - heights[i - 1]) / (xpos[j - 1] - xpos[i - 1]);

				if (ratio > maxRatio) {
					maxRatio = ratio;
					Solutions = j;
					printf("maxRatio : %f, Solutions : %d\n", maxRatio, Solutions);
				}
			}

			i = Solutions;
			printf("%d ", Solutions);

			if (i == numofStake)
				break;
		}

		printf("\n");
	}

	return 0;
}