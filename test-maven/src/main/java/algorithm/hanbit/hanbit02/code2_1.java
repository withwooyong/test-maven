#include <stdio.h>

void main()
{
	int i = 0;
	char buf[11];

	printf("�ҹ��� 11���� �Է��غ��ô�.\n");
	printf("�Է� : ");

	while (i < 11) {
		scanf("%c", &buf[i]); // Visual C++ �����Ϸ������� scanf_s() �Լ��� ����ϸ� ����
		i++;
	}

	printf("���ڸ� �Է��ϼ̱���. �빮�ڷ� ��ȯ�մϴ�.\n");

	for (i = 0; i < 11; i++)
		printf("%c", buf[i] - ('a' - 'A'));
}