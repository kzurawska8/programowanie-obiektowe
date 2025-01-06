package war_games.Game;

public interface GameActions {
    void startGame();
    void performManeuvers();
    void attack();
    void recruitSoldiers();
    void saveGame();
    void loadGame();
    void showStatus();
}