#include "node.h"

// �ܺ� �Լ� ����
extern void InitializeQueue(void);
extern void Put(NODE *);
extern NODE *Get(void);
extern int IsQueueEmpty(void);

// ���� �Լ� ����
void InitializeTree(void);
void MakeTree(void);
void Level_Traverse(NODE *);
void Visit(NODE *);

// ���� ���� ����
NODE *Parent, *LeftChild, *RightChild;
NODE *HeadNode, *EndNode;

// Ʈ���� �ʱ�ȭ
void InitializeTree(void)
{
	HeadNode = (NODE *)malloc(sizeof(NODE));
	EndNode = (NODE *)malloc(sizeof(NODE));

	HeadNode->Left = EndNode;
	HeadNode->Right = EndNode;

	EndNode->Left = EndNode;
	EndNode->Right = EndNode;
}

// Ʈ���� �ʱ� ����
void MakeTree(void)
{
	Parent = (NODE *)malloc(sizeof(NODE));
	Parent->Data = 'A';

	LeftChild = (NODE *)malloc(sizeof(NODE));
	LeftChild->Data = 'B';

	RightChild = (NODE *)malloc(sizeof(NODE));
	RightChild->Data = 'C';

	Parent->Left = LeftChild;
	Parent->Right = RightChild;

	HeadNode->Left = Parent;
	HeadNode->Right = Parent;

	Parent = Parent->Left;

	LeftChild = (NODE *)malloc(sizeof(NODE));
	LeftChild->Data = 'D';
	LeftChild->Left = EndNode;
	LeftChild->Right = EndNode;

	RightChild = (NODE *)malloc(sizeof(NODE));
	RightChild->Data = 'E';
	RightChild->Left = EndNode;
	RightChild->Right = EndNode;

	Parent->Left = LeftChild;
	Parent->Right = RightChild;
	Parent = HeadNode->Right->Right;

	LeftChild = (NODE *)malloc(sizeof(NODE));
	LeftChild->Data = 'F';
	LeftChild->Left = EndNode;
	LeftChild->Right = EndNode;

	RightChild = (NODE *)malloc(sizeof(NODE));
	RightChild->Data = 'G';
	RightChild->Left = EndNode;
	RightChild->Right = EndNode;

	Parent->Left = LeftChild;
	Parent->Right = RightChild;
}

// ť�� �̿��� Ʈ���� ��ȸ �˰���
void Level_Traverse(NODE *ptrNode)
{
	Put(ptrNode);
	while (!IsQueueEmpty()) {
		ptrNode = Get();
		Visit(ptrNode);

		if (ptrNode->Left != EndNode)
			Put(ptrNode->Left);

		if (ptrNode->Right != EndNode)
			Put(ptrNode->Right);
	}
}

void Visit(NODE *ptrNode)
{
	printf("%2c -> ", ptrNode->Data);
}

void main()
{
	InitializeQueue();  // ť�� �ʱ�ȭ
	InitializeTree();  // Ʈ���� �ʱ�ȭ

	MakeTree();  // Ʈ���� ����
	printf("ť�� ����� �ܰ� ��ȸ : ");

	Level_Traverse(HeadNode->Left);
}