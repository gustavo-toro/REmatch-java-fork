import rematch.*;
import java.io.IOException;

public class TestJavaREmatch {

    public static void testFindIter() {
        String pattern = "(^| )!word{(\\w|[\\-'])+}([ ,.])";
        String document = "You don't know about me without you have read a book by the name of The Adventures of Tom Sawyer but that ain't no matter.";

        Query query = new Query(pattern);

        for (Match m : query.findIter(document)) {
            System.out.println(m + " - " + m.group(0));
        }
    }

    public static void testFindOne() {
        String pattern = "(^| )!word{(\\w|[\\-'])+}([ ,.])";
        String document = "You don't know about me without you have read a book by the name of The Adventures of Tom Sawyer but that ain't no matter.";

        Query query = new Query(pattern);
        Match m = query.findOne(document);

        if (m != null) {
            System.out.println(m + " - " + m.group(0));
        } else {
            System.out.println("NULL");
        }
    }

    public static void testCheck() {
        String pattern = "(^| )!word{(\\w|[\\-'])+}([ ,.])";
        String document = "You don't know about me without you have read a book by the name of The Adventures of Tom Sawyer but that ain't no matter.";

        Query query = new Query(pattern);
        boolean m = query.check(document);

        System.out.println(m);
    }

    public static void testFindIterStream() {
        String pattern = "(^| )!word{(\\w|[\\-'])+}([ ,.])";
        Reader reader = new Reader("document.txt");

        Query query = new Query(pattern);

        for (Match m : query.findIter(reader)) {
            System.out.println(m + " - " + m.group(0));
        }
    }

    public static void testFindOneStream() {
        String pattern = "(^| )!word{(\\w|[\\-'])+}([ ,.])";
        Reader reader = new Reader("document.txt");

        Query query = new Query(pattern);
        Match m = query.findOne(reader);

        if (m != null) {
            System.out.println(m + " - " + m.group(0));
        } else {
            System.out.println("NULL");
        }
    }

    public static void testCheckStream() {
        String pattern = "(^| )!word{(\\w|[\\-'])+}([ ,.])";
        Reader reader = new Reader("document.txt");

        Query query = new Query(pattern);
        boolean m = query.check(reader);

        System.out.println(m);
    }

    public static void main(String[] args) {
        System.out.println("FINDITER");
        testFindIter();
        System.out.println("FINDITER STREAM");
        testFindIterStream();
        System.out.println("FINDONE");
        testFindOne();
        System.out.println("FINDONE STREAM");
        testFindOneStream();
        System.out.println("CHECK");
        testCheck();
        System.out.println("CHECK STREAM");
        testCheckStream();
    }
}