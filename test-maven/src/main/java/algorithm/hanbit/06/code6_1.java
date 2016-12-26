#include <stdio.h>
#include <stdlib.h>
#define MAX 100
#define TRUE 1
#define FALSE 0

// �Լ� ����
void MakeRandomNumber(void);
void SelectionSort(void);
void DisplayBuffer(void);
int IsNumberExit(int, int);
int Buf[MAX];

// ������ �������� �ʱ�ȭ
void MakeRandomNumber(void)
{
	int i, Num;
	i = 1;
	srand((unsigned)time(NULL));
	Buf[0] = 100;

	while (i < MAX) {
		Num = rand() % MAX;
		if (!IsNumberExit(Num, i)) {
			Buf[i] = Num;
			i++;
		}
	}
}

void SelectionSort(void)
{
	int i, j, min, dummy;

	for (i = 0; i < MAX; i++) {
		min = i;

		for (j = i + 1; j < MAX; j++)
			if (Buf[j] < Buf[min])
				min = j;

		dummy = Buf[min];
		Buf[min] = Buf[i];
		Buf[i] = dummy;
	}
}

// �迭 Buf�� ����� ������ ���
void DisplayBuffer(void)
{
	int i;

	for (i = 0; i < MAX; i++) {
		if ((i % 10) == 0)
			printf("\n");

		printf("%4d ", Buf[i]);
	}

	printf("\n");
}

// �̹� ������ ���� �ƴ����� �˻�
int IsNumberExit(int number, int index)
{
	int i;

	for (i = 0; i < index; i++) {
		if (Buf[i] == number || number == 0)
			return TRUE;
	}

	return FALSE;
}

void main()
{
	printf("������ ������ �ʱ�ȭ \n");

	MakeRandomNumber();
	DisplayBuffer();
	printf("���� �� ������ \n");

	SelectionSort();
	DisplayBuffer();
	printf("\n");
}