package javacpp;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "REmatch/match.hpp" }) //, link = { "REmatch" })
@Name("std::optional<REmatch::MultiMatch>")
public class OptionalMultiMatch extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    @Name("has_value")
    public native boolean hasValue();

    @Name("value")
    public native @ByVal MultiMatch value();
}
