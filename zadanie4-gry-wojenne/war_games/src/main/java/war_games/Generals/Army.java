package war_games.Generals;

import java.util.ArrayList;
import java.util.List;

public class Army {
    private final List<Soldier> soldiers = new ArrayList<>();

    public void addSoldier(Soldier soldier) {
        if (soldier == null) {
            throw new IllegalArgumentException("Soldier cannot be null.");
        }
        soldiers.add(soldier);
    }

    public int calculateTotalStrength() {
        return soldiers.stream()
                .filter(Soldier::isAlive)
                .mapToInt(Soldier::getStrength)
                .sum();
    }

    public List<Soldier> getSoldiers() {
        return soldiers;
    }

    public void removeDeadSoldiers() {
        soldiers.removeIf(soldier -> !soldier.isAlive());
    }
}