#include "node.h"

// �ܺ� �Լ� ����
extern void InitializeStack(void);
extern void Push(NODE *);
extern NODE *Pop(void);
extern int IsStackEmpty(void);

// ���� �Լ� ����
void InitializeTree(void);
void MakeTree(void);
void Recursive_Traverse(NODE *);
void Stack_Traverse(NODE *);
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

// ��� ȣ���� ����� Ʈ���� ��ȸ �˰���
void Recursive_Traverse(NODE *ptrNode)
{
	if (ptrNode != EndNode) {
		Recursive_Traverse(ptrNode->Left);
		Visit(ptrNode);
		Recursive_Traverse(ptrNode->Right);
	}
}

// ������ ����� Ʈ���� ��ȸ �˰���
void Stack_Traverse(NODE *ptrNode)
{
	int Finish = 0;
	do {
		while (ptrNode != EndNode) {
			Push(ptrNode);
			ptrNode = ptrNode->Left;
		}

		if (!IsStackEmpty()) {
			ptrNode = Pop();
			Visit(ptrNode);
			ptrNode = ptrNode->Right;
		}

		else
			Finish = 1;
	} while (!Finish);
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
	printf("��� ȣ���� ����� ���� ��ȸ : ");

	Recursive_Traverse(HeadNode->Left);  // ��� ȣ��� Ʈ�� ��ȯ
	printf("\n������ ����� ���� ��ȸ : ");

	Stack_Traverse(HeadNode->Left);
}