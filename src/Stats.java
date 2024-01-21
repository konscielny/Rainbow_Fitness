import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;


public class Stats {
    private final int match;
    private final Date timeStamp;

    private int killN;
    private int killH;
    private int deathN;
    private int deathH;
    private boolean win;
    private double kd;
    private boolean minusAce;

    private int sumKills;
    private int sumDeaths;

    public Stats(int match, int killN, int killH, int deathN, int deathH) {

        this.match = match;
        this.killN = killH;
        this.killN = killN;
        this.deathH = deathH;
        this.deathN = deathN;

        timeStamp = new Date();

        System.out.println(timeStamp);
        this.win = false;

        calcKills();
        calcDeaths();
        calcKd();
        proofMinusAce();
    }

    public void calcKills() {
        this.sumKills = this.killH + this.killN;
        proofMinusAce();
        calcKd();
    }

    public void calcDeaths() {
        this.sumDeaths = this.deathH + this.deathN;
        proofMinusAce();
        calcKd();
    }

    public void calcKd() {
        if (this.sumDeaths == 0)
            this.kd = this.sumKills;
        else
            this.kd = (double) this.sumKills / (double) this.sumDeaths;

        this.kd = new BigDecimal(this.kd).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }


    public int getMatch() {
        return match;
    }

    public Date getTimeStamp(){
        return timeStamp;
    }

    public int getKillN() {
        return killN;
    }

    public int getKillH() {
        return killH;
    }

    public int getDeathN() {
        return deathN;
    }

    public int getDeathH() {
        return deathH;
    }

    public boolean isWin() {
        return win;
    }

    public double getKd() {
        return kd;
    }

    public int getSumKills() {
        return sumKills;
    }

    public int getSumDeaths() {
        return sumDeaths;
    }

    public boolean isMinusAce() {
        return this.minusAce;
    }

    public void proofMinusAce() {
        this.minusAce = this.sumDeaths - this.sumKills >= 5;
    }

    public void incrKillsN() {
        this.killN += 1;
        calcKills();
    }

    public void incrKillsH() {
        this.killH += 1;
        calcKills();
    }

    public void incrDeathN() {
        this.deathN += 1;
        calcDeaths();
    }

    public void incrDeathH() {
        this.deathH += 1;
        calcDeaths();
    }

    public void decrKillsN() {
        this.killN -= 1;

        if (this.killN < 0) this.killN = 0;
        calcKills();
    }

    public void decrKillsH() {
        this.killH -= 1;
        if (this.killH < 0) this.killH = 0;

        calcKills();
    }

    public void decrDeathN() {
        this.deathN -= 1;
        if (this.deathN < 0) this.deathN = 0;

        calcDeaths();
    }

    public void decrDeathH() {
        this.deathH -= 1;
        if (this.deathH < 0) this.deathH = 0;

        calcDeaths();
    }


    public void setWin(boolean w) {
        this.win = w;
    }
}
