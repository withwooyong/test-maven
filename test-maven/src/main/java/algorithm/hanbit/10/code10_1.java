#include <stdio.h>

void main()
{
	char key;
	int data;

	printf("10����->16���� ��ȯ ���α׷��̴�\n");
	printf("10������ 16������ �ٲٷ��� [A] Ű�� ������,\n");
	printf("16������ 10������ �ٲٷ��� [B] Ű�� ��������.\n");
	printf("a�� b�� �������� : ");

	scanf("%c", &key);
	printf("��ȯ�� ���ڸ� �Է��ϼ��� : ");

	if (key == 'a') {
		scanf("%d", &data);
		printf("10���� �� : %d --> 16���� �� : %x\n", data, data);
	}

	else if (key == 'b') {
		scanf("%x", &data);
		printf("16���� �� : %x --> 10���� �� : %d\n", data, data);
	}

	else
		printf("[A]�� [B] Ű���� ����ؾ� �Ѵ�");
}