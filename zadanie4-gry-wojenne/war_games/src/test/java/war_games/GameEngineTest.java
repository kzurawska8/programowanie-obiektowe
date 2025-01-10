package war_games;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import war_games.Game.GameEngine;
import war_games.Game.GameState;
import war_games.Game.Logger;
import war_games.Game.ReportGenerator;
import war_games.Generals.Army;
import war_games.Generals.General;
import war_games.Generals.Soldier;
import war_games.Generals.SoldierRank;

class GameEngineTest {

    private GameEngine gameEngine;
    private General general1;
    private General general2;

    @BeforeEach
    public void setUp() {
        Army army1 = new Army();
        Army army2 = new Army();
        general1 = new General("General A", 100, army1);
        general2 = new General("General B", 100, army2);
        List<General> generals = new ArrayList<>();
        generals.add(general1);
        generals.add(general2);
        GameState gameState = new GameState(generals);
        gameEngine = new GameEngine(gameState);
    }

    @Test
    public void testAttack() {
        general1.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        general2.getArmy().addSoldier(new Soldier(SoldierRank.PRIVATE));
        gameEngine.attack();
        assertEquals(100, general1.getGold());
    }

    @Test
    public void testRecruitSoldiers_EnoughGold() {
        general1.setGold(50);
        gameEngine.recruitSoldiers(SoldierRank.PRIVATE);
        assertEquals(40, general1.getGold());
    }

    @Test
    public void testRecruitSoldiers_NotEnoughGold() {
        general1.setGold(5);
        gameEngine.recruitSoldiers(SoldierRank.PRIVATE);
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
        assertEquals(110, general1.getGold());
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
        soldier.setExperience(0);
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
        soldier.setExperience(2);
        soldier.loseExperience();
        assertTrue(soldier.isAlive());
    }

    @Test
    public void testPromote() {
        Soldier soldier = new Soldier(SoldierRank.PRIVATE);
        for (int i = 0; i < 5; i++) {
            soldier.gainExperience();
        }
        assertEquals(SoldierRank.CORPORAL, soldier.getRank());
    }
}