package cl.rematch.internal;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Namespace;
import org.bytedeco.javacpp.annotation.Platform;

@Platform(include = "REmatch/fstream_reader.hpp", library = "jniREmatch")
@Namespace("REmatch")
@Name("Reader")
public class Reader extends Pointer {
    static {
        Loader.load();
    }
}
