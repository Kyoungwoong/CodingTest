package SWExpert.Graph.BFS;

class UserSolution {

    class Pair{
        int x, y;
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    int N, front, rear;
    int[][] world = new int[10][10];
    int[][] step = new int[10][10];
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};
    boolean[][] visited = new boolean[10][10];
    Pair[] q = new Pair[100];

    void bfs_init(int map_size, int map[][]) {

        N = map_size;

        for(int i = 0; i < map_size; i++){
            for(int j = 0; j < map_size; j++){
                world[i][j] = map[i][j];
            }
        }

    }

    void init(){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                visited[i][j] = false;
                step[i][j] = 0;
            }
        }

        front = rear = -1;
    }

    boolean isRange(int x, int y){
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    boolean canGo(int x, int y){
        if(!isRange(x, y)) return false;
        if(visited[x][y] || world[x][y] == 1) return false;
        return true;
    }

    void push(Pair pair, int s){
        visited[pair.x][pair.y] = true;
        step[pair.x][pair.y] = s;
        q[++rear] = pair;
    }
    int bfs(int x1, int y1, int x2, int y2) {
        init();

        Pair start = new Pair(y1 - 1, x1 - 1);
        Pair end = new Pair(y2 - 1, x2 - 1);

        if(world[start.x][start.y] == 1){
            return -1;
        }

        push(start, 0);


        while(front != rear){
            Pair cur = q[++front];

            for(int i = 0; i < 4; i++){
                int nextX = cur.x + dx[i];
                int nextY = cur.y + dy[i];
                if(canGo(nextX, nextY)){
                    push(new Pair(nextX, nextY), step[cur.x][cur.y]+1);
                    if(nextX == end.x && nextY == end.y){
                        break;
                    }
                }
            }
        }


        if(step[end.x][end.y] != 0){
            return step[end.x][end.y];
        }else{
            return -1;
        }
    }
}
