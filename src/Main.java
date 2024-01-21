import com.google.gson.Gson;

import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        LinkedList<Stats> ls = Saving.load();

        new gui("Rainbow Fitness Trainer", 800, 500, ls);
    }
}
