package com.kodilla.covid;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class DataSaver {

    Map<String, Integer> map;

    public DataSaver() {
        this.map = new HashMap<>();
    }

    public void saveData() {
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("src/main/java/com/kodilla/covid/SavedSimulation.list"))) {

            bf.write("Last simulation summary: ");
            bf.newLine();
            for(Map.Entry<String, Integer> entry : map.entrySet()) {
                bf.write(entry.getKey() + " : " + entry.getValue());
                bf.newLine();
            }
            bf.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addData(String text, Integer data) {
        map.put(text, data);
    }




}
