package cl.rematch.internal;

import org.bytedeco.javacpp.*;
import org.bytedeco.javacpp.annotation.*;

@Platform(include = { "REmatch/reader_wrapper_java.hpp" })
@Namespace("REmatch")
@Name("ReaderWrapperJava")
public class ReaderWrapper extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }

    public ReaderWrapper(String path) {
        allocate(path);
    }

    private native void allocate(@StdString String path);

    public native @ByRef Reader get();
}
