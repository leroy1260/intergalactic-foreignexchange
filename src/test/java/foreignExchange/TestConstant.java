package foreignExchange;

public class TestConstant {
    public static String[] ROMAN_INPUT_1 = {"glob", "is", "I"};
    public static String[] ROMAN_INPUT_2 = {"prok", "is", "V"};
    public static String[] ROMAN_INPUT_3 = {"pish", "is", "X"};
    public static String[] ROMAN_INPUT_4 = {"tegj", "is", "L"};

    public static String[] CREDIT_INPUT_1 = {"glob", "glob", "Silver", "is", "34", "Credits"};
    public static String[] CREDIT_INVALID_1 = {"glob1", "glob", "Silver", "is", "34", "Credits"};
    public static String[] CREDIT_INVALID_2 = {"glob", "glob", "Silver1", "is", "34", "Credits"};
    public static String[] CREDIT_INPUT_2 = {"glob", "prok", "Gold", "is", "57800", "Credits"};
    public static String[] CREDIT_INPUT_3 = {"pish", "pish", "Iron", "is", "3910", "Credits"};

    public static String[] UNITS_TOTAL = {"how", "much", "is", "pish", "tegj", "glob", "glob", "?"};
    public static String[] CREDIT_TOTAL_1 = {"how", "many", "Credits", "is", "glob", "prok", "Silver", "?"};
    public static String[] CREDIT_TOTAL_2 = {"how", "many", "Credits", "is", "glob", "prok", "Gold", "?"};
    public static String[] CREDIT_TOTAL_3 = {"how", "many", "Credits", "is", "glob", "prok", "Iron", "?"};


}
