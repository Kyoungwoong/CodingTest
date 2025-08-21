package ssafy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BType {

    private int N;
    private Map<Integer, Integer> growth;
    private Seed[][] lands;
    private List<Seed> sowedSeeds;

    void init(int n, int[] growths) {
        N = n;
        lands = new Seed[N][N];

        growth = new HashMap<>();
        for (int i = 0; i < growths.length; i++) {
            growth.put(i, growths[i]);
        }

        sowedSeeds = new ArrayList<>();
    }

    int sow(int curTime, int x, int y, int category) {
        if (lands[x][y] != null) {
            return 0;
        }

        Seed seed = new Seed(x, y, 0, category, curTime);
        lands[x][y] = seed;
        sowedSeeds.add(seed);

        return 1;
    }

    int water(int curTime, int G, int x, int y, int width, int height) {
        int count = 0;

        for (Seed seed : sowedSeeds) {
            if (inRange(seed, x, y, width, height)) {
                seed.size += G;
                count++;
            }
        }

        return count;
    }

    int harvest(int curTime, int L, int x, int y, int width, int height) {
        int count = 0;
        List<Seed> remainingSeeds = new ArrayList<>();
        List<Seed> harvestedSeeds = new ArrayList<>();

        for (Seed seed : sowedSeeds) {
            if (inRange(seed, x, y, width, height)) {
                int grownSize = seed.size + (curTime - seed.sowedTime) / growth.get(seed.category);

                if (grownSize < L) {
                    // 수확 실패 시, 기존 상태 유지
                    return 0;
                }

                harvestedSeeds.add(seed);
                count++;
            } else {
                remainingSeeds.add(seed);
            }
        }

        // harvest 성공: 필드에서 제거
        for (Seed seed : harvestedSeeds) {
            lands[seed.x][seed.y] = null;
        }

        sowedSeeds = remainingSeeds;
        return count;
    }

    boolean inRange(Seed seed, int x, int y, int width, int height) {
        return x <= seed.x && seed.x < x + width && y <= seed.y && seed.y < y + height;
    }

    static class Seed {
        int x, y, size, category, sowedTime;

        public Seed(int x, int y, int size, int category, int sowedTime) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.category = category;
            this.sowedTime = sowedTime;
        }
    }
}
