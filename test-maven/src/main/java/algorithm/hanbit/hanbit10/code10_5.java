#include <stdio.h>

void main()
{
	int data1, data2;
	int i, num, max;
	printf("����� ���� �� �������� �Է��ϼ���\n");

	printf("�Է�1 : ");
	scanf("%d", &data1);

	printf("�Է�2 : ");
	scanf("%d", &data2);

	if (data1 > data2)
		num = data2;

	else
		num = data1;

	for (i = 1; i <= num; i++) {
		if (((data1 % i) == 0) && ((data2 % i) == 0))
			max = i;
	}

	printf("%d�� %d�� �ִ������� %d�̴�\n", data1, data2, max);
}