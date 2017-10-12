/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-02-21 23:34:43
 * Problem   : 1076 - Get the Containers
 * CPU       : 0.028
 * Memory    : 5596
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

int n,m;
int ves[1000001];


int main()
{
    //READ("in");
    int t;
    int low,mid,high;
    sdi(t);
    rep(i,t) {
        sdii(n,m);
        low  = 0;
        high = 0;
        rep(j,n) {
            sdi(ves[j]);
            high+=ves[j];
        }
        int sum,k,j;
        while(low<=high) {
            mid = (low+high)/2;
            k = 0;
            //cout<<low<<' '<<mid<<' '<<high<<endl;
            for(j= 0;j<m;j++) {
                sum = 0;
                while(sum+ves[k]<=mid && k<n) {
                    sum+=ves[k++];
                }
                if(k == n) break;
            }
            if(high == mid) break;
            if(k<n) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        printf("Case %d: %d\n",i+1,mid);
    }
    return 0;
}