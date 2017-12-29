#include<iostream>
#include<cstring>
#include<math.h>

using namespace std;

char arr[12];
void create(char *data)
{
	arr[2]=data[0];
	arr[4]=data[1];
	arr[5]=data[2];
	arr[6]=data[3];
	arr[8]=data[4];
	arr[9]=data[5];
	arr[10]=data[6];
	//For P1
	int temp=(arr[2]-48)+(arr[4]-48)+(arr[6]-48)+(arr[8]-48)+(arr[10]-48);
	if(temp%2==0)
	{
		arr[0]='0';	
	} 
	else
	{
		arr[0]='1';
	}
	//For P2
	temp=0;
	temp=(arr[2]-48)+(arr[5]-48)+(arr[6]-48)+(arr[9]-48)+(arr[10]-48);
	if(temp%2==0)
	{
		arr[1]='0';	
	} 
	else
	{
		arr[1]='1';
	}
	//For P4
	temp=0;
	temp=(arr[4]-48)+(arr[5]-48)+(arr[6]-48);
	if(temp%2==0)
	{
		arr[3]='0';	
	} 
	else
	{
		arr[3]='1';
	}
	//For P8
	temp=0;
	temp=(arr[8]-48)+(arr[9]-48)+(arr[10]-48);
	if(temp%2==0)
	{
		arr[7]='0';	
	} 
	else
	{
		arr[7]='1';
	}
	cout<<"\nHamming code of given data is-:" <<arr<<"\n";
}

void change(int pos, char data)
{
	arr[pos-1]=data;
}

void check()
{
	int parity[5];
	
//P8=parity[0], P4=parity[1]....
//For P1
	int temp=(arr[0]-48)+(arr[2]-48)+(arr[4]-48)+(arr[6]-48)+(arr[8]-48)+(arr[10]-48);
	if(temp%2==0)
	{
		parity[3]=0;	
	} 
	else
	{
		parity[3]=1;
	}
	//For P2
	temp=0;
	temp=(arr[1]-48)+(arr[2]-48)+(arr[5]-48)+(arr[6]-48)+(arr[9]-48)+(arr[10]-48);
	if(temp%2==0)
	{
		parity[2]=0;
	} 
	else
	{
		parity[2]=1;
	}
	//For P4
	temp=0;
	temp=(arr[3]-48)+(arr[4]-48)+(arr[5]-48)+(arr[6]-48);
	if(temp%2==0)
	{
		parity[1]=0;
	} 
	else
	{
		parity[1]=1;
	}
	//For P8
	temp=0;
	temp=(arr[7]-48)+(arr[8]-48)+(arr[9]-48)+(arr[10]-48);
	if(temp%2==0)
	{
		parity[0]=0;	
	} 
	else
	{
		parity[0]=1;
	}
	//Checking if change is done or not
	if(parity[0]==0 && parity[1]==0 && parity[2]==0 && parity[3]==0)
	{
		cout<<"\nGiven data is not changed!!";
	}
	else
	{
		//Binary to decimal
		int pos=0;
		for(int i=0;i<4;i++)
		{
			pos=pos+(parity[i]*pow(2,3-i));
		}
		cout<<"\nAltered position is-: "<<pos<<"\n altered bit is-"<<arr[pos-1];
	}
}

int main()
{
char accept[8],bit;
int ps;
cout<<"\nEnter 7 bits data to be encrypted-: ";
cin>>accept;
create(accept);
cout<<"\nEnter position where change is to be made-: ";
cin>>ps; 
cout<<"\nEnter new bit at position '"<<ps<<"'-: ";
cin>>bit;
change(ps,bit);
check();
return 0;
}
