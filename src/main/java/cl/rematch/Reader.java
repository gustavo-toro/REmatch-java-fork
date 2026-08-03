package cl.rematch;

public class Reader {
    public final cl.rematch.internal.ReaderWrapper cppReader;

    public Reader(String path) {
        this.cppReader = new cl.rematch.internal.ReaderWrapper(path);
    }
}
