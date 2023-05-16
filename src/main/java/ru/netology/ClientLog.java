package ru.netology;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;


public class ClientLog {
    protected List<String> log = new ArrayList<>();
//    protected String log = "";

    public void log (int productNum, int amount){
//            log += String.format("%d,%d\n", productNum, amount);

        log.add(Arrays.toString(new String[]{"" + productNum, "" + amount}));

    }

    public void exportAsCSV(File txtFile) throws IOException {
        if(!txtFile.exists()) {
//            log = "productNum,amount\n" + log;
            log.add(0, String.valueOf(new String[] {"productNum,amount"}));
        }

//        try (FileWriter writer = new FileWriter(txtFile, true)){
//
//            writer.write(log);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try (CSVWriter writer =  new CSVWriter(new FileWriter(txtFile, true))) {
//            writer.writeNext(log.get(0), );

            writer.writeAll((Iterable<String[]>) log);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

