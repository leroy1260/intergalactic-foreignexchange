package foreignExchange.utility;

import foreignExchange.mapper.RomanNumeral;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ExchangeUtils {

    public static final List<String> IGNORE_WORDS =  Arrays.asList("how", "Credits", "many", "much", "is", "?");
    public static final String NO_MATCH_STRING = "I have no idea what you are talking about";
    public static final String FILE_NOT_FOUND_MSG = "Please check the path for Input file. File not found";
    public static final String ERR_IO_MSG = "Some problem reading/writing file. Please try again";

    public static final String REGEX_ROMAN_INPUT = "([a-z]+ is (I|V|X|L|C|D|M))";
    public static final String REGEX_CREDIT_INPUT = "(([a-zA-Z]+\\s*){3} is \\d+ [c|C]redits)";
    public static final String REGEX_ROMAN_OUTPUT = "([h|H]ow much is ([a-zA-Z]+\\s*)+ [\\?])";
    public static final String REGEX_CREDIT_OUTPUT = "([h|H]ow many [c|C]redits is ([a-zA-Z]+\\s*)+ [\\?])";
    public static final String NO_CURRENCY_FOUND = "No currency mapping found. Please input the currency-roman mapping.";
    public static final String INVALID_CURRENCY = "Currency does not exist in the system. Please check and pass a valid input";
    public static final String NO_EARTH_METAL_FOUND = "No Earth Metal value present. Please exchange your earth metal";
    public static final String INVALID_EARTH_METAL = "Earth Metal does not exist in the system";

    public static String prepareUnitOutput(int value, String[] words) {
        String output = "";
        for (int i = 0 ; i < words.length; i++) {
            if (!IGNORE_WORDS.contains(words[i])) {
                output += words[i] + " ";
            }
        }

        return output + "is " + value;
    }

    public static String prepareCreditOutput(double value, String[] words) {
        String output = "";
        for (int i = 0 ; i < words.length; i++) {
            if (!IGNORE_WORDS.contains(words[i])) {
                output += words[i] + " ";
            }
        }

        return output + "is " + Math.round(value) + " Credits";
    }

    public static boolean checkIfNoCurrencyExists(Map<String, RomanNumeral> unitMapping) {
        return unitMapping.isEmpty();
    }

    public static boolean checkIfNoEarthMetalExists(Map<String, Double> earthMetalMapping) {
        return earthMetalMapping.isEmpty();
    }

    public static boolean checkIfMappingExists(Map<String, RomanNumeral> unitMappings, String word) {
        return unitMappings.containsKey(word);
    }

    public static boolean checkIfEarthMetalExists(Map<String, Double> earthMetalMapping, String word) {
        return earthMetalMapping.containsKey(word);
    }
}
