import javax.swing.*;

public class functions {

    private static Stats stats;
    private static int situps;
    private static int pushUps;

    public static String generatePunishment(Stats stat) {
        stats = stat;
        situps = -20;
        pushUps = 0;

        punishDeaths();
        punishHeadShot();
        punishLose();
        punishMinusAce();
        punishNegativeKd();
        bonusKills();
        bonusHeadShotKills();
        bonusWin();

        if (situps < 0) situps = 0;
        if (pushUps < 0) situps = 0;

        return "Die Strafe beträgt " + situps + " Situps und " + pushUps + " Liegestütze";
    }

    private static void punishDeaths() {
        pushUps += 10 * stats.getDeathN();
    }


    private static void punishHeadShot() {
        situps += 10 * stats.getDeathH();
    }

    private static void punishLose() {
        if (!stats.isWin()) situps += 10;
    }

    private static void punishMinusAce() {

        if (stats.isMinusAce()) {
            situps += 20;
            pushUps += 20;
        }
    }

    private static void punishNegativeKd() {
        if (stats.getKd() < 1.0) situps += 20;
    }

    private static void bonusWin() {
        if (stats.isWin()) situps -= 10;
    }

    private static void bonusKills() {
        pushUps -= 5 * stats.getKillN();
        if(pushUps < 0) pushUps = 0;
    }

    private static void bonusHeadShotKills() {
        situps -= 5 * stats.getKillH();
        if (situps < 0) situps = 0;
    }

    public static JFrame initFrame(String title, int x, int y, JPanel panel1) {
        JFrame frame = new JFrame(title);

        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(x, y);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }
}
