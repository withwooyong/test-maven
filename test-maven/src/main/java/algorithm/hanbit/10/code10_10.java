#include <stdio.h>

int gcd(int, int);

int gcd(int x, int y)
{
	static int ExecNum = 1;
	printf("%dȸ �����Ѵ�\n", ExecNum++);

	if (y != 0) {
		printf("x : %d, y : %d\n", x, y);
		return gcd(y, x % y);
	}

	else
		return x;
}

void main()
{
	int result;
	int num1, num2;
	printf("�ִ������� ���ϴ� ���α׷�\n");
	printf("2���� ���� �Է��ϼ���\n");
	printf("�Է� => ");
	scanf("%d %d", &num1, &num2);

	result = gcd(num1, num2);
	printf("%d�� %d�� �ִ������� : %d\n", num1, num2, result);
}