/**
 * Author    : sjsakib.bd
 * Lang      : C
 * Date      : 2015-05-27 06:45:31
 * Problem   : 1015 - Brush (I)
 * CPU       : 0.008
 * Memory    : 1088
**/
#include <stdio.h>

int main(void) {
	int t,t2;
	int i,j;
	int s,x;
	scanf("%d",&t);
	for(i = 0;i<t;i++){
		scanf("%d",&t2);
		s = 0;
		for(j = 0;j<t2;j++) {
			scanf("%d",&x);
			if(x>0) s+=x;
		}
		printf("Case %d: %d\n",i+1,s);
	}
	return 0;
}
