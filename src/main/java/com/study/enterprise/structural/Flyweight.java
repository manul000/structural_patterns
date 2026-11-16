package com.study.enterprise.structural;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flyweight {
    record Style(String font, String color) {
    }

    record Label(String text, int x, int y, Style style) {
    }

    static class StyleFactory {
        private final Map<Style, Style> pool = new HashMap<>();

        Style get(String font, String color) {
            Style key = new Style(font, color);
            return pool.computeIfAbsent(key, style -> style);
        }
    }

    public static String demo() {
        StyleFactory factory = new StyleFactory();
        List<Label> labels = new ArrayList<>();
        for (int index = 0; index < 6; index++) {
            labels.add(new Label("Товар " + index, 0, index * 20,
                    factory.get("Arial", index % 2 == 0 ? "blue" : "gray")));
        }
        return "Надписей: " + labels.size() + "; общих стилей: " + factory.pool.size()
                + "; первый и третий используют один стиль: " + (labels.get(0).style() == labels.get(2).style());
    }
}
