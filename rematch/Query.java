package rematch;

import java.util.ArrayList;
import java.util.List;

public class Query {

    private final javacpp.Query cppQuery;

    public Query(String pattern, Flags flags, int maxMempoolDuplications, int maxDeterministicStates, int bufferSize) {
        this.cppQuery = new javacpp.Query(pattern, flags.getValue(), maxMempoolDuplications, maxDeterministicStates,
                bufferSize);
    }

    public Query(String pattern, Flags flags) {
        this(pattern, flags, 8, 2000, 1024); // valores por defecto TODO:
    }

    public Query(String pattern) {
        this(pattern, Flags.none(), 8, 2000, 1024); // valores por defecto TODO:
    }

    public boolean check(String document) {
        return cppQuery.check(document);
    }

    public boolean check(Reader reader) {
        return cppQuery.check(reader.cppReader.get());
    }

    public String[] variables() {
        javacpp.StringVector cppVariables = this.cppQuery.variables();
        String[] variables = new String[(int) cppVariables.size()];
        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i);
        }
        return variables;
    }

    public Match findOne(String document) {
        javacpp.OptionalMatch match = cppQuery.findone(document);
        if (match.hasValue()) {
            return new Match(match.value());
        }
        return null;
    }

    public Match findOne(Reader reader) {
        javacpp.OptionalMatch match = cppQuery.findone(reader.cppReader.get());
        if (match.hasValue()) {
            return new Match(match.value());
        }
        return null;
    }

    public List<Match> findMany(String document, int limit) {
        javacpp.MatchVector cppMatches = cppQuery.findmany(document, limit);
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    public List<Match> findMany(Reader reader, int limit) {
        javacpp.MatchVector cppMatches = cppQuery.findmany(reader.cppReader.get(), limit);
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    public List<Match> findAll(String document) {
        javacpp.MatchVector cppMatches = cppQuery.findall(document);
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    public List<Match> findAll(Reader reader) {
        javacpp.MatchVector cppMatches = cppQuery.findall(reader.cppReader.get());
        List<Match> result = new ArrayList<>();
        for (int i = 0; i < cppMatches.size(); i++) {
            result.add(new Match(cppMatches.at(i)));
        }
        return result;
    }

    public MatchGenerator findIter(String document) {
        javacpp.MatchGenerator generator = cppQuery.finditer(document);
        return generator != null ? new MatchGenerator(generator) : null;
    }

    public MatchGenerator findIter(Reader reader) {
        javacpp.MatchGenerator generator = cppQuery.finditer(reader.cppReader.get());
        return generator != null ? new MatchGenerator(generator) : null;
    }
}
