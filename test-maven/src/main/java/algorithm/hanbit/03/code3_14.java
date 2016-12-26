#include <stdio.h>
#include <stdlib.h>

typedef struct _NODE {
	int Data;
	struct _NODE *Next;
} NODE;

NODE *Front, *Rear;
NODE *ptrNode;

void InitializeQueue(void);
void Put(int);
int Get(void);
void DisplayQueue(void);

void InitializeQueue(void)
{
	Front = (NODE *)malloc(sizeof(NODE));
	Rear = (NODE *)malloc(sizeof(NODE));
	Front->Next = Rear;
	Rear->Next = Front;
}

void Put(int num)
{
	ptrNode = (NODE *)malloc(sizeof(NODE));
	ptrNode->Data = num;

	if (Front->Next == Rear) {
		Front->Next = ptrNode;
		ptrNode->Next = Rear;
		Rear->Next = ptrNode;
	}

	else {
		Rear->Next->Next = ptrNode;
		ptrNode->Next = Rear;
		Rear->Next = ptrNode;
	}
}

int Get(void)
{
	int ret;
	NODE *deleteNode;
	printf("\n");

	if (Front->Next == Rear)
		printf("Queue Empty\n");

	else {
		deleteNode = Front->Next;
		Front->Next = deleteNode->Next;
		ret = deleteNode->Data;
		printf("get() : %d", ret);

		free(deleteNode);
	}

	return ret;
}

void DisplayQueue(void)
{
	NODE *ptrTemp;

	if (Front->Next != Rear) {
		for (ptrTemp = Front->Next; ptrTemp->Next != Rear; ptrTemp = ptrTemp->Next) {
			printf("%d -> ", ptrTemp->Data);
		}
		printf("%d", ptrTemp->Data);
	}

	else if (Front->Next == Rear)
		printf("Queue Empty\n");
}

void main()
{
	int ret;
	InitializeQueue();
	printf("Put() �Լ��� ȣ���غ���!\n");

	Put(1);
	Put(3);
	Put(10);
	Put(20);
	Put(12);

	printf("�ټ� ���� Put() �Լ� ȣ�� �� ��� : ");
	DisplayQueue();

	ret = Get();
	ret = Get();
	ret = Get();

	printf("\n�� ���� Get() �Լ� ȣ�� �� ��� : ");
	DisplayQueue();

	ret = Get();
	ret = Get();

	printf("\n�� ���� Get() �Լ� ȣ�� �� ��� : ");
	DisplayQueue();
}