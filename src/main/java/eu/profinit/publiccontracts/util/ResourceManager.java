package eu.profinit.publiccontracts.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Represents the resource manager for the project.
 */
public class ResourceManager {

    public static Properties loadProperties() throws IOException {
        final Resource resource = new ClassPathResource("/public-contract.properties");
        final Properties properties = PropertiesLoaderUtils.loadProperties(resource);
        return properties;
    }

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

}
