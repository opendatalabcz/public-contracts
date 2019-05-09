package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.dto.ParameterDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class represents application properties manager.
 * Essentially, its a key-value properties map of different categories.<br>
 * <br>
 * Currently used for:<br>
 * document download filtering,<br>
 * document downloader building.<br>
 * <br>
 * The presence of both keys and values checks with usage of string contain method:<br>
 * Example: category: "car", key="CAR1", value="BMW;M3;2.0L,blue"<br>
 * PropertyManager.containsValue("var","BMW") is true
 */
public class PropertyManager {

    public static String SUPPORTED_MIME_TYPE = "supported_mime_type";
    public static String DOWNLOAD_RULE = "download_rule";

    private Map<String, Map<String,String>> properties = new HashMap<>();

    public PropertyManager() {

    }

    public void putValue(String category, String key, String value) {
        Map<String, String> categoryMap = getCategory(category);
        categoryMap.put(key, value);
    }

    public boolean containsCategory(String category) {
        return properties.containsKey(category);
    }

    public void putCategory(String category) {
        properties.put(category, new HashMap<>());
    }

    public boolean containsValue(String categoryName, String value) {
        if (containsCategory(categoryName)) {
            Map<String, String> category = getCategory(categoryName);
            for (String refValue: category.values()) {
                if (value.contains(refValue)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsKey(String categoryName, String key) {
        if (containsCategory(categoryName)) {
            Map<String, String> category = getCategory(categoryName);
            for (String refKey: category.keySet()) {
                if (key.contains(refKey)) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getValue(String categoryName, String key) {
        if (!containsCategory(categoryName)) {
            throw new IllegalArgumentException("Category does not exist: " + categoryName);
        }
        Map<String, String> category = getCategory(categoryName);
        for (String refKey: category.keySet()) {
            if (key.contains(refKey)) {
                return category.get(refKey);
            }
        }
        return null;
    }

    public Map<String, String> getCategory(String category) {
        if (!containsCategory(category)) {
            putCategory(category);
        }
        return properties.get(category);
    }

    public static PropertyManager createProperties(List<ParameterDto> parameterDtos) {
        PropertyManager properties = new PropertyManager();
        for (ParameterDto property: parameterDtos) {
            String category = property.getCategory();
            String key = property.getKey();
            String value = property.getValue();
            properties.putValue(category, key, value);
        }
        return properties;
    }

}
