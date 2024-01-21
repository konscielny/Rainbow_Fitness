import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ItemEvent;
import java.util.LinkedList;
import java.util.List;

public class gui {
    private JPanel panel1;
    private JPanel panelSettings;

    private JLabel normalKillLabel;
    private JLabel headShotKillLabel;
    private JLabel normalDeathLabel;
    private JLabel headShotDeathLabel;
    private JLabel winLabel;
    private JLabel minusAceLabel;
    private JLabel strafeLabel;

    private JLabel kdLabel;

    private JLabel killN;
    private JLabel killH;
    private JLabel deathN;
    private JLabel deathH;
    private JLabel kd;
    private JLabel win;
    private JLabel minusAce;
    private JLabel strafe;
    private JButton incrKillN;
    private JButton decrKillN;
    private JButton incrKillH;
    private JButton decrKillH;
    private JButton incrDeathN;
    private JButton decrDeathN;
    private JButton incrDeathH;
    private JButton decrDeathH;
    private JComboBox<ComboItem> matches;
    private JButton newMatchButton;
    private JCheckBox winCheckBox;
    private JButton Settings;
    private JLabel sumLabel;
    private JCheckBox deleteMatchCheckBox;
    private JButton submitButton;
    private JButton previousButton;
    private JButton nextButton;

    private int currPos;
    private Stats current;

    List<Stats> statList;
    JFrame frame;

    public gui(String title, int x, int y, LinkedList<Stats> prevContent) {

        previousButton.setText("<--");
        nextButton.setText("-->");
        Settings.setText("\u2699");

        frame = functions.initFrame(title, x, y, panel1);
        startUp(prevContent);

        currPos = matches.getSelectedIndex();
        current = statList.get(currPos);


        winCheckBox.addItemListener(e -> {
            current.setWin(e.getStateChange() == ItemEvent.SELECTED);

            initialLabels(current);

        });

        //Settings.setVisible(false);

        incrKillN.addActionListener(e -> {
            current.incrKillsN();
            initialLabels(current);
        });
        decrKillN.addActionListener(e -> {
            current.decrKillsN();
            initialLabels(current);

        });
        incrKillH.addActionListener(e -> {
            current.incrKillsH();
            initialLabels(current);

        });
        decrKillH.addActionListener(e -> {
            current.decrKillsH();
            initialLabels(current);

        });
        incrDeathN.addActionListener(e -> {
            current.incrDeathN();
            initialLabels(current);

        });
        decrDeathN.addActionListener(e -> {
            current.decrDeathN();
            initialLabels(current);

        });
        incrDeathH.addActionListener(e -> {
            current.incrDeathH();
            initialLabels(current);

        });
        decrDeathH.addActionListener(e -> {
            current.decrDeathH();
            initialLabels(current);

        });

        previousButton.addActionListener(e ->{
            int selected = matches.getSelectedIndex();
            if(selected != 0){
                matches.setSelectedIndex(selected-1);
                initialLabels(statList.get(selected-1));
            }
        });

        nextButton.addActionListener(e -> {
            int selected = matches.getSelectedIndex();
            if(selected != matches.getItemCount()-1){
                matches.setSelectedIndex(selected+1);
                initialLabels(statList.get(selected+1));
            }
        });

        newMatchButton.addActionListener(e -> {

            addStat(statList.size() + 1);
            matches.addItem(new ComboItem("Match " + statList.size(), statList.get(statList.size() - 1)));

            int newMatchPos = matches.getItemCount() - 1;
            matches.setSelectedIndex(newMatchPos);
            current = statList.get(newMatchPos);

            initialLabels(current);
        });

        matches.addActionListener(e -> {
            currPos = matches.getSelectedIndex();
            current = statList.get(currPos);
            initialLabels(current);
        });


        Settings.addActionListener(e -> {
            frame.setEnabled(false);
            new Settings("Settings", 400, 200, statList, frame);
        });

        submitButton.addActionListener(e -> {
            if (deleteMatchCheckBox.isSelected()) {
                removeLastMatch();
                deleteMatchCheckBox.setSelected(false);
            }
        });
    }

    /**
     * Funktion, die beim öffnen des Programms die Save Files sowie die einzelnen Elemente des Programms lädt
     *
     * @param st Linked List, die geladen werden soll
     */
    private void startUp(LinkedList<Stats> st) {
        statList = st;

        //Wenn Liste leer, neue erstellen und erstes Match manuell eintragen
        if (statList == null)
            statList = new LinkedList<>();
        if (statList.isEmpty()) {
            addStat(1);
        }

        //alle Matches in die Combobox laden
        for (Stats sta : statList) {
            matches.addItem(new ComboItem("Match " + sta.getMatch(), sta));
        }

        int size = statList.size() - 1;
        Stats curr = statList.get(size);
        matches.setSelectedIndex(size);
        initialLabels(curr);
    }

    /**
     * - lädt alle Elemente, die im Programm sind
     * - wird jedes Mal aufgerufen, wenn sich ein Button geklickt wird
     *
     * @param st Statistik, die angezeigt werden soll     *
     */
    private void initialLabels(@NotNull Stats st) {

        setDisabledWhenFinished();

        killN.setText("" + st.getKillN());
        killH.setText("" + st.getKillH());
        deathH.setText("" + st.getDeathH());
        deathN.setText("" + st.getDeathN());

        kdLabel.setText("Kd (" + st.getSumKills() + "/" + st.getSumDeaths() + "):");
        kd.setText("" + st.getKd());
        win.setText("" + (st.isWin() ? "gewonnen" : "verloren"));
        minusAce.setText("" + (st.isMinusAce() ? "ja" : "nein"));

        winCheckBox.setSelected(st.isWin());
        strafe.setText(functions.generatePunishment(st));
        strafe.setForeground(Color.red);

        //statList.set(st.getMatch() - 1, st); Todo watch when Error

        int[] punish = getAllPunishments();
        sumLabel.setText("Situps: " + punish[0] + " Liegestütze: " + punish[1]);

        int selected = matches.getSelectedIndex();

        nextButton.setVisible(selected != matches.getItemCount() - 1);
        previousButton.setVisible(selected != 0);

        Saving.save(statList);

        previousButton.setVisible(false);
        nextButton.setVisible(false);
        //Todo fix
    }

    /**
     * der liste wird ein neues Blank Match hinzugefügt
     *
     * @param matchId matchId des neuen Matches
     */
    private void addStat(int matchId) {
        statList.add(new Stats(matchId, 0, 0, 0, 0));
    }

    /**
     * Wenn das angezeigte Match nicht das aktuelle Match ist, werden die Buttons deaktiviert
     */
    private void setDisabledWhenFinished() {

        if (matches.getSelectedIndex() != matches.getItemCount() - 1) {
            this.incrDeathH.setVisible(false);
            this.incrDeathN.setVisible(false);
            this.incrKillH.setVisible(false);
            this.incrKillN.setVisible(false);
            this.decrDeathH.setVisible(false);
            this.decrDeathN.setVisible(false);
            this.decrKillH.setVisible(false);
            this.decrKillN.setVisible(false);
            this.winCheckBox.setVisible(false);
        } else {
            this.incrDeathH.setVisible(true);
            this.incrDeathN.setVisible(true);
            this.incrKillH.setVisible(true);
            this.incrKillN.setVisible(true);
            this.decrDeathH.setVisible(true);
            this.decrDeathN.setVisible(true);
            this.decrKillH.setVisible(true);
            this.decrKillN.setVisible(true);
            this.winCheckBox.setVisible(true);
        }
    }

    /**
     * Wenn das aktuelle Match angezeigt wird, soll das nach Bestätigung gelöscht werden
     */
    private void removeLastMatch() {

        int size = statList.size();
        if (size <= 1) return;

        Stats last = statList.get(size - 1);

        if (last.getSumDeaths() != 0 || last.getSumKills() != 0 || last.isWin()) {
            statList.remove(last);
            matches.setSelectedIndex(size - 2);
            matches.removeItemAt(matches.getItemCount() - 1);

            setDisabledWhenFinished();
        }
    }

    /**
     * Gib alle ausgerechneten Strafen zurück
     *
     * @return int[0] == Anzahl der Situps, int[1] == Anzahl der Liegestütze
     */
    private int[] getAllPunishments() {
        int situp = 0;
        int pushup = 0;

        for (Stats st : statList) {
            String tmp = functions.generatePunishment(st);

            String[] words = tmp.split(" ");

            situp += Integer.parseInt(words[3]);
            pushup += Integer.parseInt(words[6]);
        }
        return new int[]{situp, pushup};
    }
}
