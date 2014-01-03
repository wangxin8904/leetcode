
// Attempt 1 : greedy algo
public class Solution {
    private String pack(String[] words, int L, int start, int itemNum) {
        int len = 0;
        for(int i = 0; i < itemNum; i++) {
            len += words[start + i].length();
        }
        
        int spaceNum = 0;
        if(itemNum > 1) spaceNum = (L - len) / (itemNum - 1);
        
        int extra = 0;
        if(itemNum > 1) extra = (L - len) % (itemNum - 1);
        
        String res = "";
        
        for(int i = 0; i < itemNum; i++) {
            res += words[start + i];
            if(i < itemNum - 1) {
                for(int j = 0; j < spaceNum; j++) res += " ";
                
                if(i < extra) res += " ";
            }
        }
        while(res.length() < L) res += " ";
        
        return res;
    }
    
    public ArrayList<String> fullJustify(String[] words, int L) {
        ArrayList<String> ans = new ArrayList<String>();
        if(words.length < 1) return ans;
        
        int start = 0;
        int itemNum = 1;
        int lenSum = words[0].length();
        for(int i = 1; i < words.length; i++) {
            if(words[i].length() + lenSum + 1 <= L) {
                itemNum++;
                lenSum += 1 + words[i].length();
            } else {
                String temp = pack(words, L, start, itemNum);
                ans.add(temp);
                start = i;
                itemNum = 1;
                lenSum = words[i].length();
            }
        }
        
        // last line
        String last = "";
        for(int i = start; i < words.length; i++) {
            last += words[i];
            if(i < words.length - 1) last += " ";
        }
        while(last.length() < L) last += " ";
        ans.add(last);
        
        return ans;
    }
}


