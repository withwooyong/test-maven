#include <stdio.h>

void main()
{
	int MaxLine = 5;
	int data;
	int MInteger;
	int LineNumber;

	printf("������� ���� ��� ���α׷�\n");

	for (LineNumber = 1; LineNumber <= MaxLine; LineNumber++) {
		if ((LineNumber % 2) == 0) {
			for (data = LineNumber * 5; data >= (LineNumber * 5) - 4; data--) {
				printf("%d\t", data);
			}

			printf("\n");
		}

		else {
			for (data = (5 * (LineNumber - 1)) + 1; data
				<= (5 * (LineNumber - 1)) + 5; data++) {
				printf("%d\t", data);
			}

			printf("\n");
		}
	}
}