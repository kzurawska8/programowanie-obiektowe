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

public class GameState implements Serializable, Data {
    private static final long serialVersionUID = 1L;
    private List<General> generals;

    public GameState(List<General> generals) {
        this.generals = generals;
    }

    public List<General> getGenerals() {
        return generals;
    }

    public static GameState loadLastState() {
        GameState tempState = new GameState(new ArrayList<>());
        return tempState.load("game_state.dat");
    }

    @Override
    public GameState load(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            Object obj = ois.readObject();
            if (obj instanceof GameState) {
                return (GameState) obj;
            } else {
                throw new ClassNotFoundException("Invalid game state data.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No valid previous state found. Starting new game.");
            return new GameState(new ArrayList<>());
        }
    }

    @Override
    public void save(String fileName) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(this);
            System.out.println("Game state saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving game state: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "Generals: " + generals;
    }
}