import java.util.*;
import java.util.ArrayList;

// A better solution http://yucoding.blogspot.com/2013/04/leetcode-question-69-permutations.html

// My solution. Notice: the input should not have duplicates
public class Permutations {

    private void dfs(int idx, ArrayList<Integer> track,  ArrayList<ArrayList<Integer>> res, int[] num) {
	track.add(num[idx]);
	
	if(track.size() >= num.length) {
		ArrayList<Integer> cur = new ArrayList<Integer>(track);
		res.add(cur);
		track.remove(track.size() - 1);
		return;
	} 

	for(int i = 0; i < num.length; i++) {
		if(track.contains(num[i])) continue;
		else {
			dfs(i, track, res, num);
		}
	}

	track.remove(track.size() - 1);

    }
    
    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        // Note: The Solution object is instantiated only once and is reused by each test case.
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
	ArrayList<Integer> tr = new ArrayList<Integer>();
	
	for(int i = 0; i < num.length; i++) {
		dfs(i, tr, res, num);
	} 
	return res;
    }


    public static void main(String[] args) {
	int[] num = {1, 2, 3};
	Permutations so = new Permutations();
	ArrayList<ArrayList<Integer>> res = so.permute(num);

	for(int i = 0; i < res.size(); i++) {
		ArrayList<Integer> t = res.get(i);

		for(Integer m : t) {
			System.out.print(m + " ");
		}
		System.out.println();
	}  
  }
}
