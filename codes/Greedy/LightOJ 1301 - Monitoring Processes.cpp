/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-22 04:26:28
 * Problem   : 1301 - Monitoring Processes
 * CPU       : 0.272
 * Memory    : 2336
**/
#include <bits/stdc++.h>

using namespace std;

bool cmp(pair<int,int> a, pair<int,int> b) {
    return a.first<b.first;
}

int main() {
    //freopen("in","r",stdin);
    int t;
    scanf("%d",&t);
    int n,x,y;
    vector<int> s;
    vector<int> e;
    for(int i = 0;i<t;i++) {
        scanf("%d",&n);
        for(int j = 0;j<n;j++) {
            scanf("%d %d",&x,&y);
            s.push_back(x);
            e.push_back(y);
        }
        sort(s.begin(),s.end());
        sort(e.begin(),e.end());
        vector<int>::iterator si=s.begin(),ei=e.begin();
        int count =0,mx=0;
        while(si!=s.end() && ei!=e.end()) {
            if(*si <= *ei ) {
                count++;
                mx=max(mx,count);
                si++;
            } else {
                count--;
                ei++;
            }
        }
        printf("Case %d: %d\n",i+1,mx);
        s.clear();
        e.clear();
    }    
    return 0;
}