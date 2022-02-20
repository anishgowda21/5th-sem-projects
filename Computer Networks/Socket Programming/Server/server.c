#include<stdio.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include<sys/types.h>
#include<sys/socket.h>
#include<netdb.h>
#include<unistd.h>
#include<string.h>
#define SERV_PORT_TCP 5035
#define MAX 60

char buff[4096],t;

FILE f1*;

int main()
{
	int sockfd,newsockfd,clength;
	char str[MAX],t[MAX];
	struct sockaddr_in serv_addr,cli_addr;
	strcpy(t,"exit");
	sockfd = socket(AF_INET,SOCK_STREAM,0);
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = INADDR_ANY;
	serv_addr.sin_port = htons(SERV_PORT_TCP);
	printf("\nBinded..\n");
	bind(sockfd,(struct sockaddr*)&serv_addr,sizeof(serv_addr));
	printf("\nListening...\n");
	listen(sockfd,5);
	clength = sizeof(cli_addr);
	newsockfd = accept(sockfd,(struct sockaddr*)&cli_addr,sizeof(&clength));
	close(sockfd);
	read(newsockfd,&str,MAX);
	printf("\nClient Message\nFile Name : %s \n",str);
	f1 = fopen(str,"r");
	while(fgets(buff,4096,f1) != null )
	{
		write(newsockfd,buff,MAX);

	}
	printf("\n");
	fclose(f1);
	printf("File Transferd\n");
	return 0
}