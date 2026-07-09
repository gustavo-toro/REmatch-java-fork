package rematch;


import java.util.Iterator;

public class MultiMatchGenerator implements Iterable<MultiMatch> {
    private final javacpp.MultiMatchGenerator generator;

    public MultiMatchGenerator(javacpp.MultiMatchGenerator generator) {
        this.generator = generator;
    }

    @Override
    public MultiMatchIterator iterator() {
        return new MultiMatchIterator(generator.begin(), generator.end());
    }
}
