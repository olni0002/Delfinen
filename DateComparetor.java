import java.util.Comparator;

public class DateComparetor implements Comparator<Begivenheder> {
    @Override
    public int compare(Begivenheder o1, Begivenheder o2) {
        return o1.getDag().compareTo(o2.getDag());
    }
}
