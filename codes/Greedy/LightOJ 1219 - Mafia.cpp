/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-29 16:48:08
 * Problem   : 1219 - Mafia
 * CPU       : 0.008
 * Memory    : 2012
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

//bool operator < ( const node& p ) const {      return dist > p.dist;   }

int fx[]={0,0,-1,+1,-1,-1,+1,+1};
int fy[]={-1,+1,0,0,-1,+1,-1,+1};

int n;
vector<int> edges[10001];
int nodes[10001];
bool dpvis[10001];
int dp[10001];

int count(int x) {
    if(dpvis[x] == true) return dp[x];
    int r = nodes[x]-1;
    rep(i,edges[x].size()) {
        r+=count(edges[x][i]);
    }
    dp[x] = r;
    dpvis[x] = true;
    return r;
}


int main()
{
    //READ("in");
    int t;
    sdi(t);
    int v,d,x;
    int sum;
    rep(i,t) {
        sdi(n);
        rep(j,n) {
            sdi(v);
            edges[v].clear();
            sdi(nodes[v]);
            sdi(d);
            rep(k,d) {
                sdi(x);
                edges[v].push_back(x);
            }
        }
        sum = 0;
        memset(dpvis,false,sizeof(dpvis[0])*n+1);
        repl(j,n) {
            sum+=abs(count(j));
        }
        printf("Case %d: %d\n",i+1,sum);
    }
    return 0;
}