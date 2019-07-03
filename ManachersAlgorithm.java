import java.util.Scanner;

class Manachers{
	private String str;
	private int N;
	
	public Manachers(String str, int N) {
		this.str = makeStringOddLength(str, N);
		this.N = N;
	}
	
	public int getLengthOfLongestPalindromicSubstring() {
		int len = (2 * N) + 1;
		int[] P = new int[len];
		int c = 0; //stores the center of the longest palindromic substring until now
		int r = 0; //stores the right boundary of the longest palindromic substring until now
		int maxLen = 0;
		for(int i = 0; i < len; i++) {
			//get mirror index of i
			int mirror = (2 * c) - i;
			
			//see if the mirror of i is expanding beyond the left boundary of current longest palindrome at center c
			//if it is, then take r - i as P[i]
			//else take P[mirror] as P[i]
			if(i < r) {
				P[i] = Math.min(r - i, P[mirror]);
			}
			
			//expand at i
			int a = i + (1 + P[i]);
			int b = i - (1 + P[i]);
			while(a < len && b >= 0 && str.charAt(a) == str.charAt(b)) {
				P[i]++;
				a++;
				b--;
			}
			
			//check if the expanded palindrome at i is expanding beyond the right boundary of current longest palindrome at center c
			//if it is, the new center is i
			if(i + P[i] > r) {
				c = i;
				r = i + P[i];
				
				if(P[i] > maxLen) { //update maxlen
					maxLen = P[i];
				}
			}
		}
		return maxLen;
	}
	
	private String makeStringOddLength(String str, int N) {
		/*example
		"abc" -> "#a#b#c#" len = 7
		"ab" -> "#a#b#" len = 5
		We are adding n + 1 special characters to the given string
		So as to make it odd length always
		*/
		StringBuilder sb = new StringBuilder();
		char spl = '#'; //special character
		sb.append(spl); 
		for(int i = 0; i < N; i++) {
			sb.append(str.charAt(i));
			sb.append(spl);
		}
		return sb.toString();
	}
}

public class ManachersAlgorithm {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		Manachers manachers = new Manachers(str, str.length());
		System.out.println("Length of longest palindromic substring: " + manachers.getLengthOfLongestPalindromicSubstring());
		sc.close();
	}
}