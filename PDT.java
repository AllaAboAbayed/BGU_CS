
//////////////// DON'T CHANGE THIS FILE ////////////////

public interface PDT 
{
	
	//O(log n). Will be called up to log n times
	void addPoint(Point point); 
	
	//O(log n + k)
	Point[] getPointsInRange(int XLeft, int XRight);
	
	//O(log n)
	int numOfPointsInRange(int XLeft, int XRight);
	
	//O(log n)
	double averageHeightInRange(int XLeft, int XRight);

	//O(log n). Will be called up to log n times
	void removeMedianPoint(); 
	
	//O(log n)
	Point[] getMedianPoints(int k);
	
	//O(n)
	Point[] getAllPoints();

}
