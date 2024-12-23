package war_games.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import war_games.Generals.General;
import war_games.Generals.Soldier;
import war_games.Generals.SoldierRank;

public class GameEngine implements GameActions {
    private final GameState gameState;
    private final Scanner scanner;

    public GameEngine() {
        this.gameState = GameState.loadLastState();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void startGame() {
        System.out.println("Welcome to War Games!");
        while (true) {
            displayMenu();
            int choice = getValidatedChoice();
            switch (choice) {
                case 1 -> attack();
                case 2 -> recruitSoldiers();
                case 3 -> saveGame();
                case 4 -> loadGame();
                case 5 -> showStatus();
                case 6 -> {
                    System.out.println("Exiting game...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
            autoSave();
        }
    }

    private void displayMenu() {
        System.out.println("1. Attack");
        System.out.println("2. Recruit Soldiers");
        System.out.println("3. Save Game");
        System.out.println("4. Load Game");
        System.out.println("5. Show Status");
        System.out.println("6. Exit");
    }

    private int getValidatedChoice() {
        int choice = -1;
        while (choice < 1 || choice > 6) {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
        return choice;
    }

    @Override
    public void attack() {
        System.out.println("Attacking...");
    }

    @Override
    public void recruitSoldiers() {
        List<General> generals;
        try {
            generals = gameState.getGenerals();
        } catch (ClassCastException e) {
            System.out.println("Error: Invalid generals list format.");
            generals = new ArrayList<>();
        }
        if (generals.isEmpty()) {
            System.out.println("No generals available.");
            return;
        }

        General general = generals.get(0);
        System.out.println("Choose rank of soldier to recruit: 1.PRIVATE, 2.CORPORAL, 3.CAPTAIN, 4.MAJOR");
        int choice = getValidatedChoice();
        SoldierRank rank = switch (choice) {
            case 1 -> SoldierRank.PRIVATE;
            case 2 -> SoldierRank.CORPORAL;
            case 3 -> SoldierRank.CAPTAIN;
            case 4 -> SoldierRank.MAJOR;
            default -> throw new IllegalArgumentException("Invalid choice.");
        };
        int cost = 10 * rank.getValue();
        if (general.getGold() >= cost) {
            general.getArmy().addSoldier(new Soldier(rank));
            general.setGold(general.getGold() - cost);
            System.out.println("Soldier recruited!");
        } else {
            System.out.println("Not enough gold.");
        }
    }

    @Override
    public void saveGame() {
        System.out.println("Saving game...");
        gameState.save("game_state.dat");
    }

    @Override
    public void loadGame() {
        System.out.println("Loading game...");
        gameState.load("game_state.dat");
    }

    @Override
    public void showStatus() {
        System.out.println("Game Status:");
        System.out.println(gameState);
    }

    private void autoSave() {
        saveGame();
    }
}