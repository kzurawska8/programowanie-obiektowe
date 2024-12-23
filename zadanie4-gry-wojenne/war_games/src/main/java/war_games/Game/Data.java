package war_games.Game;

public interface Data {
    void save(String fileName);
    GameState load(String fileName);
}