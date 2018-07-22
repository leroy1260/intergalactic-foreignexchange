package foreignExchange;

import foreignExchange.mapper.IntergalacticUnitMapper;
import foreignExchange.mapper.RomanNumeral;

import java.util.HashMap;
import java.util.Map;

/**
 * @author leroy
 * <p>
 * Represents one of the Galaxy planet which has its own currency and roman unit associated with it.
 * Inorder to buy sell items on this planet there should be conversion of currency which is taken care by
 * mapper.IntergalacticUnitMapper
 * On this planet you will get to know your earthly metal price and credits for the items you buy/sell.
 */
class IntergalacticPlanet implements IntergalacticUnitMapper {

    // Map containing Units on Galaxy like GLOB, PROK, PISH, TEGJ and mapping ROMAN values
    private Map<String, RomanNumeral> unitMappings = new HashMap<String, RomanNumeral>();

    // Earth Metal/Dirt value map like GOLD/IRON/SILVER
    private Map<String, Double> earthMetalMappings = new HashMap<String, Double>();

    public void mapToRoman(String unit, RomanNumeral romanNumeral) {
        unitMappings.put(unit, romanNumeral);
    }

    /**
     * Checks Roman Integrity of values passed and performs Addition/Subtraction operations and Sums the total.
     *
     * @param romanNumerals array of integer roman values
     * @return int total of all the units passed
     */
    public int checkIntegrityAndCalculate(int... romanNumerals) {
        int[] units = romanNumerals;
        int total = 0;
        for (int i = 0; i < units.length; ) {
            for (int j = i; j < i + 1; j++) {
                if (j + 1 == units.length) {
                    total = total + (units[j]); //  sums the last unit
                    break;
                }
                if (units[j] < units[j + 1]) {
                    total = total + (units[j + 1] - units[j]);
                } else {
                    total = total + (units[j + 1] + units[j]);
                }
            }
            i = i + 2;
        }
        return total;
    }

    /**
     * Get Earth metal credit value as per this planet
     * @param totalUnitValue int - Sum of all the Roman Units
     * @param credits double - Credits received for earthly metal in exchange
     * @return double estimated credit value for earthly metal
     */
    public double calculateEarthMetalPrice(int totalUnitValue, double credits) {
        return credits / totalUnitValue;
    }

    /**
     * Get Credit value
     * @param totalUnitValue
     * @param earthMetalPrice
     * @return
     */
    public double calculateCredits(int totalUnitValue, double earthMetalPrice) {
        return totalUnitValue * earthMetalPrice;
    }

    public Map<String, Double> getEarthMetalMappings() {
        return earthMetalMappings;
    }

    public Map<String, RomanNumeral> getUnitMappings() {
        return unitMappings;
    }
}
