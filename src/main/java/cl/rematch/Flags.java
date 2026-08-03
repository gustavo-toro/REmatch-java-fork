package cl.rematch;


public class Flags {
    private final cl.rematch.internal.Flags cppFlags;

    public enum Mode {
        NONE,
        LINE_BY_LINE
    }

    public Flags(Mode mode) {
        switch (mode) {
            case LINE_BY_LINE:
                this.cppFlags = cl.rematch.internal.Flags.LINE_BY_LINE();
                break;
            case NONE:
            default:
                this.cppFlags = cl.rematch.internal.Flags.NONE();
                break;
        }
    }

    public int getValue() {
        return this.cppFlags.getVal();
    }

    public static Flags none() {
        return new Flags(Mode.NONE);
    }

    public static Flags lineByLine() {
        return new Flags(Mode.LINE_BY_LINE);
    }
}
