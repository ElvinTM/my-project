package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildDataFile {
    String file = "src/main/resources/data.json";
    String fileCSV = "src/main/resources/data.csv";

    public List<metroJSON> buildJson(){
        String file = "Data/data.json";
        List<metroJSON> stations = new ArrayList<>();
        try {
            Object dataJson= new JSONParser().parse(new FileReader(file));
            JSONArray array = (JSONArray) dataJson;
            for (Object obj : array) {
                JSONObject object = (JSONObject) obj;
                String name = (String) object.put("name", obj);
                String depth = (String) object.put("depth", obj);
                stations.add(new metroJSON(name, depth));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return stations;
    }

    public List<metroCSV> buildCSV() {
        List<String> list = new ArrayList<>();
        List<metroCSV> metro = new ArrayList<>();
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(fileCSV), Charset.forName("Windows-1251"));
            String line;
            String regexName = "[А-Яа-яЁё\\-\\s\\n]+[0-9\\sа-я]+";
            String regexNumber = "[0-9.]{10}";
            Pattern namePattern = Pattern.compile(regexName);
            Pattern numberPattern = Pattern.compile(regexNumber);
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                list.addAll(List.of(columns));
                Matcher matcherName = namePattern.matcher(line);
                Matcher matcherNumber = numberPattern.matcher(line);
                while (matcherName.find()) {
                    String name = matcherName.group(0);
                    while (matcherNumber.find()) {
                        String number = matcherNumber.group(0);
                        metro.add(new metroCSV(name, number));
                        for (int i = 1; i <= matcherNumber.groupCount(); i++) {
                            System.out.println("Group " + i + ": " + matcherNumber.group(i));
                        }
                    }
                    for (int i = 1; i <= matcherName.groupCount(); i++) {
                        System.out.println("Group " + i + ": " + matcherName.group(i));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return metro;
    }

    public List<metroData> constructMetro(List<metroJSON> build) {
        JSONArray construct= new JSONArray();
        for (metroJSON depthMetro : build) {
            if (!depthMetro.equals("?")) {
                for (metroCSV metroDataOpen : buildCSV()) {
                    if (metroDataOpen.name.equals(depthMetro.name)) {
                        construct.add(new metroData(metroDataOpen.name, metroDataOpen.data, depthMetro.depth));
                    }
                }
            }
        }
        construct.forEach(System.out::println);
        return construct;
    }

    public void buildMetro(List<stationJSON> metro){;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FileWriter writer = new FileWriter("src/main/resources/buildMetro.json");
            writer.write('[' + "\n");
            for (int i = 0; i < metro.size(); i++) {
                String s = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(metro.get(i));
                if (i == metro.size()-1){
                    writer.write(s + "\n");
                } else {
                    writer.write(s +  ',' + "\n");
                }
            }
            writer.write(']');
            writer.flush();
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
