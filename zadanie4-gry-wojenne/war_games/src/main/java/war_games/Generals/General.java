package war_games.Generals;

import war_games.Utils.InputValidator;

public class General {
    private String name;
    private int gold;
    private Army army;

    public General(String name, int gold, Army army) {
        this.name = name;
        this.gold = gold;
        this.army = army;
    }

    public String getName() {
        return name;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        InputValidator.validatePositiveNumber(gold, "General's gold");
        this.gold = gold;
    }

    public Army getArmy() {
        return army;
    }

    @Override
    public String toString() {
        return "General{name='" + name + "', gold=" + gold + ", army=" + army + '}';
    }
}