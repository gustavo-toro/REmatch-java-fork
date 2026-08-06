package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "REmatch/multi_match.hpp",
        "REmatch/multi_query.hpp",
        "REmatch/constants.hpp",
        "REmatch/multi_match_generator.hpp"
})
@Namespace("REmatch")
public class MultiMatchGenerator extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    public MultiMatchGenerator(Pointer p) {
        super(p);
    }

    public native @ByVal MultiMatchIterator begin();

    public native @ByVal MultiMatchIterator end();
}
