package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSearch {
    String paths = "C:\\Users\\mamed\\Downloads\\Практика 19\\data";

    public void searchFile() {
        try {
            Files.walk(Paths.get(paths))
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .collect(Collectors.toList()).forEach(System.out::println);
            System.out.println("");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void searchByType(String format) {
        String fileNameToFind = format;
        File file = new File(paths);

        List<File> foundFiles = new ArrayList<>();

        try (Stream<Path> walkStream = Files.walk(file.toPath())) {
            walkStream.filter(p -> p.toFile().isFile())
                    .forEach(f -> {
                        if (f.toString().endsWith(fileNameToFind)) {
                            foundFiles.add(f.toFile());
                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        foundFiles.forEach(System.out::println);
    }

    public void writeJSON(List<String> path){
        JSONArray list = new JSONArray();
        List<metroJSON> buildMetro = new ArrayList<>();
        List<metroJSON> stations = new ArrayList<>();
        try {
            Object o = new JSONParser().parse(new FileReader(path.get(0)));
            JSONArray array = (JSONArray) o;
            for (Object obj : array) {
                JSONObject object = (JSONObject) obj;
                String name = (String) object.put("station_name", obj);
                String number = (String) object.put("depth", obj);
                stations.add(new metroJSON(name, number));
                for (metroJSON s : stations) {
                    if (s.name.equals(name) & s.depth.equals(number)) {
                        buildMetro.add(new metroJSON(s.name, s.depth));
                    }
                }
            }

            ObjectMapper objectMapper = new ObjectMapper();
            FileWriter writer = new FileWriter("src/main/resources/data.json");
            writer.write('[');
            for (int i = 0; i < buildMetro.size(); i++) {
                String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(buildMetro.get(i));
                if (i == list.size()-1){
                    writer.write(s + "\n");
                } else {
                    writer.write(s +  ',' + "\n");
                }
            }
            writer.write(']');
            writer.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void writeCSV(List<String> dataPath) {
        List<String> list = new ArrayList<>();
        HashMap<String,String> map = new HashMap<>();
        BufferedReader br = null;
        try {
            for (int i=0; i<=dataPath.size()-1; i++){
                br = Files.newBufferedReader(Paths.get(dataPath.get(i)), Charset.forName("UTF-8"));
                String line;
                String regexName = "[А-Яа-яЁё\\-\\s\\n]+[0-9\\sа-я]+";
                String regexNumber = "[0-9.]{10}";
                Pattern namePattern = Pattern.compile(regexName);
                Pattern numberPattern = Pattern.compile(regexNumber);

                while ((line = br.readLine()) != null) {
                    Matcher matcherName = namePattern.matcher(line);
                    Matcher matcherNumber = numberPattern.matcher(line);
                    while (matcherName.find()) {
                        while (matcherNumber.find()) {
                            String number = matcherNumber.group(0);
                            String name = matcherName.group(0);
                            list.add(name);
                            map.put(name, number);
                        }
                    }
                }
            }
            FileWriter writer = new FileWriter("src/main/resources/data.csv");
            map.forEach((k,v)-> {
                try {
                    writer.write(k + "," + v + "\n");
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
