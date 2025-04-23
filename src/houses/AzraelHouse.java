import java.util.Scanner;

public class AzraelHouse {
    private final String[] products = {"Лосось", "Камбала", "Треска", "Палтус", "Тунец"};
    private final String favoriteProduct = "Камбала";
    private Smurf player;
    private int attempts = 0;

    public AzraelHouse(Smurf player) {
        this.player = player;
    }

    public void choose() {
        Scanner scanner = new Scanner(System.in);

        if (player instanceof JoketSmurf) {
            ((JoketSmurf) player).askRiddle();
        }

        while (true) {
            System.out.println("Выберите рыбу, чтобы покормить Азраэля:");
            for (int i = 0; i < products.length; i++) {
                System.out.printf("%d. %s%n", i + 1, products[i]);
            }
            if (player instanceof JoketSmurf) {
                ((JoketSmurf) player).askRiddle();
            }
            System.out.print("Введите номер выбранной рыбы: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Пожалуйста, введите корректный номер.");
                continue;
            }

            if (choice < 1 || choice > products.length) {
                System.out.println("Неверный номер. Попробуйте снова.");
                continue;
            }

            attempts+=1;
            String selectedProduct = products[choice - 1];

            if (selectedProduct.equals(favoriteProduct)) {
                int reward = (attempts == 1) ? 100 : 60;
                player.changeSmurfberryCount(reward, true);
                System.out.printf("Поздравляю, вы построили Дом Азраэля и заработали %d смурфяник!%n", reward);
                break;
            } else {
                System.out.println("Азраэль не любит эту рыбу.");
                System.out.print("Хотите попробовать ещё раз? (да/нет): ");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (!answer.equals("да")) {
                    System.out.println("Игра окончена. Попробуйте снова позже.");
                    break;
                }
            }
        }
        System.out.println("Ваш текущий запас смурфяники: " + player.getSmurfberryCount());
    }
    //тестим
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите Смурфа для игры:");
        System.out.println("1. Папа Смурф");
        System.out.println("2. Весельчак");
        System.out.println("3. Умник");
        System.out.print("Введите номер: ");

        int smurfChoice;
        try {
            smurfChoice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Некорректный ввод. Игра завершена.");
            scanner.close();
            return;
        }

        Smurf player;
        switch (smurfChoice) {
            case 1:
                player = new PapaSmurf();
                break;
            case 2:
                player = new JoketSmurf();
                break;
            case 3:
                player = new BrainySmurf();
                break;
            default:
                System.out.println("Неверный выбор. Игра завершена.");
                scanner.close();
                return;
        }


        AzraelHouse level = new AzraelHouse(player);
        level.choose();

        scanner.close();
    }
}