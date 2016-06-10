import java.util.Comparator;

/**
 * Comparator for the minHeap, smallest number has the highest priority, natural ordering
 */
public class minHeapComparator  implements Comparator<Point> {

    @Override
    public int compare(Point i, Point j) {
        int result;
        if (i.getY() > j.getY())
            result =1;
        else  if (i.getY() == j.getY()){
            if (i.getX() > j.getX())
                result =1;
            else if (i.getX()<j.getX())
                result =-1;
            else result=0;
        }
        else result=-1;
        return result;
    }
}
