package rematch;

public class MatchGenerator implements Iterable<Match> {
    private final javacpp.MatchGenerator generator;

    public MatchGenerator(javacpp.MatchGenerator generator) {
        this.generator = generator;
    }

    @Override
    public MatchIterator iterator() {
        return new MatchIterator(generator.begin(), generator.end());
    }
}
