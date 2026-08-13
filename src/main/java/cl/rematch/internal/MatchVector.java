package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "<vector>", "REmatch/match.hpp" }, library = "jniREmatch")
@Namespace("std")
@Name("vector<REmatch::Match>")
public class MatchVector extends Pointer {
    static {
        Loader.load();
    }

    public MatchVector() {
        allocate();
    }

    private native void allocate();

    public native @Cast("size_t") long size();

    public native @ByVal Match at(@Cast("size_t") long index);
}
