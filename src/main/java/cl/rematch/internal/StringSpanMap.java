package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "<map>", "<string>", "REmatch/span.hpp" })
@Name("std::map<std::string, Span>")
public class StringSpanMap extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    public StringSpanMap() {
        allocate();
    }

    private native void allocate();

    public native @ByRef Span at(@StdString String key);

    public native @Cast("size_t") long size();

    public native boolean count(@StdString String key);
}
