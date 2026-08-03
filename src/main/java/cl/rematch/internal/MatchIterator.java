package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "REmatch/match.hpp",
        "REmatch/match_generator.hpp"
}) //, link = "REmatch")
@Namespace("REmatch")
@Name("Iterator")
public class MatchIterator extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    @Name("operator*")
    public native @ByRef Match operator_star();

    @Name("operator->")
    public native @ByPtr Match operator_arrow();

    @Name("operator++")
    public native @ByRef MatchIterator operator_increment();

    // Comparación de igualdad (==)
    @Name("operator==")
    public native boolean operatorEquals(@ByRef MatchIterator other);

    // Comparación de desigualdad (!=)
    @Name("operator!=")
    public native boolean operatorNotEquals(@ByRef MatchIterator other);

    public static void main(String[] args) {
        try {
            MatchIterator it = new MatchIterator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
