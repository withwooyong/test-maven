#include <stdio.h>

void main()
{
	int data;
	int i;
	printf("����� ���� �������� �Է��ϼ���\n");
	printf("�Է� : ");
	scanf("%d", &data);

	for (i = 1; i <= data; i++) {
		if ((data % i) == 0)
			printf("%4d", i);
	}
}