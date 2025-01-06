package war_games.Game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import war_games.Generals.General;
import war_games.Generals.Soldier;

public class GameState implements Serializable, Data {
    private static final long serialVersionUID = 1L;
    private List<General> generals;

    public GameState(List<General> generals) {
        if (generals.size() != 2) {
            throw new IllegalArgumentException("There must be exactly 2 generals in the game.");
        }
        this.generals = generals;
    }

    public List<General> getGenerals() {
        return generals;
    }

    @Override
    public GameState load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof GameState) {
                GameState loadedState = (GameState) obj;
                validateGameState(loadedState);
                return loadedState;
            } 
            else {
                throw new ClassNotFoundException("Invalid game state data.");
            }
        } 
        catch (IOException | ClassNotFoundException e) {
            System.out.println("No valid previous state found. Starting new game.");
            return new GameState(new ArrayList<>());
        }
    }

    public void validateGameState(GameState gameState) {
        if (gameState.getGenerals().size() != 2) {
            throw new IllegalArgumentException("Invalid number of generals. Exactly 2 generals are required.");
        }
        for (General general : gameState.getGenerals()) {
            if (general.getGold() < 0) {
                throw new IllegalArgumentException("General's gold must be non-negative.");
            }
            for (Soldier soldier : general.getArmy().getSoldiers()) {
                if (soldier.getExperience() < 0) {
                    throw new IllegalArgumentException("Soldier experience must be non-negative.");
                }
                if (soldier.getRank() == null) {
                    throw new IllegalArgumentException("Soldier rank cannot be null.");
                }
            }
        }
    }

    public static GameState loadLastState() {
        return new GameState(new ArrayList<>()).load("game_state.dat");
    }

    @Override
    public void save(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Game state saved to " + fileName);
        } 
        catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Generals: " + generals;
    }
}