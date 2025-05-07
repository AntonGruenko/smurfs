package houses;

import smurfs.Smurf;

import java.util.Random;
import java.util.Scanner;

public class Casino implements Buildable{
    private boolean isBuilt;
    private final Scanner scanner = new Scanner(System.in);
    private final String[] COLORS = {"зеленое", "красное", "черное", "красное", "черное", "красное",
            "черное", "красное", "черное", "красное", "черное",
            "черное", "красное", "черное", "красное", "черное",
            "красное", "черное", "красное", "красное", "черное",
            "красное", "черное", "красное", "черное", "красное",
            "черное", "красное", "черное", "черное", "красное",
            "черное", "красное", "черное", "красное", "черное", "красное"};
    private int bet;
    private String answer;
    private Smurf player;
    private final int BET_MODE_NUMBER = 1;
    private final int BET_MODE_TWO_NUMBERS = 2;
    private final int BET_MODE_THREE_NUMBERS = 3;
    private final int BET_MODE_FOUR_NUMBERS = 4;
    private final int BET_MODE_SIX_NUMBERS = 5;
    private final int BET_MODE_DOZEN = 6;
    private final int BET_MODE_COLOR = 7;
    private final int BET_MODE_EVEN_OR_ODD = 8;
    private int betMode;
    private final Random random = new Random();
    @Override
    public void build() {
        this.isBuilt = true;
        this.betMode = 0;

    }

    public void setPlayer(Smurf player){
        this.player = player;
    }

    public int getBet(){
        return this.bet;
    }

    public int choseBetMode(){
        scanner.nextLine();
        System.out.println("Выберите тип ставки или напишите \"выход\": \n" +
                "1. На число \n" +
                "2. На два числа \n" +
                "3. На три числа \n" +
                "4. На четыре числа \n" +
                "5. На шесть чисел \n" +
                "6. На дюжину \n" +
                "7. На цвет \n" +
                "8. На четность");
        answer = scanner.nextLine();
        if (answer.equals("выход")){
            return 0;
        }
        int res = setBetModeFromAnswer(answer);
        if (res == 0){
            System.out.println("Нет такого номера ставки!");
            return choseBetMode();
        }
        return res;
    }

    public void play(){
        System.out.println("Введите вашу ставку!");
        if (scanner.hasNextInt()) {
            int inputBet = scanner.nextInt();
            setBet(inputBet);
        }
        else{
            System.out.println("Это не число");
            play();
        }
        int mode = choseBetMode();
        switch (mode){
            case 0:
                return;
            case 1:
                playOneNumber();
                break;
            case 6:
                playDozen();
                break;
            case 7:
                playColor();
                break;
            case 8:
                playOddOrNot();
                break;
        }
        playAgainOffer();
    }


    public boolean setBet(int bet){
        int smurfberryCount = player.getSmurfberryCount();
        if (smurfberryCount < bet){
            return false;
        }
        else{
            player.changeSmurfberryCount(bet, false);
            this.bet += bet;
            return true;
        }
    }

    int setBetModeFromAnswer(String answer){
        this.betMode = switch (answer){
            case "1" -> this.BET_MODE_NUMBER;
            case "2" -> this.BET_MODE_TWO_NUMBERS;
            case "3" -> this.BET_MODE_THREE_NUMBERS;
            case "4" -> this.BET_MODE_FOUR_NUMBERS;
            case "5" -> this.BET_MODE_SIX_NUMBERS;
            case "6" -> this.BET_MODE_DOZEN;
            case "7" -> this.BET_MODE_COLOR;
            case "8" -> this.BET_MODE_EVEN_OR_ODD;
            default -> 0;
        };
        return this.betMode;
    }

    int spin(){
        int res = random.nextInt(37);
        System.out.printf("Вам выпало... %d, %s! \n", res, COLORS[res]);
        return res;
    }

    void playOneNumber(){
        System.out.println("Введите одно число!");
        answer = scanner.nextLine();
        if (answer.equals("выход")){
            exit();
            return;
        }
        try {
            int answerNum = Integer.parseInt(answer);
            int spinRes = spin();
            if (answerNum == spinRes){
                System.out.printf("Вы выиграли %d!", bet * 35);
                this.bet *= 36;
            }
            else{
                System.out.println("Вы проиграли");
                this.bet = 0;
            }
        }
        catch (NumberFormatException exception){
            System.out.println("Это не число!");
        }
    }

    void playDozen(){
        System.out.println("Cтавим на...\n" +
                "1. Первая дюжина (1-12)\n" +
                "2. Вторая дюжина (13-24)\n" +
                "3. Третья дюжина (25-36)");
        answer = scanner.nextLine();
        if (answer.equals("выход")){
            exit();
            return;
        }
        int answerRes = switch (answer){
            case "1" -> 0;
            case "2" -> 1;
            case "3" -> 2;
            default -> -1;
        };
        int spinRes = spin();
        int spinDozen = (spinRes - 1) / 12;
        if (spinDozen == answerRes && (spinRes != 0)){
            System.out.printf("Вы выиграли %d!", bet * 2);
            this.bet *= 3;
        }
        else{
            System.out.println("Вы проиграли");
            this.bet = 0;
        }
    }
    void playOddOrNot(){
        System.out.println("Cтавим на...\n" +
                "1. Нечетное\n" +
                "2. Четное");
        answer = scanner.nextLine();
        if (answer.equals("выход")){
            exit();
            return;
        }
        int answerRes = switch (answer){
            case "1" -> 1;
            case "2" -> 2;
            default -> -1;
        };
        int spinRes = spin();
        if ((spinRes % 2 == answerRes) && (spinRes != 0)){
            System.out.printf("Вы выиграли %d!", bet);
            this.bet *= 2;
        }
        else{
            System.out.println("Вы проиграли");
            this.bet = 0;
        }
    }

    void playColor(){
        System.out.println("Cтавим на...\n" +
                "1. Красное\n" +
                "2. Черное");
        answer = scanner.nextLine();
        if (answer.equals("выход")){
            exit();
            return;
        }
        String answerRes = switch (answer){
            case "0" -> "зеленое";
            case "1" -> "красное";
            case "2" -> "черное";
            default -> "-1";
        };
        int spinRes = spin();
        if (COLORS[spinRes].equals(answerRes)){
            System.out.printf("Вы выиграли %d!", bet);
            this.bet *= 2;
        }
        else{
            System.out.println("Вы проиграли");
            this.bet = 0;
        }
    }

    void exit(){
        player.changeSmurfberryCount(bet, true);
        bet = 0;
    }

    void playAgainOffer(){
        System.out.println("Сыграем еще раз?\n" +
                "1. Да \n" +
                "2. Нет");
        answer = scanner.nextLine();
        if (answer.equals("1")){
            play();
        }
        else{
            exit();
        }
    }
}
