#define k 20
#include <stdio.h>
#include <unistd.h>
#include <string.h>

char buff[k];
int size = 0;
int eof = 0;
	
int findEndOfString(){
	int i = 0;		
	while(i < size ){
		if(buff[i] == '\n'){
			return i;		
		}			
		++i;
	}
	return -1;
}
void clean(){
	size = 0;
}
void printReverse(int i, int j){
	int temp;
	for(temp = i; temp <= (i + j) / 2; ++temp){
		char c = buff[temp];
		buff[temp] = buff[j - temp];
		buff[j - temp] = c;
	}
	int out = fileno(stdout);
	write(out, buff + sizeof(char) * (i + 1), j - i);
	char newline[1] = {'\n'};
	write(out, newline, 1);
}

int readBuff(){
	int in = fileno(stdin);
	int readCount = read(in, buff + sizeof(char) * size, k - size);
	size = size + readCount;
	if(size != k){
		eof = 1;
	} else if(readCount == -1){
		return -1;	
	}
	return 1;
}

void normalize(int i){
	memcpy(buff, buff + sizeof(char) * i, size - i);
}

int main(int argv, char* argc[]){
	int error = 0;
	while(!eof && readBuff() > 0){	
		if(findEndOfString() == -1){
			error = 1;
			clean();		
		} else{
			int i;
			while((i = findEndOfString()) != -1){
				if(error == 1){
					error = 0;
				} else {
					printReverse(0, i);
				}
				normalize(i + 1);
				size -= i + 1;
			}
		}
	}
	return 0;
}
