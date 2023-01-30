package SWExpert.DFS;

public class UserSolution {

    int[][] childList = new int[101][5];
    int[] childCnt = new int[101];
    boolean[] visited = new boolean[101];
    int king = 0, max = -1;
    int[] stack = new int[101];
    int top = -1, child = -1;

    public void dfs_init(int N, int[][] path) {

        for(int i = 0; i <= 100; i++){
            childCnt[i] = 0;
            for(int j = 0; j < 5; j++){
                childList[i][j] = 0;
            }
        }

        king = 0;

        for(int i = 0; i < N-1; i++){
            int parent = path[i][0];
            int child = path[i][1];
            childList[parent][childCnt[parent]] = child;
            childCnt[parent]++;
        }
    }

    public int dfs(int N) {

        if(king == 0){
            child = -1;
            king = N;
        }

        if(N == king && childCnt[king] == 0){
            king = 0;
            child = -1;
            return -1;
        }

        for(int i = 0; i < childCnt[N]; i++){
            if(child != -1) {
                break;
            }
            findChild(childList[N][i]);
        }


        king = 0;
        if(child == 100){
            child = -1;
        }
        return child;
    }

    public void findChild(int node){
        if(child < node && node > king){
            child = node;
            return;
        }

        for(int i = 0; i < childCnt[node]; i++){
            if(child != -1){
                return;
            }
            findChild(childList[node][i]);
        }
    }
}