#include <stdio.h>

void main(void)
{
	int data = 10;    // int �ڷ��� ���� data�� ����� �ʱ�ȭ
	int *ptr;         // int �ڷ��� ������ ���� ptr ����

	// ���� data�� �޸� �ּ� ���� ���� �� ���
	printf("data�� �ּ� : 0x%p, data�� �� : %d \n", &data, data);
	ptr = &data;

	// ������ ���� ptr�� �޸� �ּ� ���� ���� �� ���
	printf("ptr�� �ּ� : 0x%p, ptr�� �� : 0x%p \n", &ptr, ptr);
}