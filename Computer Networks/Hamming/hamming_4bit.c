// Program in C/ C++ for hamming code generation for error detection/correction for 4bit data

#include <stdio.h>

int main()
{
    int data[10],datatrac[10],c,c1,c2,c3,i;

    printf("Enter 4 bits of data one by one\n");
    scanf("%d",&data[0]);
    scanf("%d",&data[1]);
    scanf("%d",&data[2]);
    scanf("%d",&data[4]);

    //calculate even parity

    data[6] = data[0] ^ data[2] ^ data[4];
    data[5] = data[0] ^ data[1] ^ data[4];
    data[3] = data[0] ^ data[1] ^ data[2];

    printf("\nEncoded data is:\n");

    for(i=0;i<7;i++)
    {
        printf("%d",data[i]);
    }


    printf("\n\nEnter received data bits one by one:\n");

    for(i=0;i<7;i++)
    {
        scanf("%d",&datatrac[i]);
    }

    c1 = datatrac[6] ^ datatrac[4] ^ datatrac[2] ^ datatrac[0];
    c2 = datatrac[5] ^ datatrac[4] ^ datatrac[1] ^ datatrac[0];
    c3 = datatrac[3] ^ datatrac[2] ^ datatrac[1] ^ datatrac[0];
    c = c3*4 + c2*2 + c1;

    if(c==0)
    {
        printf("\nNo error detected\n");
    }
    else
    {
        printf("\nError detected at bit %d\n",c);

        printf("\n Data sent was:\n");
        for(i=0;i<7;i++)
        {
            printf("%d",data[i]);
        }

        printf("\n\nData received was:\n");
        for(i=0;i<7;i++)
        {
            printf("%d",datatrac[i]);
        }

        printf("\n\nCorrected data is:\n");
        if(datatrac[7-c] == 1)
        {
            datatrac[7-c] = 0;
        }
        else
        {
            datatrac[7-c] = 1;
        }

        for(i=0;i<7;i++)
        {
            printf("%d",datatrac[i]);
        }
    }
}