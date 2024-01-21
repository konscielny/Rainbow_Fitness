import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class Saving {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static File file;
    //private static File tmpFile;

    private static final String dirs = System.getProperty("user.home") + "\\AppData\\Roaming\\Rainbow Fitness\\";

    /**
     *
     * erstelle Verzeichnis wenn noch nicht vorhanden
     *
     */
    private static void createDir() {

        File tmp = new File(Saving.dirs);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
    }

    /**
     * Erstelle das Save File
     */
    private static void createFile() {

        createDir();
        file = new File(dirs  + "statList.json");
        //File tmpFile = new File("C:\\Users\\domin\\OneDrive\\Desktop\\r6 fit\\statList.json");
    }

    /**
     *
     * Schreibe die Liste in ein save File
     *
     * @param tmpFile in welches File soll gespeichert werden
     * @param list welche Liste soll gespeichert werden
     */
    private static void write(File tmpFile, List<Stats> list){
        try (FileWriter writer = new FileWriter(tmpFile)) {

            writer.write(gson.toJson(list));

        }
        catch (Exception ignored) {
        }
    }

    /**
     * Speichere die Liste in das File durch write()
     *
     * @param list welche Liste soll gespeichert werden
     */
    public static void save(List<Stats> list) {

        createFile();

        write(file, list);
        //write(tmpFile, list);
    }

    /**
     * Lade alles aus dem Save File
     *
     * @return geladene Liste
     */
    public static LinkedList<Stats> load() {

        LinkedList<Stats> st = new LinkedList<>();

        createFile();

        try {
            Type listtyp = new TypeToken<LinkedList<Stats>>() {}.getType();
            st = gson.fromJson(new FileReader(file), listtyp);

        } catch (Exception ignored) {

        }

        return st;
    }
}
