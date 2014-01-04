
public class Solution {
    // fisherlei.blogspot.com/2013/12/leetcode-sort-list-solution.html?
	private ListNode h = null;
	
    private ListNode sort(int len) {
        if(len == 1) {
            ListNode temp = h;
            h = h.next;
            temp.next = null;
            return temp;
        }
        
        ListNode leftH = sort(len / 2);
        ListNode rightH = sort(len - len/2);
        ListNode newH = merge(leftH, rightH);
        return newH;
    }
    
    private ListNode merge(ListNode left,ListNode right) {
        ListNode dummy = new ListNode(0);
        ListNode pt = dummy;
        
        while(left != null && right != null) {
            if(left.val <= right.val) {
                pt.next = left;
                left = left.next;
            } else {
                pt.next = right;
                right = right.next;
            }
            pt = pt.next;
        }
        
        if(left != null) pt.next = left;
        if(right != null) pt.next = right;
        
        return dummy.next;
    }
    
    public ListNode sortList(ListNode head) {
        ListNode l = head;
        int len = 0;
        while(l != null) {
            len++;
            l = l.next;
        }
        
        if(len == 0) return null;
        
        h = head;
        ListNode newHead = sort(len);
        
        return newHead;
    }    

}

