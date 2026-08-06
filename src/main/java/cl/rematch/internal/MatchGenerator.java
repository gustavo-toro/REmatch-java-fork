package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "REmatch/match.hpp",
        "REmatch/query.hpp",
        "REmatch/constants.hpp",
        "REmatch/match_generator.hpp"
})
@Namespace("REmatch")
public class MatchGenerator extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    public MatchGenerator(Pointer p) {
        super(p);
    }

    public native @ByVal MatchIterator begin();

    public native @ByVal MatchIterator end();
}
