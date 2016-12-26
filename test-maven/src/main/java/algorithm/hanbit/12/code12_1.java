#include <stdio.h>

int Cnt;

void hanoi(int n, int a, int b)
{
	int temp;

	if (n == 1) {
		printf("���� %d�� %d���� %d�� �̵��Ѵ�\n", n, a, b);
	}
	else {
		temp = 6 - a - b;
		hanoi(n - 1, a, temp);
		printf("���� %d�� %d���� %d�� �̵��Ѵ�\n", n, a, b);
		hanoi(n - 1, temp, b);
	}

	Cnt++;
}

int main()
{
	int n;
	printf("�̵��� ���� ���� �Է��ϼ���: ");
	scanf("%d", &n);

	hanoi(n, 1, 2);
	printf("������ �� �̵� Ƚ�� : %dȸ\n", Cnt);
	return 0;
}