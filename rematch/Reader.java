package rematch;

public class Reader {
    public final javacpp.ReaderWrapper cppReader;

    public Reader(String path) {
        this.cppReader = new javacpp.ReaderWrapper(path);
    }
}
