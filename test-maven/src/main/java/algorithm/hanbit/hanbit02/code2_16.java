#include <stdio.h>

void Square(int, int *); // �����ٰ� ���������� ���ϴ� �Լ�

void Square(int lv, int *ret)
{
	if (lv == 2)
		*ret = *ret * *ret;

	else if (lv == 3)
		*ret = *ret * *ret * *ret;
}

void main()
{
	int number, level;
	printf("C ���α׷��ֿ����� ������ ��� �� - �� ��° \n");

	level = 2;
	number = 3;
	Square(level, &number); // 3�� 2��
	printf("Level : %d Return Value : %d\n", level, number);

	printf("\n");

	level = 3;
	number = 4;
	Square(level, &number); // 4�� 3��

	printf("Level : %d Return Value : %d\n", level, number);
}