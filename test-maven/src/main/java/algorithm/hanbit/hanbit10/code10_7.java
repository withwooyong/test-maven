#include <stdio.h>

void main()
{
	int i, num, count = 0;
	long sum = 0;

	printf("1���� 1000 ���̿��� ������ ���� ����� �� ����, ����� ���� ���ΰ�?\n");
	printf("1���� 1000 ������ �� �߿��� �ϳ��� �Է��ϼ��� ==> ");
	scanf("%d", &num);

	for (i = 1; i <= 1000; i++) {
		if (i % num == 0) {
			sum += i;
			count++;
		}
	}

	printf("1���� 1000 ���� %d�� ����� ���� : %d, ����� �� : %ld\n", num, count, sum);
}