#include <stdio.h>

int m, n;

int a[21][301];
int t[21][301] = { 0 };
int Benefit[21][301];

void input()
{
	freopen("input.txt", "r", stdin);
	scanf("%d", &m);  // ������ �ݾ��� �Է¹޴´�
	scanf("%d", &n);  // ������ ����� ���� �Է¹޴´�
	int z;

	for (int i = 1; i <= m; i++) {  // 1���� ������ �ݾ� m���� �о�´�
		scanf("%d", &z);  // ���� �ݾ��� z�� �о�´�

		for (int j = 0; j < n; j++)
			// 1���� j ��° ������� z �ݾ��� �������� ��
			// ������ �迭 a[j][z]�� �����Ѵ�
			scanf("%d", &a[j][z]);
	}
}

void process()
{
	int i, j, k;

	for (i = 1; i <= n; i++) {
		for (j = 0; j <= m; j++) {
			for (k = 0; k <= j; k++) {
				// a[i][k]�� ������ �߰��� ������ ���� ���� t[i][j]���� ū ���
				if (t[i - 1][j - k] + a[i][k] > t[i][j]) {
					t[i][j] = t[i - 1][j - k] + a[i][k];
					Benefit[i][j] = k;
				}
			}
		}
	}
}

void output()
{
	int g[21];
	int i, j;

	j = m;

	for (i = n; i >= 0; i--) {
		g[i] = Benefit[i][j];
		j -= g[i];
	}

	for (i = 0; i < n; i++)
		printf("%d ", g[i]);

	printf("\n");
	printf("%d\n", t[n][m]);
}

void main()
{
	input();
	process();
	output();
}