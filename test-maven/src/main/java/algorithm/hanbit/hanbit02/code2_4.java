#include <stdio.h>

void DisplayTable(void);

int Grade[5][2];

void main(void)
{
	int i;

	printf("�� �л��� ���� ������ ���� ������ �Է��ϼ���.\n");

	for (i = 0; i < 5; i++) {
		printf("%d�� �л��� ���� ���� : ", i + 1);
		scanf("%d", &Grade[i][0]);

		printf("%d�� �л��� ���� ���� : ", i + 1);
		scanf("%d", &Grade[i][1]);
	}

	printf("�Է��� �����մϴ�.\n\n");
	DisplayTable();
}

void DisplayTable(void)
{
	int i;
	int Total[2] = { 0, 0 };

	printf("�л� ��ȣ\t���� ����\t���� ����\n");

	// �� �л����� ���� ������ ���� ������ ����Ѵ�
	for (i = 0; i < 5; i++) {
		printf("\t%d\t\t%d\t\t%d\n", i + 1, Grade[i][0], Grade[i][1]);
		Total[0] += Grade[i][0];  // ���� ������ ����Ѵ�
		Total[1] += Grade[i][1];  // ���� ������ ����Ѵ�
	}

	printf("===================================================\n");
	printf("�� ��\t\t\t%d\t\t%d\n", Total[0], Total[1]);  // ���� ���
	printf("�� ��\t\t\t%d\t\t%d\n", Total[0] / 5, Total[1] / 5);  // ��� ���
}