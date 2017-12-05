package com.geek.rpg.game.actions;

import com.badlogic.gdx.graphics.Texture;
import com.geek.rpg.game.Assets;
import com.geek.rpg.game.Unit;
import com.geek.rpg.game.effects.DefenceStanceEffect;
import com.geek.rpg.game.effects.HaveDefenderEffect;

public class DefenceStanceAction extends BaseAction {
    public DefenceStanceAction() {
        super("DEFENCE", "btnDefence");
    }

    @Override
    public boolean action(Unit me) {
        if (me.getTarget() == null || !me.isMyTeammate(me.getTarget())) {
            DefenceStanceEffect dse = new DefenceStanceEffect();
            dse.start(me, 1);
            me.addEffect(dse);
            return true;
        }
        if(me.isMyTeammate(me.getTarget())){
            HaveDefenderEffect hde = new HaveDefenderEffect();
            me.getTarget().endEffect(hde.getClass());
            hde.start(me.getTarget(),2);
            me.getTarget().getStats().setDefender(me);
            me.getTarget().addEffect(hde);
            return true;
        }
        return false;
    }
}
