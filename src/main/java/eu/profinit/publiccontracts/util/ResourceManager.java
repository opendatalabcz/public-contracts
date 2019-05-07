package eu.profinit.publiccontracts.util;

import eu.profinit.publiccontracts.dto.PropertyDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceManager {

    public static Object createClassInstance(String className) {
        ClassLoader classLoader = DocumentFetcher.class.getClassLoader();
        try {
            Class aClass = classLoader.loadClass(className);
            return aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, Map<String,String>> transformProperties(List<PropertyDto> propertyDtos) {
        Map<String,Map<String,String>> properties = new HashMap<>();
        for (PropertyDto property: propertyDtos) {
            String category = property.getCategory();
            if (!properties.containsKey(category)) {
                properties.put(category, new HashMap<>());
            }
            Map<String, String> categoryMap = properties.get(category);
            String key = property.getKey();
            String value = property.getValue();
            categoryMap.put(key, value);
        }
        return properties;
    }

}
