import java.util.PriorityQueue;

public class PointDataStructure implements PDT {

	int heapSize;

	Point[] minHeap;
	Point[] maxHeap;
	int numOfElements;
	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public PointDataStructure(Point[] points, Point initialYMedianPoint) {

		MedianHeap medianHeap = new MedianHeap();
		medianHeap.insert(initialYMedianPoint);


	}

	@Override
	public void addPoint(Point point) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Point[] getPointsInRange(int XLeft, int XRight) {
		// TODO Auto-generated method stub
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

	//TODO: add members, methods, etc.
	
}

