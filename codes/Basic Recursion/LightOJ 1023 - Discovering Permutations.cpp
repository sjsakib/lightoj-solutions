/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-25 22:08:43
 * Problem   : 1023 - Discovering Permutations
 * CPU       : 0.040
 * Memory    : 1688
**/
/*
 *  sjsakib
 */ 
#include <bits/stdc++.h>

#define stream istringstream
#define rep(i,n) for(int i=0; i<(int)n; i++)
#define repv(i,n) for(int i=n-1; i>=0; i--)
#define repl(i,n) for(int i=1; i<=(int)n; i++)
#define replv(i,n) for(int i=n; i>=1; i--)


#define INF (1<<28)
#define PI 3.14159265358979323846264338327950
#define pb(x) push_back(x)
#define ppb pop_back
#define all(x) x.begin(),x.end()
#define mem(x,y) memset(x,y,sizeof(x));
#define eps 1e-9
#define pii pair<int,int>
#define pmp make_pair


#define sdi(x) scanf("%d",&x)
#define sdii(x,y) scanf("%d%d",&x,&y)
#define sdc(x) scanf("%c",&x)
#define SDs(x) scanf("%s",x)
#define uu first
#define vv second

using namespace std;

template<class T> inline T gcd(T a,T b) {if(a<0)return 
gcd(-a,b);if(b<0)return gcd(a,-b);return (b==0)?a:gcd(b,a%b);}
template<class T> inline T lcm(T a,T b) {if(a<0)return 
lcm(-a,b);if(b<0)return lcm(a,-b);return a*(b/gcd(a,b));}
template<class T> inline T sqr(T x){return x*x;}
template<class T> T power(T N,T P){ return (P==0)?  1: N*power(N,P-1); }

typedef long long i64;
typedef unsigned long long ui64;

#define READ(f) freopen(f, "r", stdin)
#define WRITE(f) freopen(f, "w", stdout)

//bool operator < ( const node& p ) const {      return dist > p.dist;   }

#define cl list<char>

int fx[]={0,0,-1,+1,-1,-1,+1,+1};
int fy[]={-1,+1,0,0,-1,+1,-1,+1};


void per(cl,int,int,bool);
void forward(cl,int,int);

int N,K;

void print(cl s) {
    cl::iterator it = s.begin();
    while(it!=s.end()) {
        cout<<*it;
        it++;
    }
}

void forward(cl s,int b,int a) {
    cl::iterator itb = s.begin(),ita = s.begin();
    advance(ita,a);
    advance(itb,b);
    s.insert(ita,*itb);
    s.erase(itb);
    print(s);
    cout<<endl;
    K--;
    if(K<=0) return;
    if(!(b==s.size()-1 && a == s.size()-2) ) {
        per(s,a+1,s.size()-1,false);
    }
}

void per(cl s,int a,int b,bool p) {
    if(p) {
        print(s);
        cout<<endl;
        K--;
    }
    if(K<=0) return;
    for(int i = b;i>=a;i--) {
        for(int j = i+1;j<s.size();j++) {
            forward(s,j,i);
            if(K<=0) return;
        }
    }
}

int main()
{
    //READ("in");
    int t;
    sdi(t);
    char c;
    for(int i = 0;i<t;i++) {
        c = 'A';
        cl s;
        sdii(N,K);
        rep(j,N) {
            s.push_back(c);
            c++;
        }
        printf("Case %d:\n",i+1 );
        per(s,0,N-1,true);
    }
    return 0;
}