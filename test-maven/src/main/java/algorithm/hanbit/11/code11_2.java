#include <stdio.h>
#define INPUT_FILE "input.txt"
#define OUTPUT_FILE "output.txt"
#define MAX 100
#define false 0
#define true (!false)

typedef int bool;

int T, N;
int DNA[2][MAX];
int split_n;
FILE *inf, *outf;

void input_data()
{
	int i;
	fscanf(inf, "%d\n", &N);

	for (i = 0; i < N; i++)
		fscanf(inf, "%d", &DNA[0][i]);
}

void solve()
{
	int flag = 0;
	int i, j;
	bool NotComplete = true;
	split_n = -1;

	while (NotComplete) {
		DNA[1 - flag][N - 2] = DNA[flag][N - 1];

		for (i = N - 4; i >= 0; i -= 2)
			DNA[1 - flag][i] = DNA[flag][i + 1] - DNA[1 - flag][i + 2];

		j = i + 2;
		j = 1 - j;

		DNA[1 - flag][j] = DNA[flag][0] - DNA[1 - flag][1 - j];

		for (i = j + 2; i < N; i += 2)
			DNA[1 - flag][i] = DNA[flag][i - 1] - DNA[1 - flag][i - 2];

		split_n++;
		flag = 1 - flag;

		for (i = 0; i < N; i++) {
			fprintf(outf, "%2d", DNA[flag][i]);

			if (DNA[flag][i] < 0)
				NotComplete = false;
		}

		fprintf(outf, "\n");
	}

	if (split_n == 0)
		split_n = -1;
}

void output_result()
{
	fprintf(outf, "================================\n");
	fprintf(outf, "%dȸ ����\n\n", split_n);
}

void main()
{
	int i;
	inf = fopen(INPUT_FILE, "r");
	outf = fopen(OUTPUT_FILE, "w");
	fscanf(inf, "%d\n", &T);
	for (i = 0; i < T; i++) {
		input_data();
		solve();
		output_result();
	}

	fclose(inf);
	fclose(outf);
}