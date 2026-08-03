package cl.rematch;


import java.util.Iterator;

public class MultiMatchGenerator implements Iterable<MultiMatch> {
    private final cl.rematch.internal.MultiMatchGenerator generator;

    public MultiMatchGenerator(cl.rematch.internal.MultiMatchGenerator generator) {
        this.generator = generator;
    }

    @Override
    public MultiMatchIterator iterator() {
        return new MultiMatchIterator(generator.begin(), generator.end());
    }
}
