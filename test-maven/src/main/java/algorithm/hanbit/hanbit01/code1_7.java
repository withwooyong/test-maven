#include <stdio.h>

int Add(void);
int Subtract(void);

// ���� ������ ����� ������ ���� ������ �����ߴ�
int Num_A, Num_B;

int ret;

int Add()
{
	// ���� ������ ����Ͽ� �޸𸮰� ����ȴ�
	return Num_A + Num_B;
}

int Subtract()
{
	// ���� ������ ����Ͽ� �޸𸮰� ����ȴ�
	return Num_A - Num_B;
}

int main(int argc, char *argv[])
{
	Num_A = 100;
	Num_B = 90;

	ret = Add();

	// ... �߰� ���� ...
	ret = Subtract();
	// ... �߰� ���� ...
}