#include <stdio.h>
#define MAX 5

void main()
{
	int Scores[MAX];
	double total, aver;
	int i;

	printf("5���� ������ �Է����ּ���.\n");

	for (i = 0; i < MAX; i++) {
		printf("���� --> ");
		scanf("%d", &Scores[i]);
	}

	total = 0;

	for (i = 0; i < MAX; i++) {
		total += Scores[i];
	}

	aver = total / MAX;
	printf(" ���� : %f ��� : %.2f\n", total, aver);
}