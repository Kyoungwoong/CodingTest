package ThisIsCodingTest.Sort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Rank {
    static class Student implements Comparable<Student>{
        String name;
        int score;

        public Student(String name, int score){
            this.name = name;
            this.score = score;
        }

        @Override
        public int compareTo(Student s){
            return this.score- s.score;
        }
    }

    public static int N;
    public static Student[] classA;

    public static void main(String[] args) throws IOException {
//        prev();

        oct14();
    }

    private static void oct14() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            Student student = new Student(st.nextToken(), Integer.parseInt(st.nextToken()));
            students.add(student);
        }

        Collections.sort(students);

        for (Student student : students) {
            System.out.print(student.name + " ");
        }
    }

    private static void prev() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        classA = new Student[N];
        StringTokenizer st;

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            classA[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(classA);

        for(int i = 0; i < N; i++){
            System.out.print(classA[i].name+" ");
        }

        // version 나동빈
//        Scanner sc = new Scanner(System.in);
//
//        // N을 입력받기
//        int n = sc.nextInt();
//
//        List<Student> students = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            String name = sc.next();
//            int score = sc.nextInt();
//            students.add(new Student(name, score));
//        }
//
//        Collections.sort(students);
//
//        for (int i = 0; i < students.size(); i++) {
//            System.out.print(students.get(i).name + " ");
//        }
    }
}

/*
2
홍홍길동 95
이순신 77
 */
