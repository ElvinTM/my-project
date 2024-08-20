package org.example;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String path = "Data/map.json";

        BuilderMetro metro = new BuilderMetro();
        BuildDataFile builderMetro = new BuildDataFile();
        JSONWriter jsonWriter = new JSONWriter(metro);
        JSONReader jsonReader = new JSONReader(path);

        //Сборка и запись станций меро с линиями
        jsonWriter.writeJSON(path);
        jsonReader.printNumOfStationsOnEachLine();

        FileSearch fileSearch = new FileSearch();
        System.out.println();
        fileSearch.searchFile(); // Поиск фалов в папках
        fileSearch.searchByType(".json"); // Поиск файлов json
        System.out.println();
        fileSearch.searchByType(".csv"); // Поиск файлов csv

        // Объединение файлов json в один
        String fileJSON1 = "C:\\Users\\mamed\\Downloads\\Практика 19\\data\\2\\4\\depths-1.json";
        String fileJSON2 = "C:\\Users\\mamed\\Downloads\\Практика 19\\data\\4\\6\\depths-3.json";
        String fileJSON3 = "C:\\Users\\mamed\\Downloads\\Практика 19\\data\\7\\1\\depths-2.json";
        List<String> pathJSON = new ArrayList<>();
        pathJSON.add(fileJSON1);
        pathJSON.add(fileJSON2);
        pathJSON.add(fileJSON3);
        fileSearch.writeJSON(pathJSON);

        // Объединение файлов csv в один
        String fileCSV1 = "C:\\Users\\mamed\\Downloads\\Практика 19\\data\\0\\5\\dates-2.csv";
        String fileCSV2 = "C:\\Users\\mamed\\Downloads\\Практика 19\\data\\4\\6\\dates-1.csv";
        String fileCSV3 = "C:\\Users\\mamed\\Downloads\\Практика 19\\data\\9\\6\\dates-3.csv";
        List<String> pathCSV = new ArrayList<>();
        pathCSV.add(fileCSV1);
        pathCSV.add(fileCSV2);
        pathCSV.add(fileCSV3);
        fileSearch.writeCSV(pathCSV);

        builderMetro.buildMetro(jsonWriter.buildFinalMetro());
    }
}