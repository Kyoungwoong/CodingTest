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
//        prev();
        int[] ans = nov6();
        for (int ansNum : ans) {
            System.out.print(ansNum + " ");
        }
    }

    private static int[] nov6() {
        int len = genres.length;
        Map<String, Integer> genreRank = new HashMap<>();
        Map<String, List<Album>> albumRank = new HashMap<>();

        // 데이터 초기화
        for (int i = 0; i < len; i++) {
            genreRank.put(genres[i], genreRank.getOrDefault(genres[i], 0) + plays[i]);
            albumRank.putIfAbsent(genres[i], new ArrayList<>());
            albumRank.get(genres[i]).add(new Album(i, plays[i]));
        }

        // 장르별 총 재생 횟수에 따라 장르 정렬
        List<String> sortedGenres = new ArrayList<>(genreRank.keySet());
        sortedGenres.sort((a, b) -> genreRank.get(b) - genreRank.get(a));

        List<Integer> ans = new ArrayList<>();

        // 각 장르 내에서 앨범 정렬 및 상위 2곡 선택
        for (String genre : sortedGenres) {
            List<Album> genreAlbum = albumRank.get(genre);
//            genreAlbum.sort((a, b) -> {
//                if (b.playCnt == a.playCnt) {
//                    return a.idx - b.idx; // 재생 횟수가 같으면 인덱스 오름차순
//                }
//                return b.playCnt - a.playCnt; // 재생 횟수 내림차순
//            });
            Collections.sort(genreAlbum);
            for (int i = 0; i < Math.min(2, genreAlbum.size()); i++) {
                ans.add(genreAlbum.get(i).idx);
            }
        }

        // 결과를 배열로 반환
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void prev() {
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
