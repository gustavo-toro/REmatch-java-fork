package rematch;

import java.util.ArrayList;
import java.util.List;

public class MultiQuery {

    private final javacpp.MultiQuery cppMultiQuery;

    public MultiQuery(String pattern, Flags flags, int maxMempoolDuplications, int maxDeterministicStates,
            int bufferSize) {
        this.cppMultiQuery = new javacpp.MultiQuery(pattern, flags.getValue(), maxMempoolDuplications,
                maxDeterministicStates, bufferSize);
    }

    public MultiQuery(String pattern) {
        this(pattern, Flags.none(), 8, 2000, 1024); // TODO:
    }

    public MultiQuery(String pattern, Flags flags) {
        this(pattern, flags, 8, 2000, 1024); // TODO:
    }

    public boolean check(String document) {
        return cppMultiQuery.check(document);
    }

    public boolean check(Reader reader) {
        return cppMultiQuery.check(reader.cppReader.get());
    }

    public String[] variables() {
        javacpp.StringVector cppVariables = cppMultiQuery.variables();
        String[] variables = new String[(int) cppVariables.size()];
        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i).toString();
        }
        return variables;
    }

    public MultiMatch findOne(String document) {
        javacpp.OptionalMultiMatch match = cppMultiQuery.findone(document);
        if (match.hasValue()) {
            return new MultiMatch(match.value());
        }
        return null;
    }

    public MultiMatch findOne(Reader reader) {
        javacpp.OptionalMultiMatch match = cppMultiQuery.findone(reader.cppReader.get());
        if (match.hasValue()) {
            return new MultiMatch(match.value());
        }
        return null;
    }

    public List<MultiMatch> findMany(String document, int limit) {
        javacpp.MultiMatchVector matches = cppMultiQuery.findmany(document, limit);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public List<MultiMatch> findMany(Reader reader, int limit) {
        javacpp.MultiMatchVector matches = cppMultiQuery.findmany(reader.cppReader.get(), limit);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public List<MultiMatch> findAll(String document) {
        javacpp.MultiMatchVector matches = cppMultiQuery.findall(document);
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public List<MultiMatch> findAll(Reader reader) {
        javacpp.MultiMatchVector matches = cppMultiQuery.findall(reader.cppReader.get());
        List<MultiMatch> results = new ArrayList<>();
        for (int i = 0; i < matches.size(); i++) {
            results.add(new MultiMatch(matches.at(i)));
        }
        return results;
    }

    public MultiMatchGenerator findIter(String document) {
        javacpp.MultiMatchGenerator generator = cppMultiQuery.finditer(document);
        return generator != null ? new MultiMatchGenerator(generator) : null;
    }

    public MultiMatchGenerator findIter(Reader reader) {
        javacpp.MultiMatchGenerator generator = cppMultiQuery.finditer(reader.cppReader.get());
        return generator != null ? new MultiMatchGenerator(generator) : null;
    }
}
