import java.util.PriorityQueue;

public class PointDataStructure implements PDT {

	int heapSize;
    int median ;

    //stores all the numbers less than the current median in a maxheap, i.e median is the maximum, at the root
    private PriorityQueue<Point> maxheap;
    //stores all the numbers greater than the current median in a minheap, i.e median is the minimum, at the root
    private PriorityQueue<Point> minheap;

    //comparators for PriorityQueue
    private static final maxHeapComparator myMaxHeapComparator = new maxHeapComparator();
    private static final minHeapComparator myMinHeapComparator = new minHeapComparator();

    //////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint) {
        maxheap = new PriorityQueue<Point>(11,myMaxHeapComparator);
        minheap = new PriorityQueue<Point>(11,myMinHeapComparator);

        minheap.add(initialYMedianPoint);
        for (int i = 0; i < points.length; i++) {
            addPoint(points[i]);
        }

	}

    /**
     * Inserts into MedianHeap to update the median accordingly
     * @param n
     */
	@Override
	public void addPoint(Point n) {

        // initialize if empty
        if(isEmpty()){ minheap.add(n);}
        else{
            //add to the appropriate heap
            // if n is less than or equal to current median, add to maxheap
            if(Double.compare(n.getY(), median()) < 0){maxheap.add(n);}
            // if n is greater than current median, add to min heap
            else if (Double.compare(n.getY(), median()) > 0){minheap.add(n);}
        }
        heapSize++;

        // fix the chaos, if any imbalance occurs in the heap sizes
        //i.e, absolute difference of sizes is greater than one.
        fixChaos();

	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
        Point[] pointsInRange = new Point[XRight - XLeft + 1];
		return null;
	}

	@Override
	public int numOfPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void removeMedianPoint() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point[] getMedianPoints(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] getAllPoints() {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * Returns empty if no median i.e, no input
     * @return
     */
    private boolean isEmpty(){
        return maxheap.size() == 0 && minheap.size() == 0 ;
    }
    /**
     * Re-balances the heap sizes
     */
    private void fixChaos(){
        //if sizes of heaps differ by 2, then it's a chaos, since median must be the middle element
        if( Math.abs( maxheap.size() - minheap.size()) > 1){
            //check which one is the culprit and take action by kicking out the root from culprit into victim
            if(maxheap.size() > minheap.size()){
                minheap.add(maxheap.poll());
            }
            else{ maxheap.add(minheap.poll());}
        }
    }

    /**
     * returns the median of the numbers encountered so far
     * @return
     */
    public double median(){
        //if total size(no. of elements entered) is even, then median iss the average of the 2 middle elements
        //i.e, average of the root's of the heaps.
        if( maxheap.size() == minheap.size()) {
            return ((double)maxheap.peek().getY() + (double)minheap.peek().getY())/2 ;
        }
        //else median is middle element, i.e, root of the heap with one element more
        else if (maxheap.size() > minheap.size()){ return (double)maxheap.peek().getY();}
        else{ return (double)minheap.peek().getY();}

    }

    @Override
    public String toString() {
        return "PointDataStructure{" +
                "minheap=" + minheap +
                ", maxheap=" + maxheap +
                '}';
    }
}

