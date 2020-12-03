
package ua.edu.ucu.autocomplete;

import org.junit.Before;
import org.junit.Test;
import ua.edu.ucu.tries.RWayTrie;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * @author Andrii_Rodionov
 */
public class PrefixMatchesITTest {

    private PrefixMatches pm;

    @Before
    public void init() throws IllegalAccessException {
        pm = new PrefixMatches(new RWayTrie());
        pm.load("abc", "abce", "abcd", "abcde", "abcdef");
    }

    @Test
    public void testWordsWithPrefix_String() {
        String pref = "ab";
        Iterable<String> result = pm.wordsWithPrefix(pref);
        String[] expResult = {"abc", "abce", "abcd", "abcde", "abcdef"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefix_String_and_K() {
        String pref = "abc";
        int k = 3;
        Iterable<String> result = pm.wordsWithPrefix(pref, k);
        String[] expResult = {"abc", "abce", "abcd", "abcde"};

        assertThat(result, containsInAnyOrder(expResult));
    }

    @Test
    public void testWordsWithPrefixContains() {
        String pref = "abc";
        boolean result = pm.contains(pref);
        boolean expResult = true;

        assertEquals(result, expResult);
    }

    @Test
    public void testWordsWithPrefixContains2() {
        String pref = "abcedfff";
        boolean result = pm.contains(pref);
        boolean expResult = false;

        assertEquals(result, expResult);
    }

    @Test
    public void testWordsWithPrefixDelete() {
        String pref = "abc";
        int expSize = pm.size() - 1;
        pm.delete(pref);
        boolean result = pm.contains(pref);
        boolean expResult = false;

        assertEquals(result, expResult);
        assertEquals(pm.size(), expSize);
    }
}
