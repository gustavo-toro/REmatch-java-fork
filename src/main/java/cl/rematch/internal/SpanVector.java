package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "<vector>", "REmatch/span.hpp" }, library = "jniREmatch")
@Namespace("std")
@Name("vector<std::pair<int64_t, int64_t>>")
public class SpanVector extends Pointer {
    static {
        Loader.load();
    }

    public SpanVector() {
        allocate();
    }

    private native void allocate();

    public native @Cast("size_t") long size();

    public native @ByVal Span at(@Cast("size_t") long index);
}
