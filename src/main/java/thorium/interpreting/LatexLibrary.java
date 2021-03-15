package thorium.interpreting;

import org.jetbrains.annotations.Contract;

import java.util.HashMap;
import java.util.Map;

public class LatexLibrary {

    private static LatexLibrary single_instance = null;

    private final Map<String, String> styles;

    public static LatexLibrary getInstance() {
        if (single_instance == null)
            single_instance = new LatexLibrary();
        return single_instance;
    }

    public String getLatex(String s) {
        String v = styles.get(s);
        if(v == null)
            return s;
        return v;
    }

    @Contract(pure = true)
    private LatexLibrary() {
        styles = new HashMap<>();
        styles.put("(", "\\left( "); styles.put("[", "\\left[ "); styles.put("{", "\\lbrace ");
        styles.put(")", "\\right) "); styles.put("]", "\\right] "); styles.put("}", "\\rbrace ");
        styles.put("<", "\\langle "); styles.put(">", "\\rangle ");
        styles.put("\\|\\|", "\\Vert" ); styles.put("\\|", "\\vert ");
        styles.put("*", "\\cdot ");
        styles.put("log", "\\mathrm{log} "); styles.put("exp", "\\mathrm{exp} ");
        styles.put("ln", "\\mathrm{ln} "); styles.put("lg", "\\mathrm{lg} ");

        styles.put("in", "\\in" );
    }

}
