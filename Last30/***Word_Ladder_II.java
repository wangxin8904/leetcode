// Attemp 2
// refer: http://blog.csdn.net/pickless/article/details/12067703

public class Solution {
	class GNode {
		String str;
		int level;
		ArrayList<GNode> prev = null;
        
		GNode(int num, String val) {
			level = num;
			str = val;
			prev = new ArrayList<GNode>();
		}
	}
    
	private ArrayList<ArrayList<String>> result;
    
	private void getResult(GNode endNode, ArrayList<String> cur, GNode startNode) {
		if(endNode == startNode) {
			ArrayList<String> ans = new ArrayList<String>(cur);
			result.add(ans);
			return;
		}
		
		for(int i = 0; i < endNode.prev.size(); i++) {
			ArrayList<String> a = new ArrayList<String>(cur);
			GNode g = endNode.prev.get(i);
			a.add(0, g.str);
			getResult(g, a, startNode);
		}
	}
	
	public ArrayList<ArrayList<String>> findLadders(String start, String end,
                                                    HashSet<String> dict) {
		result = new ArrayList<ArrayList<String>>();
        
		Queue<GNode> queue = new LinkedList<GNode>();
		HashMap<String, GNode> map = new HashMap<String, GNode>();
		
		if(!dict.contains(start) || !dict.contains(end) || start.length() != end.length()) return result;
        
		GNode startNode = new GNode(1, start);
		queue.offer(startNode);
		boolean stop = false;
		
		while(!queue.isEmpty() && !stop) {
			int count = queue.size();  // number of nodes in this level
			
			for(int i = 0 ; i < count; i++) {
				GNode node = queue.poll();
				
				if(node.str.equals(end)) {
					ArrayList<String> cur = new ArrayList<String>(Arrays.asList(end));
					getResult(node, cur, startNode);
					stop = true;
					break;
				}
				
				for(int j = 0; j < node.str.length(); j++) {
					char[] arr = node.str.toCharArray();
					for(char c = 'a'; c < 'z'; c++) {
						if(c == node.str.charAt(j)) continue;
                        
						arr[j] = c;
						String temp = new String(arr);
						if(!dict.contains(temp)) continue;
						
						if(map.containsKey(temp)) {
							GNode g = map.get(temp);
							if(g.level == node.level + 1) g.prev.add(node);
						} else {
							GNode g = new GNode(node.level + 1, temp);
							map.put(temp, g);
							g.prev.add(node);
							queue.add(g);
						}
					}
					
				}
				
				
			}
			
		}
        
		return result;
	}
}



// Attempt 1, Naive BFS, TLE
// This method stores all possible paths. For eg. "cet net vet". But it shouldn't be a possibility because cet can jump directly to vet
/*
public class Solution {
	  
    class GNode {
		int no;
		String str;
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
    
    private ArrayList<ArrayList<String>> result;
    private int minDepth;
    private ArrayList<ArrayList<ArrayList<GNode>>> ware;
    
    private void bfs(String end) { 	  	
    	for(int i = 0; i < ware.size() && i < Math.pow(26, end.length()); i++) {   		
    		ArrayList<ArrayList<GNode>> curPaths = ware.get(i);
    		ArrayList<ArrayList<GNode>> nextLevel = new ArrayList<ArrayList<GNode>>();
    		
    		for(int t = 0; t < curPaths.size(); t++) {
    			
	    		ArrayList<GNode> path = curPaths.get(t);
	    		
	    		GNode top = path.get(path.size() - 1);
	    		for(int j = 0; j < top.neighbors.size(); j++) {
	
	    			GNode neighbor = top.neighbors.get(j);
	    			if(path.contains(neighbor)) continue;
	    			
	   			
	    			ArrayList<GNode> newPath = new ArrayList<GNode>(path);
	    			newPath.add(neighbor);
	    			nextLevel.add(newPath);
	    			
	    			if(neighbor.str.equals(end)) {
	    				if(i + 1 + 1 < minDepth) {
	    					minDepth = i + 1 + 1;
	    					result.clear();
	    				}
	    				
	    	            ArrayList<String> tmp = new ArrayList<String>();
	    	            for(GNode g : newPath) {
	    	            	tmp.add(g.str);
	    	            }
	    	            result.add(tmp);
	    			}
	    		}
	    		    		
    		}
    		if(i  + 1 + 1 < minDepth && nextLevel.size() > 0) 
    			ware.add(nextLevel);
    	}
    }
    
    public ArrayList<ArrayList<String>> findLadders(String start, String end, HashSet<String> dict) {
        result = new ArrayList<ArrayList<String>>();
        minDepth = Integer.MAX_VALUE;
        
        ArrayList<GNode> nodes = new ArrayList<GNode>();
    	
    	if(dict.contains(start)) {
    		GNode cur = new GNode(0);
    		cur.str = start;
    		nodes.add(cur);
    		dict.remove(start);
    	} else return result;
    	
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
    	
    	ware = new ArrayList<ArrayList<ArrayList<GNode>>>();
    	ArrayList<GNode> f = new ArrayList<GNode>();
    	f.add(nodes.get(0));
    	ArrayList<ArrayList<GNode>> ff = new ArrayList<ArrayList<GNode>>();
    	ff.add(f);
    	ware.add(ff);
    	
    	bfs(end);
    	
    	return result;
    }

    public static void main(String[] args) {
        Solution so = new Solution();
        
		String[] arr = {"dose","ends","dine","jars","prow","soap","guns","hops","cray","hove","ella","hour","lens","jive","wiry","earl","mara","part","flue","putt","rory","bull","york","ruts","lily","vamp","bask","peer","boat","dens","lyre","jets","wide","rile","boos","down","path","onyx","mows","toke","soto","dork","nape","mans","loin","jots","male","sits","minn","sale","pets","hugo","woke","suds","rugs","vole","warp","mite","pews","lips","pals","nigh","sulk","vice","clod","iowa","gibe","shad","carl","huns","coot","sera","mils","rose","orly","ford","void","time","eloy","risk","veep","reps","dolt","hens","tray","melt","rung","rich","saga","lust","yews","rode","many","cods","rape","last","tile","nosy","take","nope","toni","bank","jock","jody","diss","nips","bake","lima","wore","kins","cult","hart","wuss","tale","sing","lake","bogy","wigs","kari","magi","bass","pent","tost","fops","bags","duns","will","tart","drug","gale","mold","disk","spay","hows","naps","puss","gina","kara","zorn","boll","cams","boas","rave","sets","lego","hays","judy","chap","live","bahs","ohio","nibs","cuts","pups","data","kate","rump","hews","mary","stow","fang","bolt","rues","mesh","mice","rise","rant","dune","jell","laws","jove","bode","sung","nils","vila","mode","hued","cell","fies","swat","wags","nate","wist","honk","goth","told","oise","wail","tels","sore","hunk","mate","luke","tore","bond","bast","vows","ripe","fond","benz","firs","zeds","wary","baas","wins","pair","tags","cost","woes","buns","lend","bops","code","eddy","siva","oops","toed","bale","hutu","jolt","rife","darn","tape","bold","cope","cake","wisp","vats","wave","hems","bill","cord","pert","type","kroc","ucla","albs","yoko","silt","pock","drub","puny","fads","mull","pray","mole","talc","east","slay","jamb","mill","dung","jack","lynx","nome","leos","lade","sana","tike","cali","toge","pled","mile","mass","leon","sloe","lube","kans","cory","burs","race","toss","mild","tops","maze","city","sadr","bays","poet","volt","laze","gold","zuni","shea","gags","fist","ping","pope","cora","yaks","cosy","foci","plan","colo","hume","yowl","craw","pied","toga","lobs","love","lode","duds","bled","juts","gabs","fink","rock","pant","wipe","pele","suez","nina","ring","okra","warm","lyle","gape","bead","lead","jane","oink","ware","zibo","inns","mope","hang","made","fobs","gamy","fort","peak","gill","dino","dina","tier"};
		//String[] arr = {"kid","tag","pup","ail","tun","woo","erg","luz","brr","gay","sip","kay","per","val","mes","ohs","now","boa","cet","pal","bar","die","war","hay","eco","pub","lob","rue","fry","lit","rex","jan","cot","bid","ali","pay","col","gum","ger","row","won","dan","rum","fad","tut","sag","yip","sui","ark","has","zip","fez","own","ump","dis","ads","max","jaw","out","btu","ana","gap","cry","led","abe","box","ore","pig","fie","toy","fat","cal","lie","noh","sew","ono","tam","flu","mgm","ply","awe","pry","tit","tie","yet","too","tax","jim","san","pan","map","ski","ova","wed","non","wac","nut","why","bye","lye","oct","old","fin","feb","chi","sap","owl","log","tod","dot","bow","fob","for","joe","ivy","fan","age","fax","hip","jib","mel","hus","sob","ifs","tab","ara","dab","jag","jar","arm","lot","tom","sax","tex","yum","pei","wen","wry","ire","irk","far","mew","wit","doe","gas","rte","ian","pot","ask","wag","hag","amy","nag","ron","soy","gin","don","tug","fay","vic","boo","nam","ave","buy","sop","but","orb","fen","paw","his","sub","bob","yea","oft","inn","rod","yam","pew","web","hod","hun","gyp","wei","wis","rob","gad","pie","mon","dog","bib","rub","ere","dig","era","cat","fox","bee","mod","day","apr","vie","nev","jam","pam","new","aye","ani","and","ibm","yap","can","pyx","tar","kin","fog","hum","pip","cup","dye","lyx","jog","nun","par","wan","fey","bus","oak","bad","ats","set","qom","vat","eat","pus","rev","axe","ion","six","ila","lao","mom","mas","pro","few","opt","poe","art","ash","oar","cap","lop","may","shy","rid","bat","sum","rim","fee","bmw","sky","maj","hue","thy","ava","rap","den","fla","auk","cox","ibo","hey","saw","vim","sec","ltd","you","its","tat","dew","eva","tog","ram","let","see","zit","maw","nix","ate","gig","rep","owe","ind","hog","eve","sam","zoo","any","dow","cod","bed","vet","ham","sis","hex","via","fir","nod","mao","aug","mum","hoe","bah","hal","keg","hew","zed","tow","gog","ass","dem","who","bet","gos","son","ear","spy","kit","boy","due","sen","oaf","mix","hep","fur","ada","bin","nil","mia","ewe","hit","fix","sad","rib","eye","hop","haw","wax","mid","tad","ken","wad","rye","pap","bog","gut","ito","woe","our","ado","sin","mad","ray","hon","roy","dip","hen","iva","lug","asp","hui","yak","bay","poi","yep","bun","try","lad","elm","nat","wyo","gym","dug","toe","dee","wig","sly","rip","geo","cog","pas","zen","odd","nan","lay","pod","fit","hem","joy","bum","rio","yon","dec","leg","put","sue","dim","pet","yaw","nub","bit","bur","sid","sun","oil","red","doc","moe","caw","eel","dix","cub","end","gem","off","yew","hug","pop","tub","sgt","lid","pun","ton","sol","din","yup","jab","pea","bug","gag","mil","jig","hub","low","did","tin","get","gte","sox","lei","mig","fig","lon","use","ban","flo","nov","jut","bag","mir","sty","lap","two","ins","con","ant","net","tux","ode","stu","mug","cad","nap","gun","fop","tot","sow","sal","sic","ted","wot","del","imp","cob","way","ann","tan","mci","job","wet","ism","err","him","all","pad","hah","hie","aim","ike","jed","ego","mac","baa","min","com","ill","was","cab","ago","ina","big","ilk","gal","tap","duh","ola","ran","lab","top","gob","hot","ora","tia","kip","han","met","hut","she","sac","fed","goo","tee","ell","not","act","gil","rut","ala","ape","rig","cid","god","duo","lin","aid","gel","awl","lag","elf","liz","ref","aha","fib","oho","tho","her","nor","ace","adz","fun","ned","coo","win","tao","coy","van","man","pit","guy","foe","hid","mai","sup","jay","hob","mow","jot","are","pol","arc","lax","aft","alb","len","air","pug","pox","vow","got","meg","zoe","amp","ale","bud","gee","pin","dun","pat","ten","mob"};
        
		HashSet<String> dict = new HashSet<String>();
		
		for(int i = 0; i < arr.length; i++) {
			dict.add(arr[i]);
		}		
		
		ArrayList<ArrayList<String>> d = so.findLadders("nape", "mild", dict);
		//ArrayList<ArrayList<String>> d = so.findLadders("cet", "ism", dict);
		
		for(ArrayList<String> l : d) {
			for(String st : l) {
				System.out.print(st + " ");
			}
			System.out.println();
		}
		
		
   
    }  

}

*/
