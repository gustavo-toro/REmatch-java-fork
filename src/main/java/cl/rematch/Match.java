package cl.rematch;

import java.util.HashMap;
import java.util.Map;

public class Match {
    private final cl.rematch.internal.Match cppMatch;

    public Match(cl.rematch.internal.Match cppMatch) {
        this.cppMatch = cppMatch;
    }

    public long start(String variableName) {
        return this.cppMatch.start(variableName);
    }

    public long start(int variableId) {
        return this.cppMatch.start(variableId);
    }

    public long end(String variableName) {
        return this.cppMatch.end(variableName);
    }

    public long end(int variableId) {
        return this.cppMatch.end(variableId);
    }

    public String group(String variableName) {
        return this.cppMatch.group(variableName);
    }

    public String group(int variableId) {
        return this.cppMatch.group(variableId);
    }

    public cl.rematch.internal.Span span(int variableId) {
        return this.cppMatch.span(variableId);
    }

    public cl.rematch.internal.Span span(String variableName) {
        return this.cppMatch.span(variableName);
    }

    public String[] variables() {
        cl.rematch.internal.StringVector cppVariables = this.cppMatch.variables();
        String[] variables = new String[(int) cppVariables.size()];

        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i);
        }

        return variables;
    }

    public Map<String, cl.rematch.internal.Span> groupdict() {
        cl.rematch.internal.StringSpanMap cppGroupDict = this.cppMatch.groupdict();
        cl.rematch.internal.StringVector vars = this.cppMatch.variables();
        Map<String, cl.rematch.internal.Span> groupDict = new HashMap<>();

        for (long i = 0; i < vars.size(); i++) {
            String key = vars.at(i).toString();
            cl.rematch.internal.Span span = cppGroupDict.at(key);
            groupDict.put(key, span);
        }
        // result.groupdict().at("x").first() + ", " +
        // result.groupdict().at("x").second());

        return groupDict;
    }

    public boolean empty() {
        return this.cppMatch.empty();
    }

    public String toString() {
        return this.cppMatch.to_string();
    }

}
