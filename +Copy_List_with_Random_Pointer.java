

public class Solution {

    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode H = head;
        RandomListNode dummy = new RandomListNode(0);
        
        HashMap<RandomListNode, RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();
        
        // round 1, deep copy without random
        RandomListNode tail = dummy;
        while(head != null) {
            RandomListNode temp = new RandomListNode(head.label);
            
            map.put(head, temp);
            
            tail.next = temp;
            tail = temp;
            head = head.next;
        }
        
        // round 2, deal with random
        head = H;
        RandomListNode cur = dummy.next;
        while(head != null) {
            RandomListNode temp = map.get(head.random);
            cur.random = temp;
            
            head = head.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}







// Attempt 1, O(n^2)
// TLE 
/*

public class Solution {
    
    private RandomListNode getRLink(RandomListNode H, RandomListNode r, RandomListNode dupH) {
        while(H != r) {
            H = H.next;
            dupH = dupH.next;
        }
        return dupH;
    }
    
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode H = head;
        RandomListNode dummy = new RandomListNode(0);
        
        // round 1, deep copy without random
        RandomListNode tail = dummy;
        while(head != null) {
            RandomListNode temp = new RandomListNode(head.label);
            tail.next = temp;
            tail = temp;
            head = head.next;
        }
        
        // round 2, deal with random
        head = H;
        RandomListNode cur = dummy.next;
        while(head != null) {
            RandomListNode temp = getRLink(H, head.random, dummy.next);
            cur.random = temp;
            
            head = head.next;
            cur = cur.next;
        }
        
        return dummy.next;
    }
}

*/

