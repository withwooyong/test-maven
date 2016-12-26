#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define MAX 100
#define TRUE 1
#define FALSE 0

void MakeRandomNumber(void);
void QuickSort(int[], int, int);
void DisplayBuffer(void);
int IsNumberExit(int, int);
int BinarySearch(int);
int Buf[MAX];
int CompareCount;

// ������ ������ �ʱ�ȭ
void MakeRandomNumber(void)
{
	int i, Num;
	i = 0;
	srand((unsigned)time(NULL));

	while (i < MAX) {
		Num = rand() % 200;

		if (!IsNumberExit(Num, i)) {
			Buf[i] = Num;
			i++;
		}
	}
}

void QuickSort(int data[], int left, int right)
{
	int num, i, j, temp;
	if (right > left) {
		num = data[right];
		i = left - 1;
		j = right;

		for (;;) {
			while (data[++i] < num);
			while (data[--j] > num);
			if (i >= j)
				break;

			temp = data[i];
			data[i] = data[j];
			data[j] = temp;
		}

		temp = data[i];
		data[i] = data[right];
		data[right] = temp;

		QuickSort(data, left, i - 1);
		QuickSort(data, i + 1, right);
	}
}

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

int IsNumberExit(int number, int index)
{
	int i;

	for (i = 0; i < index; i++) {
		if (Buf[i] == number || number == 0)
			return TRUE;
	}

	return FALSE;
}

int BinarySearch(int num)
{
	int left, right;
	int min;
	left = 0;
	right = MAX;

	while (right >= left) {
		CompareCount++;
		min = (left + right) / 2;

		if (num < Buf[min])
			right = min - 1;

		else
			left = min + 1;

		if (num == Buf[min])
			return min;
	}

	return -1;
}

void main()
{
	int ret;
	CompareCount = 0;

	MakeRandomNumber();
	QuickSort(Buf, 0, MAX - 1);
	printf("���ĵ� ������\n");

	DisplayBuffer();
	ret = BinarySearch(23);  // ���� �˻�

	if (ret == -1)  // ã���� �ϴ� �����Ͱ� ���� ���
		printf("\n23�̶�� �����Ͱ� ����\n");

	else  // �����Ͱ� �����ϴ� ���
		printf("\n23�̶�� �����Ͱ� %d��°�� �����Ѵ�\n", ret);

	printf("�� �� Ƚ���� %dȸ��.\n", CompareCount);
}