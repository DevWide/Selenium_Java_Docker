package com.exemplo.utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class SelectorsLoader {
    private JSONObject selectors;

    public SelectorsLoader() {
        try (InputStream is = getClass().getClassLoader().getResourceAsStream("selectors.json")) {
            if (is == null) {
                throw new RuntimeException("selectors.json file not found");
            }
            JSONTokener tokener = new JSONTokener(is);
            selectors = new JSONObject(tokener);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load selectors.json", e);
        }
    }

    public String getSelector(String page, String element) {
        return selectors.getJSONObject(page).getString(element);
    }
}
