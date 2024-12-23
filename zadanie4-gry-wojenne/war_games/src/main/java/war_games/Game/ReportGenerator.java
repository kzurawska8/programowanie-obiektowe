package war_games.Game;

import war_games.Generals.General;
import war_games.Generals.Soldier;

public class ReportGenerator {

    public static String generateReport(General general) {
        if (general == null) {
            throw new IllegalArgumentException("General cannot be null.");
        }

        StringBuilder report = new StringBuilder();
        report.append("General: ").append(general.getName()).append("\n");
        report.append("Gold: ").append(general.getGold()).append("\n");
        report.append("Army:\n");

        for (Soldier soldier : general.getArmy().getSoldiers()) {
            report.append(" - Rank: ").append(soldier.getRank())
                  .append(", Experience: ").append(soldier.getExperience())
                  .append(", Strength: ").append(soldier.getStrength())
                  .append("\n");
        }

        return report.toString();
    }

    public static String generateOverallReport(GameState gameState) {
        if (gameState == null) {
            throw new IllegalArgumentException("Game state cannot be null.");
        }

        StringBuilder report = new StringBuilder();
        report.append("===== Overall Game Report =====\n");
        for (General general : gameState.getGenerals()) {
            report.append(generateReport(general)).append("\n");
        }
        return report.toString();
    }
}