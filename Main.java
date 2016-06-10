import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;



public class Main {

	public static void main(String[] args) 
	{
		Point[] points = {
				new Point(0, 0),
				new Point(3, 1),
				new Point(2, 3),
				new Point(1, 2),
				new Point(4, 4),
		};

		PointDataStructure pointDataStructure = new PointDataStructure(points, points[2]);


		//testA();
		//testB();
		//testC();
	}

	
	private static void testA()
	{
		Point[] points = {
				new Point(0, 0), 
				new Point(3, 1),
				new Point(2, 3),
				new Point(1, 2),
				new Point(4, 4),
				};
		
		PDT pdt=new PointDataStructure(points,points[3]);
		String testName;
		int expected;
		int result;
		
		testName = "A0";
		testExpectedPoints(testName, points, pdt.getAllPoints());

		int XLeft=3;
		int XRight=7;
		testName = "A1";
		expected = 2;
		result = pdt.numOfPointsInRange(XLeft, XRight);
		testExpected(testName, expected, result);
		
		XLeft=1;
		XRight=3;
		testName = "A2";
		double expectedD = 2.0;
		double resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		
		testName = "A3.1";
		Point[] expectedPoints1 = {
				new Point(1, 2),
				};
		testExpectedPoints(testName, expectedPoints1, pdt.getMedianPoints(1));
		testName = "A3.2";
		Point[] expectedPoints2 = { 
				new Point(3, 1),
				new Point(1, 2),
				};
		testExpectedPoints(testName, expectedPoints2, pdt.getMedianPoints(2));
		testName = "A3.3";
		Point[] expectedPoints3 = { 
				new Point(3, 1),
				new Point(2, 3),
				new Point(1, 2),
				};
		testExpectedPoints(testName, expectedPoints3, pdt.getMedianPoints(3));
		
		Point[] points2 = {
				new Point(1, 4), 
				new Point(2, 7),
				new Point(3, 15),
				new Point(7, 7),
				new Point(8, 2),
				new Point(9, 9),
				new Point(12, 5),
				new Point(13, 7),
				new Point(15, 15),
				new Point(35, 12),
				};
		
		pdt=new PointDataStructure(points2,points2[7]);
		
		
		testName = "A4";
		XLeft=12;
		XRight=100;
		Point[] expectedLargePoints = {
				new Point(12, 5),
				new Point(13, 7),
				new Point(15, 15),
				new Point(35, 12),
				};
		testExpectedPoints(testName, expectedLargePoints, pdt.getPointsInRange(XLeft, XRight));

		XLeft=3;
		XRight=7;
		testName = "A5";
		expected = 2;
		result = pdt.numOfPointsInRange(XLeft, XRight);
		testExpected(testName, expected, result);
		
		XLeft=7;
		XRight=9;
		testName = "A6";
		expectedD = 6.0;
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		
		testName = "A7.1";
		Point[] expectedPoints4 = {
				new Point(13, 7),
				};
		testExpectedPoints(testName, expectedPoints4, pdt.getMedianPoints(1));
		testName = "A7.2";
		Point[] expectedPoints5 = { 
				new Point(7, 7),
				new Point(13, 7),
				};
		testExpectedPoints(testName, expectedPoints5, pdt.getMedianPoints(2));
		testName = "A7.3";
		Point[] expectedPoints6 = { 
				new Point(7, 7),
				new Point(13, 7),
				new Point(9, 9),
				};
		testExpectedPoints(testName, expectedPoints6, pdt.getMedianPoints(3));
		testName = "A7.4";
		Point[] expectedPoints7 = { 
				new Point(2, 7),
				new Point(7, 7),
				new Point(13, 7),
				new Point(9, 9),
				};
		testExpectedPoints(testName, expectedPoints7, pdt.getMedianPoints(4));
		testName = "A7.5";
		Point[] expectedPoints8 = {
				new Point(2, 7),
				new Point(7, 7),
				new Point(13, 7),
				new Point(9, 9),
				new Point(35, 12),
				};
		testExpectedPoints(testName, expectedPoints8, pdt.getMedianPoints(5));

	}


	


	private static void testB()
	{
		
		final int NUM = 100;
		
		Point[] points = new Point[NUM];
		
		for (int i=0; i<NUM; ++i) 
		{
			points[i] = new Point(NUM-i-1, i);
		}
		
		PDT pdt=new PointDataStructure(points,points[NUM/2]);
		
		String testName;
		int expected;
		int result;
		
		testName = "B0";
		testExpectedPoints(testName, points, pdt.getAllPoints());
		
		int XLeft=3;
		int XRight=7;
		testName = "B1";
		expected = 5;
		result = pdt.numOfPointsInRange(XLeft, XRight);
		testExpected(testName, expected, result);
		
		XLeft=1;
		XRight=3;
		testName = "B2.1";
		double expectedD = 97.0;
		double resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		testName = "B2.2";
		XLeft=0;
		XRight=NUM;
		expectedD =((double) (NUM-1)*NUM)/(2*NUM);
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		
		XLeft=7;
		XRight=12;
		testName = "B3";
		Point[] expectedPoints1=
		{
			new Point(7,NUM-7-1),
			new Point(8,NUM-8-1),
			new Point(9,NUM-9-1),
			new Point(10,NUM-10-1),
			new Point(11,NUM-11-1),
			new Point(12,NUM-12-1),
		};
		testExpectedPoints(testName, expectedPoints1, pdt.getPointsInRange(XLeft,XRight));	
		
		
		int k=6;
		testName = "B4";
		int mid=NUM/2;
		Point[] expectedPoints2=
		{
			new Point(NUM-mid-1,mid),
			new Point(NUM-(mid-1)-1,mid-1),
			new Point(NUM-(mid+1)-1,mid+1),
			new Point(NUM-(mid-2)-1,mid-2),
			new Point(NUM-(mid+2)-1,mid+2),
			new Point(NUM-(mid-3)-1,mid-3),
		};
		testExpectedPoints(testName, expectedPoints2, pdt.getMedianPoints(k));
		
		testName = "B5";
		Point[] expectedPoints3=
		{
			new Point(NUM-mid-1,mid),
			new Point(NUM-(mid-1)-1,mid-1),
			new Point(NUM-(mid+1)-1,mid+1),
			new Point(NUM-(mid-2)-1,mid-2),
			new Point(NUM-(mid+2)-1,mid+2),
			new Point(NUM-(mid-3)-1,mid-3),
			new Point(NUM-(mid+3)-1,mid+3),
		};
		testExpectedPoints(testName, expectedPoints3, pdt.getMedianPoints(k+1));
		
		pdt.addPoint(new Point(NUM,NUM));
		
		
		testName = "B6.1";
		Point[] expectedPoints4=
		{
				new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
		};
		testExpectedPoints(testName, expectedPoints4, pdt.getMedianPoints(k));
		
		testName = "B6.2";
		Point[] expectedPoints5=
		{
				new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
				new Point(NUM-(mid+3)-1,mid+3),
		};
		testExpectedPoints(testName, expectedPoints5, pdt.getMedianPoints(k+1));
		
		XLeft=1;
		XRight=3;
		testName = "B6.3";
		expectedD = 97.0;
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);
		testName = "B6.4";
		XLeft=0;
		XRight=NUM;
		expectedD =((double) (NUM)*(NUM+1))/(2*(NUM+1));
		resultD = pdt.averageHeightInRange(XLeft, XRight);
		testExpected(testName, expectedD, resultD);

		pdt.removeMedianPoint();

		testName = "B7.1";
		Point[] expectedPoints6=
		{
				//new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
				new Point(NUM-(mid+3)-1,mid+3),
		};
		testExpectedPoints(testName, expectedPoints6, pdt.getMedianPoints(k));
		
		testName = "B7.2";
		Point[] expectedPoints7=
		{
				//new Point(NUM-mid-1,mid),
				new Point(NUM-(mid-1)-1,mid-1),
				new Point(NUM-(mid+1)-1,mid+1),
				new Point(NUM-(mid-2)-1,mid-2),
				new Point(NUM-(mid+2)-1,mid+2),
				new Point(NUM-(mid-3)-1,mid-3),
				new Point(NUM-(mid+3)-1,mid+3),
				//
				new Point(NUM-(mid+4)-1,mid+4),
		};
		testExpectedPoints(testName, expectedPoints7, pdt.getMedianPoints(k+1));
	}

	

	private static void testC() 
	{
		
		//add more tests
	}
	
	private static class PointCompare implements Comparator<Point>
	 {

		@Override
		public int compare(Point point1, Point point2) 
		{
			if (point1.getY()<point2.getY()) return -1;
			else if (point1.getY()==point2.getY() && point1.getX()<point2.getX()) return -1;
			return 1;
		}
		 
	 }
		
	
	private static <T> void testExpected(String testName, T expected, T actual) 
	{
		if (!actual.equals(expected)) {
			System.out.println("Test " +testName+ ": wrong! expected=" + expected + ", actual=" + actual);
		} else {
			System.out.println("Test " +testName+ ": passed :)");
		}
		
	}
	
	private static void testExpectedPoints(String testName, Point[] expectedPoints,	Point[] actualPoints) 
	{
		if (!equalAsSets(actualPoints,expectedPoints)) {
			System.out.println("Test " +testName+ ": wrong! expected=" + Arrays.toString(expectedPoints) + ", actual=" + Arrays.toString(actualPoints));
		} else {
			System.out.println("Test " +testName+ ": passed :)");
		}
	}
	
	//checks (inefficiently) that the sets are equal
	//NOTE: if one of the sets contains 2 points with same coordinates, might return erroneous result 
	private static boolean equalAsSets(Point[] actualPoints, Point[] expectedPoints) 
	{
		if (actualPoints.length!=expectedPoints.length) return false;
		for (int i=0;i<expectedPoints.length;i++)
		{
			boolean exists=false;
			for (int j=0;j<actualPoints.length;j++)
			{ 
				if (expectedPoints[i].equals(actualPoints[j])) exists=true;
			}
			if (!exists) return false;
		}
		return true;
	}


	private static Point[] readPointsFile(String fileName) 
	{
		Vector<Point> points = new Vector<Point>();;
		BufferedReader input;
		FileReader fileReader;
		
		try {
			fileReader = new FileReader(fileName);
			input = new BufferedReader(fileReader);
			String line = null;
			String name;
			int x, y;

			while ((line = input.readLine()) != null) {
				String[] lineArray = line.split(";");
				name = lineArray[0];
				x = Integer.parseInt(lineArray[1]);
				y = Integer.parseInt(lineArray[2]);

				Point p = new Point(x, y, name);
				points.add(p);
			}
			input.close();
			fileReader.close();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Point[] result = new Point[points.size()];
		points.toArray(result);
		return result;
	}

}
