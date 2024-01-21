import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.swing.*;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Settings {

    private JFrame frame;
    private JPanel panel1;
    private JComboBox comboBox1;
    private JCheckBox sitUpsCheckBox;
    private JCheckBox liegestützeCheckBox;
    private JCheckBox checkBox3;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel situpLabel;
    private JLabel pushuplabel;
    private JLabel label;

    private List<SValues> vals;

    private static final String dirs = System.getProperty("user.home") + "\\AppData\\Roaming\\Rainbow Fitness\\";

    public Settings(String title, int x, int y, List<Stats> prevFrame, JFrame mainFrame) {


        frame = functions.initFrame(title, x, y, panel1);

        comboBox1.setSize(50, comboBox1.getHeight());
        textField1.setSize(20, textField1.getHeight());
        textField2.setSize(20, textField2.getHeight());
        textField3.setSize(20, textField3.getHeight());

        textField3.setVisible(false);
        checkBox3.setVisible(false);
        label.setVisible(false);

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {

                saveSettings();
                mainFrame.setEnabled(true);
            }
        });

        File settings = new File(dirs  + "statList.json");

        if(settings.exists()){
            loadSettings();
        }

        else{
            createDefaultSettings();
            saveSettings();
        }
    }

    private void createDefaultSettings(){

        vals = new LinkedList<>();
        vals.add(new SValues(10, PunishType.PUSHUPS));
    }

    private void saveSettings() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try{

        }catch (Exception e){

        }
    }

    private void loadSettings() {

    }
}
