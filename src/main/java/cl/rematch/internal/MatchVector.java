package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "<vector>", "REmatch/match.hpp" }) //, link = { "REmatch", "stdc++" })
@Namespace("std")
@Name("vector<REmatch::Match>")
public class MatchVector extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    public MatchVector() {
        allocate();
    }

    private native void allocate();

    public native @Cast("size_t") long size();

    public native @ByVal Match at(@Cast("size_t") long index);

    public static void main(String[] args) {
        MatchVector vector = new MatchVector();
        System.out.println("Vector size: " + vector.size());
    }
}
