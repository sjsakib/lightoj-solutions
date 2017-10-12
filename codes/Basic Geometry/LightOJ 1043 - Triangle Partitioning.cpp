/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-01 16:08:08
 * Problem   : 1043 - Triangle Partitioning
 * CPU       : 0.000
 * Memory    : 1700
**/
#include <bits/stdc++.h>
#include <cstdio>

#define vi vector<int>

using namespace std;

int main() {
    int t;
    long double ab,ac,bc,de,ad,ae,ade,bdec,ade_bdec,abc;
    long double low,high,mid;
    long double s;
    scanf("%d",&t);
    for(int i = 0;i<t;i++) {
        scanf("%Lf %Lf %Lf %Lf",&ab,&ac,&bc,&ade_bdec);
        low = 0;
        high = ab;
        mid = (high+low)/2;
        while(high-low>0.0000001d) {
            mid = (low+high)/2;
            ad = mid;
            ae = ac*(ad/ab);
            de = bc*(ad/ab);
            s = (ab+ac+bc)/2;
            abc = sqrt(s*(s-ab)*(s-ac)*(s-bc));
            s = (ad+de+ae)/2;
            ade = sqrt(s*(s-ad)*(s-de)*(s-ae));
            bdec = abc - ade;
            if((ade/bdec)>ade_bdec) {
                high = mid;
                //cout<<mid;
            } else {
                low = mid;
                //cout<<mid;
            }
        }
        printf("Case %d: %Lf\n",i+1,mid);
    }
        
    return 0;
}
