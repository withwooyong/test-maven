#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void main()
{
	int num, i, data;
	srand(time(NULL));
	num = rand() % 10;
	i = 1;
	printf("0���� 9���� ���ڸ� �Է��ϼ���\n");

	while (1) {
		printf("[%2d��° ����] : ", i);
		scanf("%d", &data);

		if (data < num)
			printf("%d���� Ů�ϴ�\n", data);

		else if (data > num)
			printf("%d���� �۽��ϴ�\n", data);

		else {
			printf("���! ��Ȯ�ϴ�. %2d��° ���� ���豺��\n", i);
			break;
		}

		i++;
	}
}