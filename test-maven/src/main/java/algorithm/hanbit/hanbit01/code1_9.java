#include <stdio.h>

int main(int argc, char *argv[])
{
	int Sum = 0;
	int i;

	for (i = 1; i <= 100; i++) {
		Sum = Sum + i;
	}

	printf("1���� 100������ �� : %d\n", Sum);

	return 0;
}