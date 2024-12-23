package war_games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import war_games.Game.*;
import war_games.Generals.*;

import java.io.File;
import java.util.List;

class GameEngineTest {

    private General general1;
    private General general2;
    private Army army1;
    private Army army2;
    private GameState gameState;

    @BeforeEach
    void setUp() {
        general1 = new General("General A", 1000, new Army());
        general2 = new General("General B", 1000, new Army());
        army1 = general1.getArmy();
        army2 = general2.getArmy();
        gameState = new GameState();

        // Add soldiers to the armies
        for (int i = 0; i < 5; i++) {
            army1.addSoldier(new Soldier(SoldierRank.PRIVATE));
            army2.addSoldier(new Soldier(SoldierRank.PRIVATE));
        }
    }

    @Test
    void testNegativeExperienceNotAllowed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Soldier(SoldierRank.PRIVATE, -1));
        assertEquals("Experience cannot be negative.", exception.getMessage());
    }

    @Test
    void testNullRankNotAllowed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Soldier(null));
        assertEquals("Soldier rank must not be null.", exception.getMessage());
    }

    @Test
    void testManewry() {
        int initialGold = general1.getGold();
        List<Soldier> soldiers = army1.getSoldiers();

        int totalCost = soldiers.stream()
                .mapToInt(s -> s.getRank().getValue())
                .sum();
        general1.setGold(general1.getGold() - totalCost);
        soldiers.forEach(Soldier::gainExperience);

        assertEquals(initialGold - totalCost, general1.getGold());
        soldiers.forEach(s -> assertEquals(2, s.getExperience()));
    }

    @Test
    void testAttackVictory() {
        BattleResult result = BattleEngine.simulateBattle(general1, general2);
        assertNotNull(result);
        assertEquals(general1, result.getWinner());
        assertTrue(army2.getSoldiers().isEmpty());
        assertFalse(army1.getSoldiers().isEmpty());
    }

    @Test
    void testRemoveDeadSoldiersFromEmptyArmy() {
        Army emptyArmy = new Army();
        emptyArmy.removeDeadSoldiers();
        assertTrue(emptyArmy.getSoldiers().isEmpty());
    }

    @Test
    void testLoadEmptyGameState() {
        String fileName = "empty_game_state.dat";
        new File(fileName).delete(); // Ensure the file does not exist
        GameState loadedState = gameState.load(fileName);
        assertNotNull(loadedState);
        assertTrue(loadedState.getGenerals().isEmpty());
    }

    @Test
    void testSetNegativeGoldNotAllowed() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> general1.setGold(-100));
        assertEquals("General's gold must be greater than zero.", exception.getMessage());
    }

    @Test
    void testGenerateReportEmptyArmy() {
        General general = new General("Test", 100, new Army());
        String report = ReportGenerator.generateReport(general);
        assertTrue(report.contains("Army:"));
    }

    @Test
    void testLogMessage() {
        Logger.log("Test message", true);
        File logFile = new File("game_log.txt");
        assertTrue(logFile.exists());
        assertTrue(logFile.length() > 0);
    }

    @Test
    void testArmyAfterBattle() {
        // Simulate a battle
        BattleResult result = BattleEngine.simulateBattle(general1, general2);

        // Verify post-battle state
        if (result.getWinner() == general1) {
            assertFalse(army1.getSoldiers().isEmpty());
            assertTrue(army2.getSoldiers().isEmpty());
        } else {
            assertFalse(army2.getSoldiers().isEmpty());
            assertTrue(army1.getSoldiers().isEmpty());
        }
    }

    @Test
    void testSaveAndLoadGameState() {
        String fileName = "test_game_state.dat";
        gameState.save(fileName);
        GameState loadedState = gameState.load(fileName);

        assertNotNull(loadedState);
        assertEquals(gameState.getGenerals().size(), loadedState.getGenerals().size());
    }
}