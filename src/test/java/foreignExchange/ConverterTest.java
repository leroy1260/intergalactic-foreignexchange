package foreignExchange;

import foreignExchange.exception.InvalidCurrencyException;
import foreignExchange.exception.InvalidEarthMetalException;
import foreignExchange.exception.NoCurrencyMappedException;
import foreignExchange.exception.NoEarthMetalConversionFound;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConverterTest {

    private IntergalacticPlanet planet = new IntergalacticPlanet();
    private Converter converter = new Converter(planet);

    @Before
    public void setUp() throws Exception {
        setCurrency();
        setEarthMetal();
    }

    @Test
    public void test_units_for_pish_tegj_glob_glob() {
        Assert.assertEquals(42, converter.calculateTotalUnits(TestConstant.UNITS_TOTAL));
    }

    @Test
    public void test_credits_for_glob_prok_silver() {
        Assert.assertEquals(68.0, converter.calculateCredits(TestConstant.CREDIT_TOTAL_1), Double.MAX_VALUE);
    }

    @Test
    public void test_credits_for_glob_prok_gold() {
        Assert.assertEquals(57800, converter.calculateCredits(TestConstant.CREDIT_TOTAL_2), Double.MAX_VALUE);
    }

    @Test
    public void test_credits_for_glob_prok_iron() {
        Assert.assertEquals(782, converter.calculateCredits(TestConstant.CREDIT_TOTAL_3), Double.MAX_VALUE);
    }

    @Test(expected = InvalidCurrencyException.class)
    public void test_invalid_currency() {
        converter.feedCreditInfoForMetals(TestConstant.CREDIT_INVALID_1);
    }

    @Test(expected = InvalidEarthMetalException.class)
    public void test_invalid_earth_metal() {
        converter.calculateCredits(TestConstant.CREDIT_INVALID_2);
    }

    @Test(expected = NoCurrencyMappedException.class)
    public void test_no_currency() {
        planet.getUnitMappings().clear();
        converter.feedCreditInfoForMetals(TestConstant.CREDIT_INPUT_1);
    }

    @Test(expected = NoEarthMetalConversionFound.class)
    public void test_no_earth_metal() {
        planet.getEarthMetalMappings().clear();
        converter.calculateCredits(TestConstant.CREDIT_INPUT_2);
    }

    private void setCurrency() {
        converter.mapIntergalacticUnit(TestConstant.ROMAN_INPUT_1);
        converter.mapIntergalacticUnit(TestConstant.ROMAN_INPUT_2);
        converter.mapIntergalacticUnit(TestConstant.ROMAN_INPUT_3);
        converter.mapIntergalacticUnit(TestConstant.ROMAN_INPUT_4);
    }

    private void setEarthMetal() {
        converter.feedCreditInfoForMetals(TestConstant.CREDIT_INPUT_1);
        converter.feedCreditInfoForMetals(TestConstant.CREDIT_INPUT_2);
        converter.feedCreditInfoForMetals(TestConstant.CREDIT_INPUT_3);
    }

    @After
    public void tearDown() throws Exception {
        planet.getEarthMetalMappings().clear();
        planet.getUnitMappings().clear();
    }
}
