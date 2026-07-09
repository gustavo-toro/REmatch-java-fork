package javacpp;

import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(link = { "REmatch" })
@Namespace("REmatch")
@Name("Reader")
public class Reader extends Pointer {
    static {
        JNILoader.load("jniREmatch");
    }
}
