package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "REmatch/multi_match.hpp",
        "REmatch/multi_match_generator.hpp"
})
@Namespace("REmatch")
@Name("MultiIterator")
public class MultiMatchIterator extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    @Name("operator*")
    public native @ByRef MultiMatch operator_star();

    @Name("operator->")
    public native @ByPtr MultiMatch operator_arrow();

    @Name("operator++")
    public native @ByRef MultiMatchIterator operator_increment();

    @Name("operator==")
    public native boolean operatorEquals(@ByRef MultiMatchIterator other);

    @Name("operator!=")
    public native boolean operatorNotEquals(@ByRef MultiMatchIterator other);
}
