package ru.netology;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringJoiner;


public class ClientLog {
    protected String[] product;
    protected Integer[] quantit;

    public void produc (String[] product){
        this.product = product;
    }

    public void log (Integer[] quantit) throws IOException {
        this.quantit = quantit;

        try (CSVWriter writer = new CSVWriter(new FileWriter("log.csv"))) {
            String[] shopping = new String[4];

            for (int i = 0; i < shopping.length; i++) {
                shopping[i] = product[i] + " , " + quantit[i] + "\n";
            }

            writer.writeNext(shopping);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

