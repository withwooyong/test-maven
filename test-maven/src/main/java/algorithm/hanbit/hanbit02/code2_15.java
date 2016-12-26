#include <stdio.h>

void main()
{
	char buf[6] = { 'a', 'b', 'c', 'd', 'e', 'f' };
	char *ptr = buf;
	int i = 0;
	printf("ptr�� 1�� �������Ѻ���.\n");

	for (i = 0; i < 6; i++) {
		printf("ptr : 0x%p, *ptr : %c\n", ptr, *ptr);
		ptr++;
	}

	printf("\n�ٽ� ptr�� buf�� ����Ű�� ����.\n");
	ptr = buf;

	printf("*ptr++�� ���\n");
	printf("ptr : 0x%p, *ptr : %c\n", ptr, *ptr++);

	printf("*(ptr++)�� ���\n");
	printf("ptr : 0x%p, *ptr : %c\n", ptr, *(ptr++));

	printf("--ptr�� �� �� *(ptr++)�� ���\n");
	--ptr;
	printf("ptr : 0x%p, *ptr : %c\n", ptr, *(ptr++));

	printf("(*ptr)++�� ���\n");
	(*ptr)++;
	printf("ptr : 0x%p, *ptr : %c\n", ptr, *ptr);

	printf("ptr += 1�� �� �� *ptr�� ���\n");
	ptr += 1;
	printf("ptr : 0x%p, *ptr : %c\n", ptr, *ptr);

	printf("ptr -= 2�� �� �� *ptr�� ���\n");
	ptr -= 2;
	printf("ptr : 0x%p, *ptr : %c\n", ptr, *ptr);
}