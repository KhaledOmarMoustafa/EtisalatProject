package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvFetcher {
    private static final String CSV_FILE_PATH = "src/test/resources/localization_data.csv";
    private static Map<String, Map<String, String>> localizationData = new HashMap<>();

    // Load CSV Data Once
    static {
        loadLocalizationData();
    }

    private static void loadLocalizationData() {
        try (CSVReader reader = new CSVReader(new FileReader(CSV_FILE_PATH))) {
            String[] nextLine;
            reader.readNext(); // Skip header
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine.length < 3) continue; // Skip empty or invalid rows

                String language = nextLine[0].trim();
                String key = nextLine[1].trim();
                String value = nextLine[2].trim();

                localizationData.computeIfAbsent(language, k -> new HashMap<>()).put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    // Get localized text by key and language
    public static String getLocalizedText(String key, String language) {
        return localizationData.getOrDefault(language, new HashMap<>()).getOrDefault(key, key);
    }
}
