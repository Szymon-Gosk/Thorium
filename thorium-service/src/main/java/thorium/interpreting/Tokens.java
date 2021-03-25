package thorium.interpreting;

@Deprecated
public interface Tokens {

    String GREEK_LOWERCASE = "(alpha)|(beta)|(gamma)|(delta)|(epsilon)|(zeta)|(eta)|(theta)|(iota)|(kappa)|(lambda)|" +
            "(mu)|(nu)|(xi)|(omicron)|(pi)|(rho)|(sigma)|(tau)|(upsilon)|(phi)|(chi)|(psi)|(omega)";

    String GREEK_UPPERCASE = "(Alpha)|(Beta)|(Gamma)|(Delta)|(Epsilon)|(Zeta)|(Eta)|(Theta)|(Iota)|(Kappa)|(Lambda)|" +
            "(Mu)|(Nu)|(Xi)|(Omicron)|(Pi)|(Rho)|(Sigma)|(Tau)|(Upsilon)|(Phi)|(Chi)|(Psi)|(Omega)";

    String LITERAL_FUNCTIONS = "(log)|(ln)|(lg)|(li)|(exp)|" +
                               "(arcsinh)|(arccosh)|(arctanh)|(arccoth)|(arcsech)|(arccsch)|" +
                               "(arcsin)|(arccos)|(arctan)|(arccot)|(arcsec)|(arccsc)|" +
                               "(sinh)|(cosh)|(tanh)|(coth)|(sech)|(csch)|" +
                               "(sin)|(cos)|(tan)|(cot)|(sec)|(csc)";

                    // EXPONENTIATION

    String LOG = "(log)((_\\(.+\\)\\(.+\\))|(_\\(.+\\).{1})|(_(([0-9]++)|(.{1}))\\(.+\\))|(\\(.+\\))|(([0-9]++)|(.{1})))";
    String LN =  "(ln)((\\(.+\\))|(.{1}))";
    String LG =  "(lg)((\\(.+\\))|(.{1}))";
    String LI =  "(li)((\\(.+\\))|(.{1}))";
    String EXP = "(exp)((\\(.+\\))|(.{1}))";

                    // TRIGONOMETRIC

    String SIN = "";
    String COS = "";
    String TAN = "";
    String COT = "";
    String SEC = "";
    String CSC = "";

                    // INVERSE TRIGONOMETRIC

    String ARCSIN = "";
    String ARCCOS = "";
    String ARCTAN = "";
    String ARCCOT = "";
    String ARCSEC = "";
    String ARCCSC = "";

                    // HYPERBOLIC

    String SINH = "";
    String COSH = "";
    String TANH = "";
    String COTH = "";
    String SECH = "";
    String CSCH = "";

                    // INVERSE TRIGONOMETRIC

    String ARCSINH = "";
    String ARCCOSH = "";
    String ARCTANH = "";
    String ARCCOTH = "";
    String ARCSECH = "";
    String ARCCSCH = "";

                    // ADVANCED

    String ERF =       "";
    String DIGAMMA =   "";
    String GAMMA =     "";
    String FACTORIAL = "";

                    // SIGNED

    String ABS =   "";
    String FLOOR = "";
    String CEIL =  "";
    String MOD =   "";
    String SIGN =  "";
    String RE =    "";
    String IM =    "";
    String ARG =   "";

                   // NOTATIONS

    String BIG_O =       "O\\(.+\\)";
    String SMALL_O =     "";
    String BIG_OMEGA =   "";
    String SMALL_OMEGA = "";
    String THETA =       "";


    String SPECIAL_CHARACTER_FUNCTIONS = "(floor)|(ceil)|(ceiling)|(sgn)|(sign)|(abs)|(absolute)|(error)|(digamma)|(fac)|(fact)|" +
            "(factorial)|(re)|(real)|(im)|(imaginary)|(arg)|(argument)";

    String OPERATOR_FUNCTIONS = "d((\\(.+\\))|(.{1}))\\/d(.{1})";



}
