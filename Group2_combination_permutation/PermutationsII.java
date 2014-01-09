import java.util.ArrayList;
import java.util.*;
public class PermutationsII {

	private void print(ArrayList<Integer> track) {
		for(Integer q : track) {
			System.out.print(q + "  ");
		}
		System.out.println();
	}

    // use recursion. First sort the array
    // when there are duplicates, the latter ones must be used only if the first ones have been used
    // see http://blog.csdn.net/pickless/article/details/9790649
    private void dfs(boolean[] num, int[] n, ArrayList<Integer> track, ArrayList<ArrayList<Integer>> res ) {
    		//print(track);
            if(track.size() == num.length) {
                    ArrayList<Integer> cur = new ArrayList<Integer>(track);
                    res.add(cur);
            } else {
                    for(int i = 0; i < num.length; i++) {
                            if(num[i])  continue;
                            if(i >= 1 && n[i] == n[i - 1] && !num[i - 1]) continue;
                            num[i] = true; track.add(n[i]);
                            dfs(num, n, track,  res);
                            num[i] = false; track.remove(track.size() - 1);
                    }
            }

            return;
    }

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] n) {
            Arrays.sort(n);
            ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
            ArrayList<Integer> tr = new ArrayList<Integer>();
            boolean[] num = new boolean[n.length];

            for(int i = 0; i < n.length; i++) {
                    if( (i == 0) || (i >= 1 && n[i] != n[i - 1] )) {
                            if(num[i] == false) {
                                    num[i] = true; tr.add(n[i]);
                                    dfs(num,n,tr, res);
                                    num[i] = false; tr.remove(tr.size() - 1);
                            }
                    }
            }

            return res;
    }

	public static void main(String[] args) {
		int[] n = { 1, 1, 2};
		PermutationsII s = new PermutationsII();
		ArrayList<ArrayList<Integer>> t = s.permuteUnique(n);

		
		System.out.println("FINAL " + t.size());
		for(int i = 0; i < t.size(); i++) {
			ArrayList<Integer> m = t.get(i);
			for(Integer q : m) {
				System.out.print(q + "  ");
			}
			System.out.println();
		}
		
	}
}
