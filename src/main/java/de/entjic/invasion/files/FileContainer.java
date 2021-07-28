package de.entjic.invasion.files;

import de.entjic.invasion.Invasion;

import java.util.HashMap;

public class FileContainer {
    private final static FileContainer instance = new FileContainer();
    private final HashMap<String, File> files = new HashMap<>();

    public static FileContainer getInstance() {
        return instance;
    }

    public File getFile(String name) {
        if (files.containsKey(name)) {
            return files.get(name);
        }
        final File file = FileBuilder.create(Invasion.getProvidingPlugin(Invasion.class), name + ".yml");
        files.put(name, file);
        return file;
    }

    public void reloadFiles() {
        files.values().forEach(File::load);
        files.values().forEach(File::save);
    }
}
