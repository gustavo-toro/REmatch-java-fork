import rematch.*;
import java.io.IOException;

public class TestJavaREmatch {

    public static void main(String[] args) {
        String pattern = "(^| )!words{(\\w|[\\-'])+} !words{(\\w|[\\-'])+}([ ,.])";
        String document = """
                You don't know about me without you have read a book by the name of The Adventures of Tom Sawyer but that ain't no matter. That book was made by Mr Mark Twain and he told the truth, mainly. There was things which he stretched, but mainly he told the truth. That is nothing. I never seen anybody but lied one time or another, without it was Aunt Polly or the widow, or maybe Mary. Aunt Polly-Tom's Aunt Polly, she is-and Mary, and the Widow Douglas is all told about in that book, which is mostly a true book, with some stretchers, as I said before.
                """;

        MultiQuery multiQuery = new MultiQuery(pattern, Flags.none());

        System.out.println("\nmultiQUERY::CHECK()");
        System.out.println("¿Documento válido? " + multiQuery.check(document));

        System.out.println("\nmultiQUERY::VARIABLES():");
        for (String var : multiQuery.variables()) {
        System.out.println("→ " + var);
        }

        System.out.println("\nmultiQUERY::MATCHES():");
        MultiMatchGenerator multiMatchGenerator = multiQuery.findIter(document);
        MultiMatchIterator itMulti = multiMatchGenerator.iterator();

        while (itMulti.hasNext()) {
        MultiMatch multiMatch = itMulti.operatorStar();
        System.out.println("————— " + multiMatch.toString());
        itMulti.next();
        }

        Query q = new Query("!x{a}");
        var mg_ = q.findIter("baba");
        var it_ = mg_.iterator();

        System.out.println("loop start");

        while (it_.hasNext()) {
            Match m = it_.operatorStar();
            System.out.println(m);
            it_.next();
        }

        System.out.println("loop end");

        Reader reader = new Reader("document.txt");
        Query query = new Query("!x{a}", Flags.none());

        MatchGenerator mg = query.findIter(reader);
        MatchIterator it = mg.iterator();

        while (it.hasNext()) {
            Match m = it.operatorStar();
            System.out.println(m);
            it.next();
        }
    }
}
