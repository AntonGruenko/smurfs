public class JoketSmurf extends Smurf{
    //метод, дающий игроку подсказку на уровне "Дом Азраэля"
    public boolean askRiddle() {
        System.out.println("Весельчаку доступна подсказка в виде загадки:");
        System.out.println("В зыбкой темной глубине");
        System.out.println("День-деньской лежит на дне.");
        System.out.println("На одном боку лежит");
        System.out.println("И добычу сторожит.");
        return true;
    }
}