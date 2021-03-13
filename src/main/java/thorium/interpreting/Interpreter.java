package thorium.interpreting;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Interpreter {

    private final Map<String, Integer> precedence = new HashMap<>();

    public Interpreter() {
        precedence.put("^", 6);
        precedence.put("/", 5); precedence.put("*", 5);
        precedence.put("+", 4); precedence.put("-", 4);
        precedence.put("(", 0); precedence.put("[", 0); precedence.put("{", 0);
        precedence.put(")", 0); precedence.put("]", 0); precedence.put("}", 0);
    }

    /**
     * Converts input string to string tokens
     * @param e input string
     * @return array consisting of string tokens
     */
    public @NotNull String[] tokenize(@NotNull String e) {
        String temp = optimize(e);
        List<String> tokens = new LinkedList<>();

        StringBuilder container = new StringBuilder();
        for(int i = 0; i < temp.length(); i++) {
            char next = temp.charAt(i);
            // numerical part
            if((next >= 48 && next <= 57) || next == 46 || next == 105) {
                int cl = container.length();
                if(cl != 0) {
                    char last = container.charAt(cl - 1);
                    if((last >= 65 && last <= 90) || (last != 105 && last >= 97 && last <= 122)) {
                        tokens.add(container.toString());
                        container = new StringBuilder();
                    }
                }
                container.append(next);
            } else {
                // function name
                if((next >= 65 && next <= 90) || (next >= 97 && next <= 122)) {
                    int cl = container.length();
                    if(cl != 0) {
                        char last = container.charAt(cl - 1);
                        if((last >= 48 && last <= 57) || last == 46 || last == 105) {
                            tokens.add(container.toString());
                            container = new StringBuilder();
                        }
                    }
                    container.append(next);
                    continue;
                }
                int cl = container.length();
                if(cl != 0) {
                    tokens.add(container.toString());
                    container = new StringBuilder();
                }
                tokens.add(next + "");
            }
        }
        if(container.length() != 0) {
            tokens.add(container.toString());
        }
        String[] array = new String[tokens.size()];
        for(int i = 0; i < tokens.size(); i++) {
            array[i] = tokens.get(i);
        }
        return array;
    }

    /**
     * Converts string tokens to singular string
     * @param t array of string tokens
     * @return singular string created from tokens
     */
    public @NotNull String untokenize(@NotNull String[] t) {
        StringBuilder sb = new StringBuilder();
        for(String token: t) {
            sb.append(token);
        }
        return sb.toString();
    }

    public Queue<String> infixToRPN(String @NotNull [] tokens) {
        Queue<String> Q = new LinkedList<>();
        Stack<String> S = new Stack<>();

        for (String token : tokens) {
            // encapsulation
            if(token.length() <= 2) {
                switch (token) {
                    case "(":
                        S.push(token);
                        Q.add("(");
                        continue;
                    case "[":
                        S.push(token);
                        Q.add("[");
                        continue;
                    case "{":
                        S.push(token);
                        Q.add("{");
                        continue;
                    case "<":
                        S.push(token);
                        Q.add("<");
                        continue;
                    case ")":
                        while (!"(".equals(S.peek())) {
                            Q.add(S.pop());
                        }
                        S.pop();
                        Q.add(")");
                        continue;
                    case "]":
                        while (!"[".equals(S.peek())) {
                            Q.add(S.pop());
                        }
                        S.pop();
                        Q.add("]");
                        continue;
                    case "}":
                        while (!"{".equals(S.peek())) {
                            Q.add(S.pop());
                        }
                        S.pop();
                        Q.add("}");
                        continue;
                    case ">":
                        while (!"<".equals(S.peek())) {
                            Q.add(S.pop());
                        }
                        S.pop();
                        Q.add(">");
                        continue;
                }
            }
            // operator / function
            if (precedence.containsKey(token)) {
                while (!S.empty() && precedence.get(token) <= precedence.get(S.peek())) {
                    Q.add(S.pop());
                }
                S.push(token);
                continue;
            }
            // numerical value
            if (isNumber(token)) {
                Q.add(token);
                continue;
            }
            // todo
            throw new IllegalArgumentException("Invalid input");
        }
        // at the end, pop all the elements in S to Q
        while (!S.isEmpty()) {
            Q.add(S.pop());
        }
        return Q;
    }

    @Contract(pure = true)
    static boolean isNumber(@NotNull String str) {
        return str.matches("([0-9]+(.[0-9]+)?i?|i[0-9]+(.[0-9]+)?|i)");
    }

    @Contract(pure = true)
    private @NotNull String optimize(@NotNull String s) {
        return s.replaceAll("\\s+","");
    }

    /*
        /pnl/ : (
        /pnr/ : )
        /cbl/ : {
        /cbr/ : }
    */
    @Deprecated
    public String toLatex(String s) {
        String output = optimize(s);

        // SIMPLE OPERATORS
        output = output.replaceAll("\\*", "\\\\cdot ");

        // LITERAL FUNCTIONS
        output = output.replaceAll(Tokens.LITERAL_FUNCTIONS, "\\\\mathrm{cos} ");
        // todo
        output = output.replaceAll("\\{", "/cbl/").replaceAll("}", "/cbr/ ");

        // PARENTHESES
        output = output.replaceAll("\\(", "\\\\left( ").replaceAll("\\)", "\\\\right) ");
        output = output.replaceAll("\\[", "\\\\left[ ").replaceAll("]", "\\\\right] ");
        output = output.replaceAll("\\{", "\\\\left{ ").replaceAll("}", "\\\\right} ");
        output = output.replaceAll("<", "\\\\langle ").replaceAll(">", "\\\\rangle ");
        output = output.replaceAll("\\|\\|", "\\Vert" ).replaceAll("\\|", "\\\\vert ");

        // TEMPORARY CODES REMOVAL
        output = output.replaceAll("/cbl/", "{").replaceAll("/cbr/", "} ");

        return output;
    }

}
