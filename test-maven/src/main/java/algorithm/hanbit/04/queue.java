#include "node.h"
#define MAX 100

NODE *Queue[MAX];  // ť�� �迭�� ����
int Front, Rear;

void InitializeQueue(void);
void Put(NODE *);
NODE *Get(void);
int IsQueueEmpty(void);

// ť �ʱ�ȭ �Լ�
void InitializeQueue(void)
{
	Front = Rear = 0;
}

void Put(NODE *ptrNode)
{
	Queue[Rear] = ptrNode;
	Rear = (Rear++) % MAX;
}

NODE *Get(void)
{
	NODE *ptrNode;

	if (!IsQueueEmpty()) {
		ptrNode = Queue[Front];
		Front = (Front++) % MAX;

		return ptrNode;
	}

	else
		printf("Queue is Empty\n");

	return NULL;
}

int IsQueueEmpty(void)
{
	if (Rear == Front)
		return TRUE;

	else
		return FALSE;
}