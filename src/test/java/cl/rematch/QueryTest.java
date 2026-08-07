package cl.rematch;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

record ExpectedMatch(int x, int y) {
}

public class QueryTest {
    @Test
    void testCheck() {

    }

    @Test
    void testCheck2() {

    }

    @Test
    void testFindAll() {

    }

    @Test
    void testFindAll2() {

    }

    @Test
    void testFindIter() {
        String pattern = "!x{a}";
        String document = "baba";

        Query query = new Query(pattern);

        List<Match> actual = new ArrayList<>();

        for (Match m : query.findIter(document)) {
            actual.add(m);
        }

        assertEquals(2, actual.size());
    }

    @Test
    void testFindIter2() {

    }

    @Test
    void testFindMany() {

    }

    @Test
    void testFindMany2() {

    }

    @Test
    void testFindOne() {

    }

    @Test
    void testFindOne2() {

    }

    @Test
    void testVariables() {

    }
}
