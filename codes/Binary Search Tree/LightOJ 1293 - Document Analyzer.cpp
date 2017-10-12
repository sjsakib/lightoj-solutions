/**
 * Author    : sjsakib.bd
 * Lang      : C++
 * Date      : 2017-03-20 23:17:48
 * Problem   : 1293 - Document Analyzer
 * CPU       : 1.372
 * Memory    : 9364
**/
#include <bits/stdc++.h>

using namespace std;

vector<string> words;

void tokenize(char s[]) {
    int l = strlen(s);
    bool word = 0;
    int i = 0,j;
    char temp[15];
    while(i<=l) {
        if(s[i]>='a' && s[i]<='z') {
            if(!word) word = 1,j = 0;
            temp[j++] = s[i];
        } else if(word) {
            word = 0;
            temp[j] = '\0';
            words.push_back(string(temp));
        }
        i++;
    }
}

int main() {
    //freopen("in","r",stdin);
    int t;
    scanf("%d",&t);
    char s[109];
    for(int i = 0;i<t;i++) {
        while(1) {
            fgets(s,109,stdin);
            if(s[0] == 'E' && s[1] == 'N' && s[2] == 'D') break;
            tokenize(s);
        }
        set<string> uniq(words.begin(),words.end());
        int size = uniq.size();
        int arr_size = words.size();
        map<string,int> cur;
        int l=0,r=-1;
        int L,R;
        int min = 1e8;
        int cur_size = 0;
        while(r < arr_size) {
            while(cur_size < size && r < arr_size) {
                r++;
                if(r==arr_size) break;
                if(cur[words[r]] == 0) cur_size++;
                cur[words[r]]++;
            }
            if(r==arr_size) break;
            while(true) {
                if(cur[words[l]] > 1) {
                    cur[words[l]]--;
                    l++;
                } else break;
            }
            if(r-l<min) {
                min = r-l;
                L = l+1;
                R = r+1;
            }
            cur[words[l]]--;
            cur_size--;
            l++;
        }
        printf("Case %d: %d %d\n",i+1,L,R );
        words.clear();
    }
    return 0;
}