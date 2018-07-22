package foreignExchange;

import foreignExchange.exception.FileReadWriteException;
import foreignExchange.exception.InvalidFileException;
import foreignExchange.utility.ExchangeUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * @author leroy
 */
public class App {
    public static void main(String[] args) {

        String fileName = args[0];  // input file containing the conversation
        String outputFile = args[1];// output file containing the result
        String line;
        Converter converter = new Converter(new IntergalacticPlanet());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                while ((line = reader.readLine()) != null) {
                    String[] words = line.split("\\s+");
                    if (Pattern.matches(ExchangeUtils.REGEX_ROMAN_INPUT, line)) {
                        converter.mapIntergalacticUnit(words);
                    } else if (Pattern.matches(ExchangeUtils.REGEX_CREDIT_INPUT, line)) {
                        converter.feedCreditInfoForMetals(words);
                    } else if (Pattern.matches(ExchangeUtils.REGEX_ROMAN_OUTPUT, line)) {
                        writer.write(ExchangeUtils.prepareUnitOutput(converter.calculateTotalUnits(words), words));
                        writer.newLine();
                    } else if (Pattern.matches(ExchangeUtils.REGEX_CREDIT_OUTPUT, line)) {
                        writer.write(ExchangeUtils.prepareCreditOutput(converter.calculateCredits(words), words));
                        writer.newLine();
                    } else {
                        writer.write(ExchangeUtils.NO_MATCH_STRING);
                    }
                }
            } catch (FileNotFoundException e) {
                throw new InvalidFileException(ExchangeUtils.FILE_NOT_FOUND_MSG, e);
            } catch (IOException e) {
                throw new FileReadWriteException(ExchangeUtils.ERR_IO_MSG, e);
            }
        } catch (IOException e) {
            throw new FileReadWriteException(ExchangeUtils.ERR_IO_MSG, e);
        }
        System.out.println("Please check the output in " + outputFile);
    }
}
