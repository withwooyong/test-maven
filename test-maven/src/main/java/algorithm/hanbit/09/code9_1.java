#include <stdio.h>

typedef struct _NODE {
	int Number;
	char Name;
} NODE;

NODE Students[10];

void main()
{
	int i;

	for (i = 0; i < 10; i++) {
		Students[i].Number = 1000 + i;
		Students[i].Name = 'A' + i;
	}

	printf("�ټ� ��° �л��� �й��� �̸� ��� \n");
	printf("�й� : [%d]\n", Students[4].Number);
	printf("�̸� : [%c]\n", Students[4].Name);
}