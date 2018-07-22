package foreignExchange;


import foreignExchange.exception.InvalidCurrencyException;
import foreignExchange.exception.InvalidEarthMetalException;
import foreignExchange.exception.NoCurrencyMappedException;
import foreignExchange.exception.NoEarthMetalConversionFound;
import foreignExchange.mapper.RomanNumeral;
import foreignExchange.utility.ExchangeUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * @author leroy
 * Handles all the conversions for Buying and selling Earthly metals
 */
public class Converter {

    private IntergalacticPlanet intergalacticPlanet;

    public Converter(IntergalacticPlanet intergalacticPlanet) {
        this.intergalacticPlanet = intergalacticPlanet;
    }

    public void mapIntergalacticUnit(String[] words) {
        intergalacticPlanet.mapToRoman(words[0], RomanNumeral.valueOf(words[2]));
    }

    /**
     * updates credit information for earth metals
     * @param words String[] containing the words
     * @throws NoCurrencyMappedException, InvalidCurrencyException
     */
    public void feedCreditInfoForMetals(String[] words) throws NoCurrencyMappedException, InvalidCurrencyException {
        // Data check
        checkCurrencyMappings(intergalacticPlanet.getUnitMappings(), words);

        int unit1 = intergalacticPlanet.getUnitMappings().get(words[0]).getValue();
        int unit2 = intergalacticPlanet.getUnitMappings().get(words[1]).getValue();
        String credits = words[4];
        String metal = words[2];

        int units = intergalacticPlanet.checkIntegrityAndCalculate(unit1, unit2);

        // calculate the Earth Metal conversion price
        double priceOfMetal = intergalacticPlanet.calculateEarthMetalPrice(units, Double.parseDouble(credits));

        // register an entry in MetalPrice map
        intergalacticPlanet.getEarthMetalMappings().put(metal, priceOfMetal);

    }

    public int calculateTotalUnits(String[] words) {
        int[] units = getUnits(words);

        return intergalacticPlanet.checkIntegrityAndCalculate(units);
    }

    private int[] getUnits(String[] words) {
        return Arrays.asList(words).stream()
                .filter(word -> intergalacticPlanet.getUnitMappings().containsKey(word))
                .map(word -> intergalacticPlanet.getUnitMappings().get(word).getValue())
                .mapToInt(Integer::intValue).toArray();
    }

    public double calculateCredits(String[] words) {
        // Data check
        checkEarthMetalExists(intergalacticPlanet.getEarthMetalMappings(), words);

        int totalUnit = intergalacticPlanet.checkIntegrityAndCalculate(getUnits(words));
        return intergalacticPlanet.calculateCredits(totalUnit, intergalacticPlanet.getEarthMetalMappings().get(words[words.length - 2]));
    }

    /**
     *checks if currency map is empty and also if an invalid currency passed
     * @param unitMappings Map intergalactic currency map holding mapping to roman numerals
     * @param words String[] passed as input
     */
    private void checkCurrencyMappings(Map<String, RomanNumeral> unitMappings, String[] words) {
        if (ExchangeUtils.checkIfNoCurrencyExists(unitMappings)) {
            throw new NoCurrencyMappedException(ExchangeUtils.NO_CURRENCY_FOUND);
        }

        if (!ExchangeUtils.checkIfMappingExists(unitMappings, words[0]) || !ExchangeUtils.checkIfMappingExists(unitMappings, words[1])) {
            throw new InvalidCurrencyException(ExchangeUtils.INVALID_CURRENCY);
        }
    }

    /**
     * checks if earth metal and its value exists in the system
     * @param earthMetalMapping Map intergalactic currency map holding mapping to roman numerals
     * @param words String[] passed as input
     */
    private void checkEarthMetalExists(Map<String, Double> earthMetalMapping, String[] words) {
        if (ExchangeUtils.checkIfNoEarthMetalExists(earthMetalMapping)) {
            throw new NoEarthMetalConversionFound(ExchangeUtils.NO_EARTH_METAL_FOUND);
        }

        if (!ExchangeUtils.checkIfEarthMetalExists(earthMetalMapping, words[words.length - 2])) {
            throw new InvalidEarthMetalException(ExchangeUtils.INVALID_EARTH_METAL);
        }
    }

}
