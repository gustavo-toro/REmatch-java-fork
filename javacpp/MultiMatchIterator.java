package javacpp;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = {
        "REmatch/multi_match.hpp",
        "REmatch/multi_match_generator.hpp"
}, link = "REmatch")
@Namespace("REmatch")
@Name("MultiIterator")
public class MultiMatchIterator extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    // public MultiMatchIterator() {
    //     allocate();
    // }

    // private native void allocate();

    @Name("operator*")
    public native @ByRef MultiMatch operator_star();

    @Name("operator->")
    public native @ByPtr MultiMatch operator_arrow();

    @Name("operator++")
    public native @ByRef MultiMatchIterator operator_increment();

    // @Name("operator++") TODO:
    // public native void operator_increment_postfix();

    @Name("operator==")
    public native boolean operatorEquals(@ByRef MultiMatchIterator other);

    @Name("operator!=")
    public native boolean operatorNotEquals(@ByRef MultiMatchIterator other);

    public static void main(String[] args) {
        try {
            MultiMatchIterator it = new MultiMatchIterator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
