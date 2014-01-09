
public class Solution {

    private static int[] fact = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880 };

    public String getPermutation(int n, int k) {
        // IMPORTANT: Please reset any member data you declared, as
        // the same Solution instance will be reused for each test case.
    	int[] can = new int[n];
    	for(int i = 0; i < n; i++) can[i] = i + 1;
    	int remainder = k - 1;
        int digit = n;

        String num = "";
        while( digit > 0) {
           int cur = remainder / fact[digit - 1];
           remainder = remainder % fact[digit - 1];
           digit--;
           
           num += String.valueOf(can[cur]);
           for(int j = cur; j < n - 1; j++) {
        	   can[j] = can[j + 1];
           }
        }

        return num;
    }

    public static void main(String[] args) {
        Solution so = new Solution();
        String s = so.getPermutation(3, 6);
        System.out.println(s);
    }


}
