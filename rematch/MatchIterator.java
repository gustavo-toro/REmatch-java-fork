package rematch;

import java.util.Iterator;

public class MatchIterator implements Iterator<Match> { // TODO: add Iterable

    private final javacpp.MatchIterator current;
    private final javacpp.MatchIterator end;

    public MatchIterator(javacpp.MatchIterator begin, javacpp.MatchIterator end) {
        this.current = begin;
        this.end = end;
    }

    @Override
    public boolean hasNext() {
        return !current.operatorEquals(end); // operator==
    }

    @Override
    public Match next() {
        javacpp.Match cppMatch = current.operator_star(); // equivalente a *it en C++
        current.operator_increment(); // it++
        return new Match(cppMatch);
    }

    public Match operatorStar() {
        current.operator_star(); // equivalente a *it en C++
        return new Match(current.operator_star()); // devuelve el valor actual
    }

    public boolean operatorEquals(MatchIterator other) {
        return current.operatorEquals(other.current); // operador==
    }

    public boolean operatorNotEquals(MatchIterator other) {
        return current.operatorNotEquals(other.current); // operador!=
    }
}
