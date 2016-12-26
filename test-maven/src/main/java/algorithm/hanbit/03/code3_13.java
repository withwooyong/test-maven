#include <stdio.h>

#define MAX 100

// ť ����
int Queue[MAX];
int Front, Rear;

void InitializeQueue(void);  // ť �ʱ�ȭ �Լ�
void Put(int);  // �������� ����
int Get(void);  // �������� ����
void DisplayQueue(void);  // ť�� ������

// ť �ʱ�ȭ �Լ�
void InitializeQueue(void)
{
	Front = Rear = 0;
}

void Put(int num)
{
	Queue[Rear++] = num;

	if (Rear >= MAX)
		Rear = 0;
}

int Get(void)
{
	int ret;
	ret = Queue[Front++];

	if (Front >= MAX)
		Front = 0;

	return ret;
}

void DisplayQueue(void)
{
	int i;
	printf("Front -> ");

	for (i = Front; i < Rear; i++)
		printf("%2d -> ", Queue[i]);

	printf("Rear");
}

void main()
{
	int ret;
	InitializeQueue();

	Put(1);
	Put(3);
	Put(10);
	Put(20);
	Put(12);

	printf("�ټ� ���� Put() �Լ� ȣ�� �� ���\n");
	DisplayQueue();

	ret = Get();
	ret = Get();
	ret = Get();

	printf("\n�� ���� Get() �Լ� ȣ�� �� ���\n");
	DisplayQueue();

	printf("\n�� ���� Get() �Լ� ȣ�� �� ���\n");

	ret = Get();
	ret = Get();
	DisplayQueue();
}