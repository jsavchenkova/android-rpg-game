package com.geek.rpg.game.actions;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.geek.rpg.game.*;

public class MeleeAttackAction extends BaseAction {
    public MeleeAttackAction() {
        super("MELEE_ATTACK", "btnMeleeAttack");
    }

    @Override
    public boolean action(Unit me) {
        if (me.getTarget() == null) return false;
        if (me.isMyTeammate(me.getTarget())) return false;
        me.setAttackAction(1.0f);
        me.setCurrentAnimation(Unit.AnimationType.ATTACK);
        if (!Calculator.isTargetEvaded(me, me.getTarget())) {
            int dmg = Calculator.getMeleeDamage(me, me.getTarget());
            if(me.getTarget().getStats().getDefender() != null){
                me.getTarget().getStats().getDefender().changeHp(-(1+dmg*2/3));
                me.getTarget().changeHp(-(1+dmg/3));
                me.getTarget().getStats().setDefender(null);
            }else {
                me.getTarget().changeHp(-dmg);
            }
        } else {
            me.getTarget().evade();
        }

        return true;
    }
}
