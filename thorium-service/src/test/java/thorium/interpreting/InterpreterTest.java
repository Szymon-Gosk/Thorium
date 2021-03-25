package thorium.interpreting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class InterpreterTest {

    private static final Logger log = LogManager.getLogger(InterpreterTest.class.getName());

    private Interpreter interpreter;

    @BeforeEach
    public void initialize() {
        interpreter = new Interpreter();
    }

    @Test
    @DisplayName("Tokenization")
    public void testTokenize() {
        assertArrayEquals(new String[]{}, interpreter.tokenize(""), "Tokenization");

        assertArrayEquals(new String[]{}, interpreter.tokenize("  "), "Tokenization");

        assertArrayEquals(new String[]{"2"}, interpreter.tokenize("2"), "Tokenization");

        assertArrayEquals(new String[]{"2", "+", "4"}, interpreter.tokenize("2+4"), "Tokenization");

        assertArrayEquals(new String[]{"2", "+", "4"}, interpreter.tokenize("2 +  4"), "Tokenization");

        assertArrayEquals(new String[]{"2", "-", "4"}, interpreter.tokenize("2-4"), "Tokenization");

        assertArrayEquals(new String[]{"2", "*", "4"}, interpreter.tokenize("2*4"), "Tokenization");

        assertArrayEquals(new String[]{"2", "/", "4"}, interpreter.tokenize("2/4"), "Tokenization");

        assertArrayEquals(new String[]{"2", "^", "4"}, interpreter.tokenize("2^4"), "Tokenization");

        assertArrayEquals(new String[]{"(", "2", "+", "4", ")"}, interpreter.tokenize("(2 + 4)"),
                "Tokenization");

        assertArrayEquals(new String[]{"3", "*", "(", "2", "+", "4", ")"}, interpreter.tokenize("3*(2 + 4)"),
                "Tokenization");

        assertArrayEquals(new String[]{"a"}, interpreter.tokenize("a"), "Tokenization");

        assertArrayEquals(new String[]{"a", "+", "b"}, interpreter.tokenize("a + b"), "Tokenization");

        assertArrayEquals(new String[]{"log"}, interpreter.tokenize("log"), "Tokenization");

        assertArrayEquals(new String[]{"log", "(", "x", ")"}, interpreter.tokenize("log(x)"), "Tokenization");


    }


}
