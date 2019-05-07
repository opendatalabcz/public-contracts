package eu.profinit.publiccontracts.util;

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

}
