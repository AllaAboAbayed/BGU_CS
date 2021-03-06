import java.util.Iterator;
import java.util.PriorityQueue;

public class PointDataStructure implements PDT {

	int heapSize;
    Point[] xSortedHeap;
    int numOfPointsInRange = 0;

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
        xSortedHeap = new Point[points.length];
        int maxYvalue = 0;

        //minheap.add(initialYMedianPoint);
        for (int i = 0; i < points.length; i++) {
            if (points[i].getX()>maxYvalue)
                maxYvalue = points[i].getX();
            addPoint(points[i]);
        }
        xSortedHeap = countingSort(points, maxYvalue);

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
            if(Integer.compare(n.getY(), median()) < 0){
                maxheap.add(n);}
            // if n is greater than current median, add to min heap
            else if (Integer.compare(n.getY(), median()) > 0){
                minheap.add(n);}

            else if (Integer.compare(n.getY(), median()) == 0){
                if(compare(n, medianPoint()) < 0 ){
                    System.out.println("n< med");
                    maxheap.add(n);
                }
                else if (compare(n, medianPoint()) > 0){
                    System.out.println("n>med");
                     minheap.add(n);
                }

            }
        }
        heapSize++;

        // fix the chaos, if any imbalance occurs in the heap sizes
        //i.e, absolute difference of sizes is greater than one.
        fixChaos();
	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
        int xStart = 0;
        Point[] pointsInRange = new Point[XRight - XLeft + 1];
        if (XLeft <= xSortedHeap[0].getX()){
            xStart = xSortedHeap[0].getX();
        }
        else
            xStart = findX(XLeft, xSortedHeap);
        if (XRight > xSortedHeap[xSortedHeap.length-1].getX())
            XRight = xSortedHeap[xSortedHeap.length-1].getX();

        for (int i = 0; i + xStart < xSortedHeap.length ; i++) {
            if (xSortedHeap[xStart + i ].getX() <=XRight){
                pointsInRange[i] = xSortedHeap[xStart + i];
                numOfPointsInRange++;
            }

        }
        return  copyArry(pointsInRange, numOfPointsInRange);

	}

    private Point[] copyArry(Point[] pointsInRange, int numOfPointsInRange) {

        Point[] copiedArray = new Point[numOfPointsInRange];
        for (int i = 0; i < numOfPointsInRange; i++) {
            copiedArray[i] = pointsInRange[i];
        }
        return copiedArray;
    }

    private int findX(int xLeft, Point[] xSortedHeap) {
        int index = 0;
        int low = 0;
        int high = xSortedHeap.length - 1;
        while (low <= high){
            int middle = low + (high - low)/2;
            if (xSortedHeap[middle].getX() <= xLeft)
                index = middle;
            if (xSortedHeap[middle].getX() == xLeft)
                return index;
            if (xSortedHeap[middle].getX() > xLeft)
                high = middle;
            else if (xSortedHeap[middle].getX() < xLeft)
                low = middle;
        }
        return index;
    }

    @Override
	public int numOfPointsInRange(int XLeft, int XRight) {
        int xStart, xEnd;

        if (XLeft <= xSortedHeap[0].getX()){
            xStart = xSortedHeap[0].getX();
        }
        else
            xStart = findX(XLeft, xSortedHeap);
        if (XRight > xSortedHeap[xSortedHeap.length-1].getX())
            xEnd = xSortedHeap[xSortedHeap.length-1].getX();
        else
            xEnd = findX(XRight,xSortedHeap);
        return xEnd - xStart +1 ;

        /*int counter=0;
        if (numOfPointsInRange == 0){
            if (XLeft <= xSortedHeap[0].getX()){
                xStart = xSortedHeap[0].getX();
            }
            else
                xStart = findX(XLeft, xSortedHeap);
            if (XRight > xSortedHeap[xSortedHeap.length-1].getX())
                xEnd = xSortedHeap[xSortedHeap.length-1].getX();
            else
                xEnd = findX(XRight,xSortedHeap);

            for (int i = 0; i <= xEnd - xStart ; i++) {
                counter++;
            }
            return counter;
        }*/
		//return getPointsInRange(XLeft,XRight).length;
	}

	@Override
	public double averageHeightInRange(int XLeft, int XRight) {
        int xStart = findX(XLeft, xSortedHeap);
        double avg = 0;
        int tot=0;
        for (int i = 0; i < xSortedHeap.length && xSortedHeap[i+xStart].getX()<= XRight; i++) {
            if (xSortedHeap[xStart + i ].getX() <=XRight){
                avg += xSortedHeap[xStart + i].getY();
                tot++;
            }

        }
        return ((double) (avg/tot));
	}

	@Override
	public void removeMedianPoint() {
        if( maxheap.size() == minheap.size()) {
            if (compare(maxheap.peek(), minheap.peek()) == 1)
                 maxheap.remove();
            else  minheap.remove();


            // if (maxheap.peek().getY() >= minheap.peek().getY() && maxheap.peek().getX() >= minheap.peek().getX())
            //return ((double)maxheap.peek().getY() + (double)minheap.peek().getY())/2 ;
        }
        //else median is middle element, i.e, root of the heap with one element more
        else if (maxheap.size() > minheap.size()){  maxheap.remove();}
        else{  minheap.remove();}
        heapSize--;
        fixChaos();

    }

    @Override
    public Point[] getMedianPoints(int k) {

        Point [] medianPoints = new Point[k];
        Iterator<Point> maxIter =    maxheap.iterator();
        Iterator<Point> minIter = minheap.iterator();
        int start = (int) (heapSize/2 - Math.ceil((double)(k-1)/2));
        int end = (int) (heapSize/2 + Math.floor((k-1)/2));
        int flag = 0;
        int i = 0;

        while (start <=end){

            if (flag == 1){
                medianPoints[i] = minIter.next();
                end--;
                i++;
                flag = -1;
            }
            else if (flag == -1){
                medianPoints[i] = maxIter.next();
                start++;
                i++;
                flag = 1;
            }
            else{

                if( maxheap.size() == minheap.size() && flag == 0) {
                    if (compare(maxheap.peek(), minheap.peek()) == 1){
                        medianPoints[i] = maxIter.next();
                        start++;
                        i++;
                        flag = 1;
                    }
                    else {
                        medianPoints[i] = minIter.next();
                        end--;
                        i++;
                        flag = -1;
                    }

                }
                else if (maxheap.size() > minheap.size() && flag == 0){
                    medianPoints[i] = maxIter.next();
                    start++;
                    i++;
                    flag = 1;

                }
                else{  medianPoints[i] = minIter.next();
                    end--;
                    i++;
                    flag = -1;
                }
            }
        }
        return medianPoints;
    }

	@Override
	public Point[] getAllPoints() {
        Point[] points = new Point[maxheap.size()+minheap.size()];
        Iterator<Point> maxIter =    maxheap.iterator();
        Iterator<Point> minIter = minheap.iterator();
        for (int i = 0; i < maxheap.size(); i++) {
            points[i] = maxIter.next();
        }
        for (int i = maxheap.size(); i < minheap.size()+maxheap.size(); i++) {
            points[i] = minIter.next();
        }
        return points;
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
    public int median(){
        //if total size(no. of elements entered) is even, then median iss the average of the 2 middle elements
        //i.e, average of the root's of the heaps.

        if( maxheap.size() == minheap.size()) {
            if (compare(maxheap.peek(), minheap.peek()) == 1)
                return maxheap.peek().getY();
            else return minheap.peek().getY();


            // if (maxheap.peek().getY() >= minheap.peek().getY() && maxheap.peek().getX() >= minheap.peek().getX())
            // return ((double)maxheap.peek().getY() + (double)minheap.peek().getY())/2 ;
        }
        //else median is middle element, i.e, root of the heap with one element more
        else if (maxheap.size() > minheap.size()){ return maxheap.peek().getY();}
        else{ return minheap.peek().getY();}

    }
    public Point medianPoint(){
        //if total size(no. of elements entered) is even, then median iss the average of the 2 middle elements
        //i.e, average of the root's of the heaps.

        if( maxheap.size() == minheap.size()) {
            if (compare(maxheap.peek(), minheap.peek()) == 1)
                return maxheap.peek();
            else return minheap.peek();


            // if (maxheap.peek().getY() >= minheap.peek().getY() && maxheap.peek().getX() >= minheap.peek().getX())
            //return ((double)maxheap.peek().getY() + (double)minheap.peek().getY())/2 ;
        }
        //else median is middle element, i.e, root of the heap with one element more
        else if (maxheap.size() > minheap.size()){ return maxheap.peek();}
        else{ return minheap.peek();}

    }

    @Override
    public String toString() {
        return "PointDataStructure{" +
                "minheap=" + minheap +
                ", maxheap=" + maxheap +
                '}';
    }

    private int compare(Point i, Point j){

        int result;
        if (i.getY() > j.getY())
            result = 1;
        else  if (i.getY() == j.getY()){
            if (i.getX() > j.getX())
                result = 1;
            else if (i.getX()<j.getX())
                result =-1;
            else result=0;
        }
        else result=-1;
        return result;
    }

    public Point[] pointsArray(){
        Point[] points = new Point[maxheap.size()+minheap.size()];
        Iterator<Point> maxIter =    maxheap.iterator();
        Iterator<Point> minIter = minheap.iterator();
        for (int i = 0; i < maxheap.size(); i++) {
            points[i] = maxIter.next();
        }
        for (int i = maxheap.size(); i < minheap.size()+maxheap.size(); i++) {
            points[i] = minIter.next();
        }
        return points;
    }

    public Point[] countingSort(Point[] a, int k) {
        int c[] = new int[k+1];
        for (int i = 0; i < k; i++) {
            c[i] = 0;
        }

        for (int i = 0; i < a.length; i++)
            c[a[i].getX()]++;

        for (int i = 1; i <= k; i++)
            c[i] += c[i-1];

        Point b[] = new Point[a.length];
        for (int i = a.length-1; i >= 0; i--)
            b[--c[a[i].getX()]] = a[i];
        return b;
    }
}

