#include <stdio.h>

int main(int argc, char *argv[])
{
	int i, input;
	int data[1000];

	for (i = 0; i < 1000; i++)
		data[i] = i + 1;

	printf("ã�� ���� �Է��ϼ��� => ");
	scanf("%d", &input);

	for (i = 0; i < 1000; i++) {
		if (input == data[i]) {
			printf("ã������ �ϴ� ���� �迭 data�� %d��°�� �ֱ���.\n", i + 1);
			break;
		}
	}

	return 0;
}