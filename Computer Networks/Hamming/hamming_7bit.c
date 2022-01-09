// Program in C/ C++ for hamming code generation for error detection/correction for 4bit data

#include<stdio.h>

int main()
{
    int data[50],datatrac[50],c1,c2,c3,c4,c;

    printf("Enter a 7 bit hamming code line by line:\n");

	scanf("%d",&data[0]);
	scanf("%d",&data[1]);
	scanf("%d",&data[2]);
	scanf("%d",&data[4]);
	scanf("%d",&data[5]);
	scanf("%d",&data[6]);
	scanf("%d",&data[8]);

	//Calculating even parity
	
	data[10] = data[0]^data[2]^data[4]^data[6]^data[8];
	data[9] = data[0]^data[1]^data[4]^data[5]^data[8];
	data[7] = data[4]^data[5]^data[6];
	data[3] = data[0]^data[1]^data[2];

	printf("\nFinal code word transmited is:");
	for(int i=0;i<11;i++)
		printf("%d",data[i]);


	printf("\n\nEnter received data bits one by one\n");
	for(int i=0;i<11;i++)
		scanf("%d",&datatrac[i]);


	c1 = datatrac[10]^datatrac[0]^datatrac[2]^datatrac[4]^datatrac[6]^datatrac[8];
	c2 = datatrac[9]^datatrac[0]^datatrac[1]^datatrac[4]^datatrac[5]^datatrac[8];
	c3 = datatrac[7]^datatrac[4]^datatrac[5]^datatrac[6];
	c4 = datatrac[3]^datatrac[0]^datatrac[1]^datatrac[2];
	c = c4*8+c3*4+c2*2+c1;
	
	if(c == 0)
	{
		printf("\nNo error while transmission of data\n");
 	}
	else
	{
		printf("\nError on position %d",c);
		printf("\nData sent : ");
		for(int i=0;i<11;i++)
			printf("%d",data[i]);

		printf("\nData recieved : ");
		for(int i=0;i<11;i++)
			printf("%d",datatrac[i]);

		printf("\nCorrect message is\n");

		datatrac[11-c] = (datatrac[11-c]==0)?1:0;

		for(int i=0;i<11;i++)
                	printf("%d",datatrac[i]);
                printf("\n");
	}
}