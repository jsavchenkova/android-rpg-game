package com.geek.rpg.game.effects;

import com.geek.rpg.game.FlyingText;
import com.geek.rpg.game.Unit;




/**
 * Created by jsavchenkova on 03.12.2017.
 */

public class HaveDefenderEffect extends Effect {
    @Override
    public void start(Unit unit, int rounds) {
        super.start(unit, rounds);

        unit.getBattleScreen().getInfoSystem().addMessage("Up Behind a back", unit, FlyingText.Colors.GREEN);
    }

    @Override
    public void end() {
        unit.getBattleScreen().getInfoSystem().addMessage("Down Behind a back", unit, FlyingText.Colors.WHITE);
        unit.getStats().setDefender(null);
    }
}
