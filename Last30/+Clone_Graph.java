
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     ArrayList<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if(node == null ) return null;
        
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = 
        		new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        
        LinkedList<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
        queue.offer(node);
        
        // construct node, regardless edges
        while(!queue.isEmpty()) {
        	UndirectedGraphNode cur = queue.poll();
        	if(map.containsKey(cur)) {
        		continue;
        	} 
        	
        	for(int i = 0; i < cur.neighbors.size(); i++) {
        		UndirectedGraphNode temp = cur.neighbors.get(i);
        		queue.offer(temp);
        	}
        	
        	UndirectedGraphNode copy = new UndirectedGraphNode(cur.label);
        	map.put(cur, copy);
        }
        
        // duplicate edges
        HashSet<UndirectedGraphNode> set = new HashSet<UndirectedGraphNode>();
        queue.offer(node);
        while(queue.isEmpty() == false) {
        	UndirectedGraphNode cur = queue.poll();
        	if(set.contains(cur)) 
        		continue;
        	else 
        		set.add(cur);
        	
        	UndirectedGraphNode copy = map.get(cur);
        	for(int i = 0; i < cur.neighbors.size(); i++) {   		
        		UndirectedGraphNode i_copy = map.get( cur.neighbors.get(i) );
        		copy.neighbors.add(i_copy);
        		queue.add(cur.neighbors.get(i));
        	}
        }
        
        return map.get(node);
    }
}

