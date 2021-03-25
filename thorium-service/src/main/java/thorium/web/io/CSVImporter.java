package thorium.web.io;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.RFC4180Parser;
import com.opencsv.RFC4180ParserBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.jetbrains.annotations.Contract;
import thorium.web.models.Render;
import thorium.web.repositories.RenderRepository;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVImporter {

    @Contract(pure = true)
    public static void importToRepository(String filename, RenderRepository repository) throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();

        RFC4180Parser rfc4180Parser = new RFC4180ParserBuilder().build();
        CSVReaderBuilder csvReaderBuilder = new CSVReaderBuilder(
                new FileReader("src\\main\\resources\\renders\\" + filename)
        ).withCSVParser(rfc4180Parser);

        try (CSVReader csvReader = csvReaderBuilder.build()) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
        int i = 0;
        for(List<String> record: records) {
            repository.save(new Render(i, record.get(0), record.get(1)));
            i++;
        }

    }

}
