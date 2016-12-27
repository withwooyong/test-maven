#include <stdio.h>
#define max(a, b) ((a > b) ? a : b)
#define MAX_VALUE 1000

int Solutions[MAX_VALUE][MAX_VALUE];

int main(void) {
	int tc, testCases, i, j;
	int sizeofTriangle;

	freopen("input.txt", "r", stdin);
	setbuf(stdout, NULL);

	// �׽�Ʈ ���̽��� �Է¹޴´�
	scanf("%d", &testCases);

	for (tc = 0; tc < testCases; tc++) {

		// ��ü ũ��(������ ����)�� ���Ѵ�
		scanf("%d", &sizeofTriangle);

		// �������� �ִ� ��� ������ �Է¹޴´� 
		for (i = 0; i < sizeofTriangle; i++) {
			for (j = 0; j <= i; j++) {
				scanf("%d", &Solutions[i][j]);
				printf("%3d", Solutions[i][j]);
			}

			printf("\n");
		}

		// �������� �Ʒ� �κк��� �ִ��� ������ ���Ѵ�
		// �� ������ ������ �� �� ���������� �ݺ��Ѵ�
		for (i = sizeofTriangle - 1; i >= 0; i--) {
			for (j = 0; j <= i; j++) {
				Solutions[i][j] += max(Solutions[i + 1][j], Solutions[i + 1][j + 1]);
				printf("Solutions[%d][%d] : %d\n", i, j, Solutions[i][j]);
			}
		}

		printf("%d\n", Solutions[0][0]);
	}
	return 0;
}