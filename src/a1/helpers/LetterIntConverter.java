package a1.helpers;

import java.util.Map;

import static java.util.Map.entry;

public class LetterIntConverter {
    private static final Map<Integer, Character> intCharMap;
    static {
        intCharMap = Map.ofEntries(
                entry(0, 'a'),
                entry(1, 'b'),
                entry(2,'c'),
                entry(3,'d'),
                entry(4,'e'),
                entry(5, 'f'),
                entry(6,'g'),
                entry(7,'h')
        );
    }
    private static final Map<Character, Integer> charIntMap;
    static {
        charIntMap = Map.ofEntries(
                entry('a', 0),
                entry('b',1),
                entry('c',2),
                entry('d',3),
                entry('e',4),
                entry('f', 5),
                entry('g',6),
                entry('h',7)
        );
    }


    public static char convertIntToChar(int number) {
        return intCharMap.get(number);
    }

    public static int convertCharToInt(char letter) {
        if (!charIntMap.containsKey(letter)) { return -1; }
        return charIntMap.get(letter);
    }
}
