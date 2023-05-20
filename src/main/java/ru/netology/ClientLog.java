package ru.netology;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ClientLog {
    protected List<String> log = new ArrayList<>();

    public void log (int productNum, int amount){
        log.add(Arrays.toString(new String[]{"" + productNum, "" + amount}));

    }

    public void exportAsCSV(File txtFile) throws IOException {
        if(!txtFile.exists()) {
            log.add(0, String.valueOf(new String[] {"productNum,amount"}));
        }

        try (CSVWriter writer =  new CSVWriter(new FileWriter(txtFile, true))) {
            writer.writeAll((Iterable<String[]>) log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

