#include <stdio.h>

typedef struct _NODE {
	int Number;
	char Name;
} NODE;

NODE Students[100];

void main()
{
	int i;

	for (i = 0; i < 100; i++) {
		if (i % 2 == 0) {
			Students[i].Number = 1000 + i;
			Students[i].Name = 'A' + i;
		}

		else
			continue;
	}

	printf("��ü ������ ũ��� ����� ������ ũ�� ��\n");
	printf("��ü ������ ũ�� : %d bytes\n", sizeof(NODE) * 100);
	printf("����� ������ ũ�� : %d bytes\n", sizeof(NODE) * 100 / 2);
}