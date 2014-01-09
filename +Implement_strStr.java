public class Solution {
    // refer: http://gongxuns.blogspot.com/2012/12/leetcodeimplement-strstr.html
    
    public String strStr(String haystack, String needle) {
        if(haystack == null || needle == null || haystack.length() < needle.length()) return null;
        if(needle.length() == 0 ) return haystack;
        
        int i = 0;
        while(i < haystack.length()) {
            if(haystack.length() - i < needle.length()) return null;
            
            int j  = i;
            while(j - i < needle.length() && needle.charAt(j - i) == haystack.charAt(j)) j++;
            
            if(j - i == needle.length()) return haystack.substring(i);
            
            i++;
        }
        
        return null;
    }
}