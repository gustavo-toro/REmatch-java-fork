package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "REmatch/match.hpp",
        "REmatch/query.hpp",
        "REmatch/constants.hpp",
        "REmatch/match_generator.hpp"
}, library = "jniREmatch")
@Namespace("REmatch")
public class MatchGenerator extends Pointer {
    static {
        Loader.load();
    }

    public MatchGenerator(Pointer p) {
        super(p);
    }

    public native @ByVal MatchIterator begin();

    public native @ByVal MatchIterator end();
}
