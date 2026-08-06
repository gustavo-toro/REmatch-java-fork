package cl.rematch.internal;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;

@Namespace("REmatch")
@Name("Reader")
public class Reader extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }
}
