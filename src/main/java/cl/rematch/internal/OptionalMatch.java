package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "REmatch/match.hpp" })
@Name("std::optional<REmatch::Match>")
public class OptionalMatch extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    @Name("has_value")
    public native boolean hasValue();

    @Name("value")
    public native @ByVal Match value();
}
