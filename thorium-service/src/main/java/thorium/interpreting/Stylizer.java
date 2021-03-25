package thorium.interpreting;

import org.jetbrains.annotations.NotNull;

public class Stylizer {

    public static String @NotNull [] tokensToLatex(String @NotNull [] tokens) {
        String[] lt = new String[tokens.length];
        for(int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if(Interpreter.isNumber(token)) {
                lt[i] = token;
            } else {
                lt[i] = LatexLibrary.getInstance().getLatex(token);
            }
        }
        return lt;
    }


}
