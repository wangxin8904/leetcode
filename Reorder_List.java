
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    
    // n/2 + 1 
    public void reorderList(ListNode head) {
        ArrayList<ListNode> odds = new ArrayList<ListNode>();
        
        int len = 0;
        ListNode cur = head;
        while(cur != null) {
            len++;
            cur = cur.next;
        }
        
        if(len <= 2 ) return;
        
        int counter = (len / 2 + 1);
        ListNode part2 = head;
        for(int i = 1; i < counter; i++) {
        	part2 = part2.next;
        }
        cur = part2.next;
        part2.next = null;
        
        // reverse the latter part after len/2 + 1
        
        ListNode ll = null;
        while(cur != null) {
            ListNode nn = cur.next;
            cur.next = ll;
            ll = cur;
            cur = nn;
        }
        
        part2 = ll;
        while(part2 != null) {
            ListNode ne = head.next;
            head.next = part2;
            
            ListNode part2n = part2.next;
            part2.next = ne;
            part2 = part2n;
            
            head = ne;
        }
    }
}

