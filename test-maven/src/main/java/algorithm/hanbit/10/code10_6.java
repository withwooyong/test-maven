#include <stdio.h>

void main()
{
	int i, value_new, valuen_1, valuen_2;

	printf("�Ǻ���ġ ������ ���غ���\n");

	i = 0;
	valuen_1 = 1;
	valuen_2 = 0;

	while (i < 24) {
		value_new = valuen_1 + valuen_2;

		if (!(i % 12)) {
			printf("\n");
		}

		printf("%6d", value_new);

		valuen_2 = valuen_1;
		valuen_1 = value_new;
		i++;
	}
}