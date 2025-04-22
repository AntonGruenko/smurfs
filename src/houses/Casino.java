package houses;

import smurfs.Smurf;

public class Casino implements Buildable{
    private boolean isBuilt;
    private final String[] COLORS = {"green", "red", "black", "red", "black", "red",
            "black", "red", "black", "red", "black",
            "black", "red", "black", "red", "black",
            "red", "black", "red", "red", "black",
            "red", "black", "red", "black", "red",
            "black", "red", "black", "black", "red",
            "black", "red", "black", "red", "black", "red"};
    private int bet;
    private final int BET_MODE_NUMBER = 0;
    private final int BET_MODE_TWO_NUMBERS = 1;
    private final int BET_MODE_THREE_NUMBERS = 2;
    private final int BET_MODE_FOUR_NUMBERS = 3;
    private final int BET_MODE_SIX_NUMBERS = 4;
    private final int BET_MODE_EVEN_OR_ODD = 5;
    private final int BET_MODE_DOZEN = 6;
    private final int BET_MODE_COLOR = 7;
    private int betMode;
    @Override
    public void build() {
        isBuilt = true;
        betMode = 0;
    }

    public int choseBetMode(){
        String answer;
        System.out.println("Выберите тип ставки: \n" +
                "1. На число \n" +
                "2. На два числа \n" +
                "3. На три числа \n" +
                "4. На четыре числа \n" +
                "5. На шесть чисел \n" +
                "6. На дюжину \n" +
                "7. На цвет \n" +
                "8. На четность");

    }

    public void play(){
//        betMode number = betMode.number;
//        System.out.println(number.toString());
    }

    public boolean setBet(Smurf smurf, int bet){
        int smurfberryCount = smurf.getSmurfberryCount();
        if (smurfberryCount < bet){
            return false;
        }
        else{
            smurf.changeSmurfberryCount(bet, false);
            this.bet += bet;
            return true;
        }
    }
}
