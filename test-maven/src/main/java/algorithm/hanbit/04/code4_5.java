#include "node.h"

// �ܺ� �Լ� ����
extern void InitializeStack(void);
extern void Push(NODE *);
extern NODE *Pop(void);
extern int IsStackEmpty(void);

// ���� �Լ� ����
void InitializeTree(void);
void MakeTree(void);
void Traverse(NODE *);
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

void Traverse(NODE *ptrNode)
{
	Push(ptrNode);

	while (!IsStackEmpty()) {
		ptrNode = Pop();
		Visit(ptrNode);

		if (ptrNode->Right != EndNode)
			Push(ptrNode->Right);

		if (ptrNode->Left != EndNode)
			Push(ptrNode->Left);
	}
}

void Visit(NODE *ptrNode)
{
	printf("%2c -> ", ptrNode->Data);
}

void main()
{
	InitializeStack();  // ������ �ʱ�ȭ
	InitializeTree();  // Ʈ���� �ʱ�ȭ
	MakeTree();  // Ʈ���� ����
	Traverse(HeadNode->Left);  // Ʈ���� ��ȯ
}