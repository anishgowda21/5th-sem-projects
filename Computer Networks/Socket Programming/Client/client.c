#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<arpa/inet.h>
#include<netinet/in.h>
#include<netdb.h>
#include<unistd.h>
#include<sys/types.h>
#include<sys/socket.h>
#define SERV_TCP_PORT 5035
#define MAX 60

int main(){
	int sockfd,n;
	struct sockaddr_in serv_addr;
	char name[MAX],recived[MAX];
	sockfd = socket(AF_INET,SOCK_STREAM,0);
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	serv_addr.sin_port = htons(SERV_TCP_PORT);
	connect(sockfd,(struct sockaddr*)&serv_addr,sizeof(serv_addr));
	printf("\nEnter Filename:");
	scanf("%s",name);
	write(sockfd,name,MAX);
	while((n=read(sockfd,recived,MAX)) != 0)
	{
		printf("%s",recived);
	}
	close(sockfd);
	return 0;
}