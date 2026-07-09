package javacpp;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "REmatch/match.hpp",
        "REmatch/query.hpp",
        "REmatch/constants.hpp",
        "REmatch/match_generator.hpp"
}, link = { "REmatch", "stdc++" })
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

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
