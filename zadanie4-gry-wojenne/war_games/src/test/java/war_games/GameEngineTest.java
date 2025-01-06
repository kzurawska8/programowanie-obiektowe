package war_games;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import war_games.Game.*;
import war_games.Generals.*;
import war_games.Utils.InputValidator;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameEngineTest {

    private GameEngine gameEngine;
    private General general1;
    private General general2;

    @BeforeEach
    public void setUp() {
        Army army1 = new Army();
        Army army2 = new Army();
        general1 = new General("General A", 100, army1);
        general2 = new General("General B", 150, army2);
        List<General> generals = new ArrayList<>();
        generals.add(general1);
        generals.add(general2);
        GameState gameState = new GameState(generals);
        gameEngine = new GameEngine();
    }

    @Test
    public void testAttack() {
        general1.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        general2.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        gameEngine.attack();
        assertEquals(1, general1.getGold());
    }

    @Test
    public void testRecruitSoldiers_EnoughGold() {
        general1.setGold(50);
        gameEngine.recruitSoldiers();
        assertEquals(40, general1.getGold());
    }

    @Test
    public void testRecruitSoldiers_NotEnoughGold() {
        general1.setGold(5);
        gameEngine.recruitSoldiers();
        assertEquals(5, general1.getGold());
    }

    @Test
    public void testPerformManeuvers() {
        general1.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        gameEngine.performManeuvers();
        assertEquals(2, general1.getArmy().getSoldiers().get(0).getExperience());
    }

    @Test
    public void testSaveGame() {
        gameEngine.saveGame();
        assertTrue(true);
    }

    @Test
    public void testShowStatus() {
        assertDoesNotThrow(() -> gameEngine.showStatus());
    }

    @Test
    public void testInvalidChoice() {
        int choice = gameEngine.getValidatedChoice();
        assertTrue(choice >= 1 && choice <= 6);
    }

    @Test
    public void testLogGameState() {
        gameEngine.logGameState();
        assertTrue(true);
    }

    @Test
    public void testAutoSave() {
        gameEngine.autoSave();
        assertTrue(true);
    }

    @Test
    public void testProcessBattleOutcome() {
        gameEngine.processBattleOutcome(general1, general2);
        assertEquals(115, general1.getGold());
    }

    @Test
    public void testProcessTie() {
        gameEngine.processTie(general1, general2);
        assertEquals(0, general1.getArmy().getSoldiers().size());
    }

    @Test
    public void testRemoveRandomSoldier() {
        general1.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        gameEngine.removeRandomSoldier(general1);
        assertEquals(0, general1.getArmy().getSoldiers().size());
    }

    @Test
    public void testGameStateInitialization() {
        assertThrows(IllegalArgumentException.class, () -> new GameState(new ArrayList<>()));
    }

    @Test
    public void testGameStateLoad() {
        GameState gameState = new GameState(List.of(general1, general2));
        gameState.save("test_save.dat");
        GameState loadedState = GameState.load("test_save.dat");
        assertNotNull(loadedState);
    }

    @Test
    public void testValidateGameState() {
        assertDoesNotThrow(() -> GameState.validateGameState(new GameState(List.of(general1, general2))));
    }

    @Test
    public void testLoadLastState() {
        assertNotNull(GameState.loadLastState());
    }

    @Test
    public void testSaveMethod() {
        GameState gameState = new GameState(List.of(general1, general2));
        gameState.save("test_save.dat");
        assertTrue(true);
    }

    @Test
    public void testLoggerLogMessage() {
        Logger.log("Test log message");
        assertTrue(true);
    }

    @Test
    public void testLoggerLogWithConsoleOutput() {
        Logger.log("Test log message with console output", true);
        assertTrue(true);
    }

    @Test
    public void testGenerateReport() {
        String report = ReportGenerator.generateReport(general1);
        assertTrue(report.contains("General: General A"));
    }

    @Test
    public void testGenerateOverallReport() {
        GameState gameState = new GameState(List.of(general1, general2));
        String report = ReportGenerator.generateOverallReport(gameState);
        assertTrue(report.contains("General A"));
    }

    @Test
    public void testAddSoldier() {
        general1.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        assertEquals(1, general1.getArmy().getSoldiers().size());
    }

    @Test
    public void testCalculateTotalStrength() {
        general1.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        assertEquals(1, general1.getArmy().calculateTotalStrength());
    }

    @Test
    public void testRemoveDeadSoldiers() {
        Soldier soldier = new Soldier(SoldierRank.PRIVATE);
        general1.getArmy().addSoldier(soldier);
        soldier.loseExperience();
        general1.getArmy().removeDeadSoldiers();
        assertEquals(0, general1.getArmy().getSoldiers().size());
    }

    @Test
    public void testPerformManeuversWithSoldiers() {
        Soldier soldier = new Soldier(SoldierRank.PRIVATE);
        general1.getArmy().addSoldier(soldier);
        gameEngine.performManeuvers();
        assertEquals(2, soldier.getExperience());
    }

    @Test
    public void testGetStrength() {
        Soldier soldier = new Soldier(SoldierRank.PRIVATE);
        assertEquals(1, soldier.getStrength());
    }

    @Test
    public void testGainExperience() {
        Soldier soldier = new Soldier(SoldierRank.PRIVATE);
        soldier.gainExperience();
        assertEquals(2, soldier.getExperience());
    }

    @Test
    public void testLoseExperience() {
        Soldier soldier = new Soldier(SoldierRank.PRIVATE);
        soldier.loseExperience();
        assertFalse(soldier.isAlive());
    }

    @Test
    public void testPromote() {
        Soldier soldier = new Soldier(SoldierRank.PRIVATE);
        for (int i = 0; i < 5; i++) {
            soldier.gainExperience();
        }
        assertEquals(SoldierRank.CORPORAL, soldier.getRank());
    }

    @Test
    public void testValidatePositiveNumber() {
        assertThrows(IllegalArgumentException.class, () -> InputValidator.validatePositiveNumber(-1, "Test Field"));
    }
}