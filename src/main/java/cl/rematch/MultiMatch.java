package cl.rematch;


import java.util.ArrayList;
import java.util.List;

public class MultiMatch {

    private final cl.rematch.internal.MultiMatch cppMultiMatch;

    public MultiMatch(cl.rematch.internal.MultiMatch cppMultiMatch) {
        this.cppMultiMatch = cppMultiMatch;
    }

    public boolean empty() {
        return cppMultiMatch.empty();
    }

    public String toString() {
        return cppMultiMatch.to_string();
    }

    public String[] variables() {
        cl.rematch.internal.StringVector cppVariables = cppMultiMatch.variables();
        String[] variables = new String[(int) cppVariables.size()];
        for (int i = 0; i < cppVariables.size(); i++) {
            variables[i] = cppVariables.at(i).toString();
        }
        return variables;
    }

    public List<cl.rematch.internal.Span> spans(String variableName) {
        cl.rematch.internal.SpanVector spanVec = cppMultiMatch.spans(variableName);
        List<cl.rematch.internal.Span> spans = new ArrayList<>();
        for (int i = 0; i < spanVec.size(); i++) {
            spans.add(spanVec.at(i));
        }
        return spans;
    }

    public List<cl.rematch.internal.Span> spans(int variableId) {
        cl.rematch.internal.SpanVector spanVec = cppMultiMatch.spans(variableId);
        List<cl.rematch.internal.Span> spans = new ArrayList<>();
        for (int i = 0; i < spanVec.size(); i++) {
            spans.add(spanVec.at(i));
        }
        return spans;
    }

    public List<String> groups(String variableName) {
        cl.rematch.internal.StringVector groupVec = cppMultiMatch.groups(variableName);
        List<String> groups = new ArrayList<>();
        for (int i = 0; i < groupVec.size(); i++) {
            groups.add(groupVec.at(i).toString());
        }
        return groups;
    }

    public List<String> groups(int variableId) {
        cl.rematch.internal.StringVector groupVec = cppMultiMatch.groups(variableId);
        List<String> groups = new ArrayList<>();
        for (int i = 0; i < groupVec.size(); i++) {
            groups.add(groupVec.at(i).toString());
        }
        return groups;
    }

    public MultiMatch submatch(cl.rematch.internal.Span span) {
        cl.rematch.internal.MultiMatch sub = cppMultiMatch.submatch(span);
        return new MultiMatch(sub);
    }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj) return true;
    //     if (obj == null || getClass() != obj.getClass()) return false;
    //     MultiMatch other = (MultiMatch) obj;
    //     return cppMultiMatch.operatorEquals(other.cppMultiMatch);
    // }
    // public boolean equals(MultiMatch other) {
    //     return cppMultiMatch.operatorEquals(other.cppMultiMatch);
    // }
}
