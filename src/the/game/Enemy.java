package the.game;

import java.math.BigDecimal;

/**
 *
 * @author Mark Aronin
 * @author Alex Craig
 * 
 */

public class Enemy {
    
    private int stats[];
    private int maxHp;
    private int currentHp;
    private int level;
    private int experience;
    
    public Enemy(int enemyStats[], int lvl, double expMod) {
        stats = new int[5];
        stats = enemyStats;
        maxHp = stats[2] * 10;
        currentHp = stats[2] * 10;
        level = lvl;
        
        BigDecimal tempStat = new BigDecimal(expMod * level);
        tempStat = tempStat.setScale(0, BigDecimal.ROUND_HALF_UP);
        experience = tempStat.intValue();
        
    } //end of constructor
    
    public int getMaxHp() {
        return maxHp;
    }
    
    public int getCurrentHp() {
        return currentHp;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getExperience() {
        return experience;
    }

}