package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "REmatch/multi_match.hpp", "<optional>" }, library = "jniREmatch")
@Name("std::optional<REmatch::MultiMatch>")
public class OptionalMultiMatch extends Pointer {
    static {
        Loader.load();
    }

    @Name("has_value")
    public native boolean hasValue();

    @Name("value")
    public native @ByVal MultiMatch value();
}
