//program for error detection using CRC-CCITT (x16+x12+x5+1).

#include<stdio.h>
#include<stdlib.h>
#include<string.h>

#define N strlen(g)

char t[28],cs[28],g[]="10001000000100001";
int a,e,c;

void xor(){
    for(c = 1; c < N; c++){
        cs[c] = ((cs[c] == g[c]) ? '0' : '1');
    }
}

void crc(){
    for (e = 0; e < N ; e++)
        cs[e] = t[e];
    do{
        if(cs[0] == '1')
            xor();
        for(c = 0; c < N-1; c++)
            cs[c] = cs[c+1];
        cs[c] = t[e++];
    }while(e<=a+N-1);
}

int main()
{
    printf("Enter the message: ");
    scanf("%s",t);
    printf("\n------------------------------------------------");
    printf("\nGenerating Polynomial: %s",g);
    a = strlen(t);
    for(e=a ; e<a+N-1; e++)
        t[e] = '0';
    printf("\n------------------------------------------------");
    printf("\nModified Message: %s",t);
    printf("\n------------------------------------------------");
    crc();
    printf("\nCRC Checksum: %s",cs);
    for(e=a; e<a+N-1; e++)
        t[e] = cs[e-a];
    printf("\n------------------------------------------------");
    printf("\nFinal Message: %s",t);
    printf("\n------------------------------------------------");
    printf("\nTest Error Detection 0(YES) 1(NO): ");
    scanf("%d",&e);

    if(e==0){
        do{
            printf("\nEnter the error position: ");
            scanf("%d",&e);
        }while(e==0 || e>a+N-1);
        t[e-1] = ((t[e-1] == '0') ? '1' : '0');
        printf("\n------------------------------------------------");
        printf("\nErrorous Message: %s",t);
    }
    crc();
    for(e=0;(e<N-1)&&(cs[e]!='1');e++);
    if(e < N-1)
    {
        printf("\n------------------------------------------------");
        printf("\nCRC Checksum: %s",cs);
        printf("\nError Detected\n");
    }
    else{
        printf("\n------------------------------------------------");
        printf("\nCRC Checksum: %s",cs);
        printf("\nNo Error Detected\n");
    }
    printf("\n------------------------------------------------");
    return 0;
}