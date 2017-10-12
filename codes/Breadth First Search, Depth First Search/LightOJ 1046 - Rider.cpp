/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2016-07-04 14:37:17
 * Problem   : 1046 - Rider
 * CPU       : 0.192
 * Memory    : 1892
**/
/* sjsakib  */
#include <bits/stdc++.h>

#define stream istringstream
#define rep(i,n) for(int i=0; i<(int)n; i++)
#define repv(i,n) for(int i=n-1; i>=0; i--)
#define repl(i,n) for(int i=1; i<=(int)n; i++)
#define replv(i,n) for(int i=n; i>=1; i--)


#define INF (1<<30)
#define INF64 (1ll<<62)
#define PI 3.141592653589793238462643383279502
#define pb(x) push_back(x)
#define ppb pop_back
#define all(x) x.begin(),x.end()
#define mem(x,y) memset(x,y,sizeof(x))
#define eps 1e-9
#define pii pair<int,int>
#define vi vector<int>
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


#define OUT(x,y) (x>=m || x<0 || y>=n || y<0)

int fx[]={0,0,-1,+1,-1,-1,+1,+1};
int fy[]={-1,+1,0,0,-1,+1,-1,+1};

int dx[]={1,1,2,2,-1,-1,-2,-2};
int dy[]={2,-2,1,-1,2,-2,1,-1};

int m,n;

int level[12][12][12][12];
bool vis[12][12];

void bfs(int x,int y) {
    level[x][y][x][y] = 0;
    queue<pair<int,int> > Q;
    Q.push(pmp(x,y));
    while(!Q.empty()) {
        pii u = Q.front();
        Q.pop();
        vis[u.uu][u.vv] = true;
        rep(i,8) {
            pii v = pmp(u.uu+dx[i],u.vv+dy[i]);
            if(!OUT(v.uu,v.vv) && !vis[v.uu][v.vv]) {
                level[x][y][v.uu][v.vv] = level[x][y][u.uu][u.vv]+1;
                Q.push(v);
            }

        }
    }

}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(NULL);
    int t;
    char grid[12][12];
    cin>>t;
    rep(i,t) {
        mem(level,-1);
        cin>>m>>n;
        rep(k,m) {
            rep(l,n) {
                cin>>grid[k][l];
                //mem(vis,0);
                //bfs(l,m);
                if(grid[k][l]!='.') {
                    mem(vis,0);
                    bfs(k,l);
                }
            }
        }
        int mn = INF;
        rep(k,m) {
            rep(l,n) {
                int tmp = 0;
                rep(x,m) {
                    rep(y,n) {
                        if(grid[x][y] == '.') continue;
                        if(level[x][y][k][l] == -1) {
                            tmp = -1;
                            break;
                        }
                        int kk = grid[x][y] - '0';
                        tmp+=(int) ceil((float)level[x][y][k][l]/(float)kk);
                    }
                    if(tmp == -1) {
                        break;
                    }
                }
                if(tmp!=-1) mn = min(mn,tmp);
            }
        }
        if(mn == INF) mn = -1;
        cout<<"Case "<<(i+1)<<": "<<mn<<endl;

    }

    return 0;
}
