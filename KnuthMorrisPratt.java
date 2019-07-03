import java.util.ArrayList;

class KMP{
    private String text;
    private int N; //length of text
    private String pattern;
    private int M; //length of pattern
    private int[] lps;

    public KMP(String text, String pattern){
        this.text = text;
        N = text.length();
        this.pattern = pattern;
        M = pattern.length();
        lps = populateLPSArray();
    }

    public int getFrequencyOfPatternInText(){
        int i = 0;
        int j = 0;
        int count = 0;
        while(i < N){
            if(text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            if(j == M){
                count++;
                j = lps[j - 1];
            }
            if(i < N && text.charAt(i) != pattern.charAt(j)){
                if(j != 0){
                    j = lps[j - 1];
                }
                else{
                    i++;
                }
            }
        }
        return count;
    }
    
    public ArrayList<Integer> getStartIndexOfOccurenceOfPatternInText(){
    	ArrayList<Integer> list = new ArrayList<>();
        int i = 0;
        int j = 0;
        while(i < N){
            if(text.charAt(i) == pattern.charAt(j)){
                i++;
                j++;
            }
            if(j == M){
                list.add(i - j);
                j = lps[j - 1];
            }
            else if(i < N && text.charAt(i) != pattern.charAt(j)){
                if(j != 0){
                    j = lps[j - 1];
                }
                else{
                    i++;
                }
            }
        }
        return list;
    }

    private int[] populateLPSArray(){
        lps = new int[M];
        int i = 1;
        int len = 0;
        while(i < M){
            if(pattern.charAt(i) == pattern.charAt(len)){
                len++;
                lps[i] = len;
                i++;
            }
            else{
                if(len == 0){
                    lps[i] = 0;
                    i++;
                }
                else{
                    len = lps[len - 1];
                }
            }
        }
        return lps;
    }
}

public class KnuthMorrisPratt{
    public static void main(String[] args){
        //example
        String pattern = "smart";
        String text = "yekicmsmartplrplsmartrplplmrpsmartrpsmartwmrmsmartsmart";

        KMP kmp = new KMP(text, pattern);

        System.out.println("Frequency of pattern: " + kmp.getFrequencyOfPatternInText());
        ArrayList<Integer> list = kmp.getStartIndexOfOccurenceOfPatternInText();
        System.out.println("First index of all matches: " + list.toString());
    }
}