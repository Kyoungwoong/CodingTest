package Programmers.Hash;

import java.util.*;

public class BestAlbum {
    static class Album implements Comparable<Album>{
        int idx, playCnt;

        public Album(int idx, int playCnt) {
            this.idx = idx;
            this.playCnt = playCnt;
        }

        @Override
        public int compareTo(Album album) {
            if(this.playCnt == album.playCnt) {
                return this.idx - album.idx;
            } else {
                return album.playCnt - this.playCnt;
            }
        }
    }

    public static String[] genres = {"classic", "pop", "classic", "classic", "pop"};
    public static int[] plays = {500, 600, 150, 800, 2500};
    //    public static String[] genres = {"pop", "pop", "pop", "rap", "rap"};
//    public static int[] plays = {45, 50, 40, 60, 70};

    public static void main(String[] args) {
        HashMap<String, Integer> partition = new HashMap<>();
        HashMap<String, List<Album>> categories = new HashMap<>();

        int len = genres.length;
        for (int i = 0; i < len; i++) {
            partition.put(genres[i], partition.getOrDefault(genres[i], 0) + plays[i]);
            categories.computeIfAbsent(genres[i], k -> new ArrayList<>()).add(new Album(i, plays[i]));
        }

        for (String category : categories.keySet()) {
            Collections.sort(categories.get(category));
        }

        List<Map.Entry<String, Integer>> partitionList = new ArrayList<>(partition.entrySet());
        partitionList.sort((a, b) -> b.getValue() - a.getValue());

        List<Integer> answer = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : partitionList) {
            String genre = entry.getKey();
            List<Album> albums = categories.get(genre);
            // 최대 두 개의 곡을 선택
            for (int i = 0; i < Math.min(2, albums.size()); i++) {
                answer.add(albums.get(i).idx);
            }
        }
        System.out.println("answer = " + answer);

//        return answer.stream()
//                .mapToInt(Integer::intValue)
//                .toArray();

    }
}
