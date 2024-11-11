package com.exemplo.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStreamReader;

public class SelectorsLoader {
    private JsonObject selectors;

    public SelectorsLoader() {
        try (InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("selectors.json"))) {
            selectors = JsonParser.parseReader(reader).getAsJsonObject();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load selectors.json", e);
        }
    }

    public String getSelector(String page, String element) {
        return selectors.getAsJsonObject(page).get(element).getAsString();
    }
}
