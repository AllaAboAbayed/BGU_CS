/**
 * This class represents an arithmetic addition operation
 */

import java.util.Comparator;
        import java.util.PriorityQueue;
        import java.util.Queue;

public class Heap {


    public int heapSize = 0;
    public Point[] mHeap;

    /*public Queue<Point> minHeap;
    public Queue<Point> maxHeap;
    public int numOfElements;

    public Heap() {
        Comparator<Point> comparator = new MaxHeapComparator();
        minHeap = new PriorityQueue<Point>();
        maxHeap = new PriorityQueue<Point>(10, comparator);
        numOfElements = 0;
    }

    public void addPointToStream(Point p) {
        maxHeap.add(p);
        if (numOfElements % 2 == 0) {
            if (minHeap.isEmpty()) {
                numOfElements++;
                return;
            } else if (maxHeap.peek().getY() > minHeap.peek().getY()) {
                Point maxHeapRoot = maxHeap.poll();
                Point minHeapRoot = minHeap.poll();
                maxHeap.add(minHeapRoot);
                minHeap.add(maxHeapRoot);
            }
            else if (maxHeap.peek().getY() == minHeap.peek().getY() && maxHeap.peek().getX() > minHeap.peek().getX() ){
                System.out.println("Y values are identical ");
                Point maxHeapRoot = maxHeap.poll();
                Point minHeapRoot = minHeap.poll();
                maxHeap.add(minHeapRoot);
                minHeap.add(maxHeapRoot);
            }
        } else {
            minHeap.add(maxHeap.poll());
        }
        numOfElements++;
    }

    public Double getMedian() {
        if (numOfElements % 2 != 0)
            return new Double(maxHeap.peek().getY());
        else
            return (maxHeap.peek().getY() + minHeap.peek().getY()) / 2.0;
    }*/
}

