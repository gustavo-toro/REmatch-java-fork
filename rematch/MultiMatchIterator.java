package rematch;

import java.util.Iterator;

public class MultiMatchIterator implements Iterator<MultiMatch> {

    private final javacpp.MultiMatchIterator current;
    private final javacpp.MultiMatchIterator end;

    private boolean needToIncrement = false;

    public MultiMatchIterator(javacpp.MultiMatchIterator begin, javacpp.MultiMatchIterator end) {
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
    public MultiMatch next() {
        if (needToIncrement) {
            current.operator_increment();
            needToIncrement = false;
        }

        if (!hasNext()) {
            throw new java.util.NoSuchElementException();
        }

        needToIncrement = true;
        javacpp.MultiMatch cppMatch = current.operator_star();
        return new MultiMatch(cppMatch);
    }

    public MultiMatch operatorStar() {
        current.operator_star();
        return new MultiMatch(current.operator_star());
    }

    public boolean operatorEquals(MultiMatchIterator other) {
        return current.operatorEquals(other.current);
    }

    public boolean operatorNotEquals(MultiMatchIterator other) {
        return current.operatorNotEquals(other.current);
    }
}
