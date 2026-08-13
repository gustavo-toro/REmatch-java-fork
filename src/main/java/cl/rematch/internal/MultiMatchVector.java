package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "<vector>", "REmatch/multi_match.hpp" }, library = "jniREmatch")
@Namespace("std")
@Name("vector<REmatch::MultiMatch>")
public class MultiMatchVector extends Pointer {
    static {
        Loader.load();
    }

    public MultiMatchVector() {
        allocate();
    }

    private native void allocate();

    public native @Cast("size_t") long size();

    public native @ByVal MultiMatch at(@Cast("size_t") long index);

    public native void push_back(@ByVal MultiMatch value);
}
