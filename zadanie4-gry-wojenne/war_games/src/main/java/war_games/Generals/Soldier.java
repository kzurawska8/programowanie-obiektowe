package war_games.Generals;

import war_games.Utils.InputValidator;

public class Soldier {
    private SoldierRank rank;
    private int experience;

    public Soldier(SoldierRank rank) {
        this.rank = rank;
        this.experience = 1;
        validateState();
    }

    public SoldierRank getRank() {
        return rank;
    }

    public int getExperience() {
        return experience;
    }

    public int getStrength() {
        return rank.getValue() * experience;
    }

    public void gainExperience() {
        experience++;
        validateState();
        if (experience >= 5 * rank.getValue()) {
            promote();
        }
    }

    public void loseExperience() {
        experience--;
        validateState();
    }

    private void promote() {
        switch (rank) {
            case PRIVATE -> rank = SoldierRank.CORPORAL;
            case CORPORAL -> rank = SoldierRank.CAPTAIN;
            case CAPTAIN -> rank = SoldierRank.MAJOR;
            default -> {}
        }
        experience = 1;
    }

    public boolean isAlive() {
        return experience > 0;
    }

    private void validateState() {
        InputValidator.validatePositiveNumber(experience, "Soldier experience");
        if (rank == null) {
            throw new IllegalArgumentException("Soldier rank must not be null.");
        }
    }
}