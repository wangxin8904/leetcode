

public class Solution {
    public ListNode insertionSortList(ListNode head) {
        ListNode dummy = new ListNode(0);
        
        ListNode cur = head;
        while(cur != null) {
                ListNode pt = dummy;
                while(pt.next != null && pt.next.val < cur.val) {
                    pt = pt.next;
                }
                ListNode temp = cur;
                cur = cur.next;
                temp.next = pt.next;
                pt.next = temp;
        }
        
        
        return dummy.next;
    }
}

