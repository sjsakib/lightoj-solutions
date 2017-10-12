/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-23 14:31:35
 * Problem   : 1198 - Karate Competition
 * CPU       : 0.000
 * Memory    : 1700
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

int fx[]={0,0,-1,+1,-1,-1,+1,+1};
int fy[]={-1,+1,0,0,-1,+1,-1,+1};

int my[51],op[51],n;
int dp[51][51];

int call(int i,int j) {
    if(dp[i][j]!=-1) return dp[i][j];
    if(i == n) {
        return 0;
    }
    if(my[i]>op[j]) return dp[i][j] = 2+call(i+1,j+1);
    else if(my[i] == op[j]) return  dp[i][j] =  max(call(i+1,j),1+call(i+1,j+1));
    else return dp[i][j] = call(i+1,j);
}

int main()
{
    //READ("in");
    int t;
    int res;
    sdi(t);
    rep(i,t) {
        sdi(n);
        rep(j,n) {
            sdi(my[j]);
        }
        rep(j,n) {
            sdi(op[j]);
        }
        sort(my,my+n);
        sort(op,op+n);
        /*res = 0;
        int  j = 0,k = 0,x = 0;
        while(j<n) {
            if(my[j]>op[k]) {
                res+=2;
                j++;
                k++;
            } else if(my[j]==op[k] && j<n-1) {
                if(my[j+1]>op[k] && my[j+1]<=op[k+1]) {
                    j++;
                } else {
                    res+=1;
                    j++;
                    k++;
                }
            } else if(my[j]==op[k]) {
                res+=1;
                j++;
                k++;
            } else {
                j++;
            }
            //cout<<j<<endl;
        }*/
        mem(dp,-1);
        printf("Case %d: %d\n",i+1,call(0,0));
    }
    return 0;
}