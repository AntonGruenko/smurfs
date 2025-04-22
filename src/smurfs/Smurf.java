package smurfs;

import java.util.Random;

public abstract class Smurf {
    private int smurfberryCount;
    private String[] positiveReactions;
    private String[] negativeReactions;
    private double intelligence;
    private double luck;
    private double respect;

    public int getSmurfberryCount() {
        return smurfberryCount;
    }

    public void setSmurfberryCount(int smurfberryCount) {
        this.smurfberryCount = smurfberryCount;
    }

    public String[] getPositiveReactions() {
        return positiveReactions;
    }

    public void setPositiveReactions(String[] positiveReactions) {
        this.positiveReactions = positiveReactions;
    }

    public String[] getNegativeReactions() {
        return negativeReactions;
    }

    public void setNegativeReactions(String[] negativeReactions) {
        this.negativeReactions = negativeReactions;
    }

    public double getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(double intelligence) {
        this.intelligence = intelligence;
    }

    public double getRespect() {
        return respect;
    }

    public void setRespect(double respect) {
        this.respect = respect;
    }

    public double getLuck() {
        return luck;
    }

    public void setLuck(double luck) {
        this.luck = luck;
    }

    public String reactNegatively(){
        int len = this.negativeReactions.length;
        Random random = new Random();
        int reactionIndex = random.nextInt(len);
        return this.negativeReactions[reactionIndex];
    }

    public String reactPositively(){
        int len = this.positiveReactions.length;
        Random random = new Random();
        int reactionIndex = random.nextInt(len);
        return this.positiveReactions[reactionIndex];
    }

    public int changeSmurfberryCount(int change, boolean isChangePositive){
        if (isChangePositive){
            this.smurfberryCount += change;
        }
        else{
            this.smurfberryCount -= change;
        }
        return this.smurfberryCount;
    }
}
