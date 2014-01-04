



// Attempt 1, naive method, TLE

public class Solution {
	
	class GNode {
		int no;
		String str;
		int level;
		ArrayList<GNode> neighbors = null;
		
		GNode(int num) {
			no = num;
			neighbors = new ArrayList<GNode>();
		}
	}
	
	private int distance(String s1, String s2) {
		int d = 0;
		for(int i = 0; i < s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) { 
				if(d == 0) d++;
				else return -1;
			}
		}
		return d;
	}

    public int ladderLength(String start, String end, HashSet<String> dict) {
    	ArrayList<GNode> nodes = new ArrayList<GNode>();
    	
    	if(dict.contains(start)) {
    		GNode cur = new GNode(0);
    		cur.str = start;
    		nodes.add(cur);
    		dict.remove(start);
    	} else return 0;
    	
    	Iterator<String> iter = dict.iterator();
    	int count = 1;
    	while(iter.hasNext()) {
    		String cur = iter.next();
    		GNode temp = new GNode(count++);
    		temp.str = cur;
    		nodes.add(temp);
    	}
    	
    	for(int i = 0; i < nodes.size(); i++) {
    		for(int j = i + 1; j < nodes.size(); j++) {
    			if(distance(nodes.get(i).str, nodes.get(j).str) == 1) {
    				nodes.get(i).neighbors.add(nodes.get(j));
    				nodes.get(j).neighbors.add(nodes.get(i));
    			}
    		}
    	}
    	
    	Queue<GNode> queue = new LinkedList<GNode>();
    	HashSet<GNode> set = new HashSet<GNode>();
    	nodes.get(0).level = 1;
    	queue.offer(nodes.get(0));
    	set.add(nodes.get(0));
    	
    	while(!queue.isEmpty()) {
    		GNode cur = queue.poll();
    		
    		for(int i = 0; i < cur.neighbors.size(); i++) {
    			GNode temp = cur.neighbors.get(i);
    			
    			if(set.contains(temp)) continue;
    			else set.add(temp);
    			
    			if(temp.str.equals(end)) {
    				return cur.level + 1;
    			}
    			
    			temp.level = cur.level + 1;
    			queue.add(temp);
    		}
    		
    	}
    	
    	return 0;
    }


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution so = new Solution();
		
		String[] arr = {"dose","ends","dine","jars","prow","soap","guns","hops","cray","hove","ella","hour","lens","jive","wiry","earl","mara","part","flue","putt","rory","bull","york","ruts","lily","vamp","bask","peer","boat","dens","lyre","jets","wide","rile","boos","down","path","onyx","mows","toke","soto","dork","nape","mans","loin","jots","male","sits","minn","sale","pets","hugo","woke","suds","rugs","vole","warp","mite","pews","lips","pals","nigh","sulk","vice","clod","iowa","gibe","shad","carl","huns","coot","sera","mils","rose","orly","ford","void","time","eloy","risk","veep","reps","dolt","hens","tray","melt","rung","rich","saga","lust","yews","rode","many","cods","rape","last","tile","nosy","take","nope","toni","bank","jock","jody","diss","nips","bake","lima","wore","kins","cult","hart","wuss","tale","sing","lake","bogy","wigs","kari","magi","bass","pent","tost","fops","bags","duns","will","tart","drug","gale","mold","disk","spay","hows","naps","puss","gina","kara","zorn","boll","cams","boas","rave","sets","lego","hays","judy","chap","live","bahs","ohio","nibs","cuts","pups","data","kate","rump","hews","mary","stow","fang","bolt","rues","mesh","mice","rise","rant","dune","jell","laws","jove","bode","sung","nils","vila","mode","hued","cell","fies","swat","wags","nate","wist","honk","goth","told","oise","wail","tels","sore","hunk","mate","luke","tore","bond","bast","vows","ripe","fond","benz","firs","zeds","wary","baas","wins","pair","tags","cost","woes","buns","lend","bops","code","eddy","siva","oops","toed","bale","hutu","jolt","rife","darn","tape","bold","cope","cake","wisp","vats","wave","hems","bill","cord","pert","type","kroc","ucla","albs","yoko","silt","pock","drub","puny","fads","mull","pray","mole","talc","east","slay","jamb","mill","dung","jack","lynx","nome","leos","lade","sana","tike","cali","toge","pled","mile","mass","leon","sloe","lube","kans","cory","burs","race","toss","mild","tops","maze","city","sadr","bays","poet","volt","laze","gold","zuni","shea","gags","fist","ping","pope","cora","yaks","cosy","foci","plan","colo","hume","yowl","craw","pied","toga","lobs","love","lode","duds","bled","juts","gabs","fink","rock","pant","wipe","pele","suez","nina","ring","okra","warm","lyle","gape","bead","lead","jane","oink","ware","zibo","inns","mope","hang","made","fobs","gamy","fort","peak","gill","dino","dina","tier"};
		
		HashSet<String> dict = new HashSet<String>();
		
		for(int i = 0; i < arr.length; i++) {
			dict.add(arr[i]);
		}
		
		int d = so.ladderLength("nape", "mild", dict);
		
		System.out.println(d);
		
	}

}


