
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
public class Solution {
    // www.cnblogs.com/TenosDoIt/p/3444086.html
    
    class PointComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            if(p1.x != p2.x) return p1.x - p2.x;
            else {
                return p1.y - p2.y;
            }
        }
    }
    
    private int getNum(Point[] points, int idx) {
        HashMap<Float, Integer> map = new HashMap<Float, Integer>();
        
        for(int i = idx + 1; i < points.length; i++) {
            float slope = Float.MAX_VALUE;
            if(points[i].x != points[idx].x) {
                slope = (float)(points[i].y - points[idx].y) / (float)(points[i].x - points[idx].x);
            }
            
            if(map.containsKey(slope)) {
                map.put(slope, map.get(slope) + 1);
            } else {
                map.put(slope, 1);
            }
        }
        
        if(map.size() == 0) return 0;
        
        Object[] vs = map.values().toArray();
        int m = -1;
        for(int i = 0; i < vs.length; i++) {
            if((int)vs[i] > m) m = (int)vs[i];
        }
        return m;
    }
    
    public int maxPoints(Point[] points) {
        if(points.length < 1) return 0;
        
        Arrays.sort(points, new PointComparator());
        
        int maxNum = -1;
        PointComparator pc = new PointComparator();
        int duplicate = 0;
        
        int i = 0;
        while(i < points.length) {
            int next = i;
            while(next < points.length &&
                pc.compare(points[i], points[next]) == 0) {
                next++;
            }
            duplicate = next - i;
            
            int curNum = getNum(points, next - 1) + duplicate;
            if(curNum > maxNum) maxNum = curNum;
            
            i = next;
        }
        
        return maxNum;
    }
}

