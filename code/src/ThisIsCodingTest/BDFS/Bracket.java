package ThisIsCodingTest.BDFS;

import java.io.IOException;

public class Bracket {
    public int balancedIdx(String p) {
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '('){
                count++;
            }else{
                count--;
            }
            if(count == 0) return i;
        }
        return -1;
    }

    public boolean checkCorrect(String p) {
        int count = 0;
        for (int i = 0; i < p.length(); i++) {
            if(p.charAt(i) == '(') count++;
            else count--;
        }
        if(count == 0) return true;
        else return false;
    }
    public String Solution(String p){
        String answer = "";
        if (p.equals(answer)) return answer;

        int idx = balancedIdx(p);
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1);

        if (checkCorrect(u)) {
            answer += u + Solution(v);
        }else{
            answer += "(";
            answer += Solution(v);
            answer += ")";
            String temp = u.substring(1, p.length() - 1);
            for(int i = 0; i < temp.length(); i++){
                if(temp.charAt(i) == '('){
                    answer += ")";
                }else{
                    answer += "(";
                }
            }
        }

        return answer;
    }
}

/*
"(()())()"
--- "(()())()"

")("
--- "()"

"()))((()"
--- "()(())()"

 */
