import java.util.Comparator;

/**
 * Comparator for the maxHeap, largest number has the highest priority
 */
class maxHeapComparator implements Comparator<Point> {
    // opposite to minHeapComparator, invert the return values
    @Override
    public int compare(Point i, Point j) {

        int result;
        if (i.getY() > j.getY())
            result = j.getY() - i.getY();
        else  if (i.getY() == j.getY()){
            if (i.getX() > j.getX())
                result = j.getX() - i.getX();
            else if (i.getX()<j.getX())
                result = 1;
            else result = 0;
        }
        else result = 1;
        return result;

    }
}