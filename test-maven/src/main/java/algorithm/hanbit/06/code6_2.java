#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#define MAX 100
#define TRUE 1
#define FALSE 0

void MakeRandomNumber(void);
void InsertionSort(void);
void DisplayBuffer(void);
int IsNumberExit(int, int);
int Buf[MAX];

// ������ ������ �ʱ�ȭ
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

void InsertionSort(void)
{
	int i, j, dummy;
	for (i = 0; i < MAX; i++) {
		dummy = Buf[i];
		j = i;

		while (Buf[j - 1] > dummy && j > 0) {
			Buf[j] = Buf[j - 1];
			j--;
		}

		Buf[j] = dummy;
	}
}

void DisplayBuffer(void)
{
	int i;
	for (i = 0; i < MAX; i++) {
		if ((i % 10) == 0)
			printf("\n");

		printf("%4d", Buf[i]);
	}

	printf("\n");
}

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
	printf("������ ������ �ʱ�ȭ\n");

	MakeRandomNumber();
	DisplayBuffer();
	printf("���� �� ������\n");

	InsertionSort();
	DisplayBuffer();
	printf("\n");
}