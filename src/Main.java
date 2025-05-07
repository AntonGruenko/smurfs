import houses.Casino;
import smurfs.PapaSmurf;

import java.util.Random;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        PapaSmurf papa = new PapaSmurf();
        papa.setSmurfberryCount(100);
        Casino casino = new Casino();
        casino.build();
        casino.setPlayer(papa);
        casino.play();
        System.out.println(papa.getSmurfberryCount());
    }
}