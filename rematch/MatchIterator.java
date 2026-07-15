package rematch;

import java.util.Iterator;

public class MatchIterator implements Iterator<Match> {

    private final javacpp.MatchIterator current;
    private final javacpp.MatchIterator end;

    private boolean needToIncrement = false;

    public MatchIterator(javacpp.MatchIterator begin, javacpp.MatchIterator end) {
        this.current = begin;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        if (needToIncrement) {
            current.operator_increment();
            needToIncrement = false;
        }
        return !current.operatorEquals(end);
    }

    @Override
    public Match next() {
        if (needToIncrement) {
            current.operator_increment();
            needToIncrement = false;
        }

        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }

        needToIncrement = true;
        javacpp.Match cppMatch = current.operator_star();
        return new Match(cppMatch);
    }

    public Match operatorStar() {
        return new Match(current.operator_star());
    }

    public boolean operatorEquals(MatchIterator other) {
        return current.operatorEquals(other.current);
    }

    public boolean operatorNotEquals(MatchIterator other) {
        return current.operatorNotEquals(other.current);
    }
}
