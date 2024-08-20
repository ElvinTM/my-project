package org.example;

import org.json.simple.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BuilderMetro {
    String htmlFile = "src/main/resources/Task/MoscowMetro.html";

    public Element buildMainElement() {
        Element metroData = null;
        try {
            Document document = Jsoup.parse(parseFile(htmlFile));
            metroData = document.select("div.b-vd-metro").first();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return metroData;
    }

    public List<Line> buildLines(Element mainElement) {
        Elements lines = mainElement.select("span.js-metro-line");
        List<Line> linesList = new ArrayList<>();
        JSONArray jsonLinesArray = new JSONArray();
        for (Element line : lines
        ) {
            linesList.add(new Line(line.attr("data-line"), line.text()));
            jsonLinesArray.add(new Line(line.attr("data-line"), line.text()));
        }
        return linesList;
    }

    public List<LineContent> buildLinesContent(Element mainElement) {
        Elements lines = mainElement.select("div.js-metro-stations");
        List<LineContent> lineContentList = new ArrayList<>();
        for (Element line : lines
        ) {
            lineContentList
                    .add(new LineContent(line.attr("data-line"),
                            line.select("span.name").eachText()));
        }
        return lineContentList;
    }

    public List<Station> buildStationsList(List<LineContent> linesContentList) {
        Elements stationConect = buildMainElement().select("p.single-station");
        Elements contr = stationConect.select("span[title]");
        Map<String, String> map = new LinkedHashMap<>();
        List<Station> stations = new ArrayList<>();
        for (LineContent st : linesContentList
        ) {
            for (Element line : stationConect) {
                Elements cont = line.select("span[title]");
                for (String name : st.getStations()) {
                    if (!cont.isEmpty()) {
                        boolean connect = true;
                        stations.add(new Station(name, st.getLineNumber(), connect));
                    } else if (cont.isEmpty()) {
                        boolean connect = false;
                        stations.add(new Station(name, st.getLineNumber(), connect));
                    }
                }
            }

        }
        return stations;
    }

    public String parseFile(String path) {
        StringBuilder builder = new StringBuilder();

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            lines.forEach(line -> builder.append(line + "\n"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return builder.toString();
    }
}
