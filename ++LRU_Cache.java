// Attempt 2, HashMap + double linked list
public class LRUCache {
    // www.cnblogs.com/TenosDoIt/p/3417157.html
    
    
    class CacheNode {
        int key;
        int value;
        CacheNode prev;
        CacheNode next;
        
        CacheNode(int k, int v) {
            key = k;
            value = v;
        }
    }
    
    // hashmap + double Linked list
    CacheNode dummy = null;
    HashMap<Integer, CacheNode> map = null;
    int LIMIT;
     
    public LRUCache(int capacity) {
        dummy = new CacheNode(-1, -1);
        map = new HashMap<Integer, CacheNode>();
        LIMIT = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
        	CacheNode cur = map.get(key);
        	
        	// move cur to head of linked list
        	cur.prev.next = cur.next;
        	if(cur.next != null) cur.next.prev = cur.prev;
        	
        	cur.prev = dummy;
        	cur.next = dummy.next;
        	dummy.next = cur;
        	if(cur.next != null) cur.next.prev = cur;
        	
            return cur.value;
        } else {
            return -1;
        }
    }
    
    public void set(int key, int value) {
        if(get(key) != -1) {
            CacheNode cur = map.get(key);
            cur.value = value;
            
        	// move cur to head of linked list
        	cur.prev.next = cur.next;
        	if(cur.next != null) cur.next.prev = cur.prev;
        	
        	cur.prev = dummy;
        	cur.next = dummy.next;
        	dummy.next = cur;
        	if(cur.next != null) cur.next.prev = cur;
        } else {
            if(map.size() == LIMIT) {
            	// remove the last element
                CacheNode temp = dummy.next;
                while(temp.next != null) temp = temp.next;
                
                temp.prev.next = null;
                map.remove(temp.key);
            }
            
            CacheNode cur = new CacheNode(key, value);
           
            cur.prev = dummy;
            cur.next = dummy.next;
            dummy.next = cur;
            if(cur.next != null) cur.next.prev = cur;
            
            map.put(key, cur);
        }

    }
}





// Attempt 1, use HashMap, TLE
//
/* 
public class LRUCache {
    HashMap<Integer, Integer> map = null;
    int LIMIT;
    int minKey, minVal;
    
    public LRUCache(int capacity) {
        map = new HashMap<Integer, Integer>();
        minKey = -1; 
        minVal = -1;
        LIMIT = capacity;
    }
    
    public int get(int key) {
        if(map.containsKey(key)) {
            return map.get(key);
        } else return -1;
    }
    
    private void setMin() {
        Object[] keys = map.keySet().toArray();
        Object[] values = map.values().toArray();
        
        if(values.length == 0) {
            this.minVal = -1;
            this.minKey = -1;
            return;
        }
        
        int min = Integer.MAX_VALUE;
        int idx = -1;
        for(int i = 0; i < values.length; i++) {
            if((int)values[i] < min) {
                min = (int)values[i];
                idx = i;
            }
        }
        
        this.minVal = min;
        this.minKey = (int)keys[idx];
    }
    
    public void set(int key, int value) {
        if(get(key) != -1) { // already exist. no remove
            map.put(key, value);
            if(minVal == -1 || value < minVal) {
                minVal = value;
                minKey = key;
            }
        } else { // check capacity
            if(map.size() == LIMIT) { //remove least one
                map.remove(minKey);
                setMin();
            }
            
            map.put(key,value);
            if(minVal == -1 || value < minVal) {
                minVal = value;
                minKey = key;
            }
        }
    }
}
*/
