/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-03-01 11:27:30
 * Problem   : 1005 - Rooks
 * CPU       : 0.000
 * Memory    : 1696
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
#define mem(x,y) memset(x,y,sizeof(x))
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

#define cl list<char>

#define READ(f) freopen(f, "r", stdin)
#define WRITE(f) freopen(f, "w", stdout)

int fx[]={0,0,-1,+1,-1,-1,+1,+1};
int fy[]={-1,+1,0,0,-1,+1,-1,+1};



int n,k;
i64 dp[31][31];
i64 dpf[31];

i64 ncr(int n,int r) {
    if(r>n || n == 0) return 0;
    if(r==1) return n;
    if(n==r) return 1;
    if(dp[n][r]!=-1) return dp[n][r];
    else {
        dp[n][r] = ncr(n-1,r)+ncr(n-1,r-1);
        return dp[n][r];
    }
}
i64 fact(int n) {
    if(dpf[n]!=-1) return dpf[n];
    if(n == 1 || n == 0) return 1;
    else return dpf[n] = n*fact(n-1);
}

int main()
{
    //READ("in");
    int t;
    sdi(t);
    i64 res;
    mem(dp,-1);
    mem(dpf,-1);
    rep(i,t) {
        sdii(n,k);
        res = ncr(n,k);
        res*=res*fact(k);
        if(k == 0) res = 1;
        printf("Case %d: %lld\n",i+1,res);
    }    
    return 0;
}