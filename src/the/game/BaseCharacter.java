package the.game;

import java.math.BigDecimal;

/**
 *
 * @author Mark Aronin
 * @author Alex Craig
 * 
 */

public class BaseCharacter {
    
    private int requiredExperience[];
    private int baseCharacterStats[][];
    private int fullStats[][];
    private double statMultipliers[];
    private int maxHp;
    private int currentHp;
    private int maxMana;
    private int currentMana;
    private int currentExperience;
    private int currentLevel;
    
    public BaseCharacter(double sMult[]) {
        statMultipliers = new double[5];
        statMultipliers = sMult;
        requiredExperience = new int[100];
        requiredExperience[0] = 0;
        baseCharacterStats = new int[5][100];
        
        for (int i=1; i<requiredExperience.length; i++) {
            requiredExperience[i] = 60 * (i+1)^2 + i+1;
        }
        
        for (int j=0; j<baseCharacterStats.length; j++) {
            for (int k=0; k<baseCharacterStats[j].length; k++) {
                BigDecimal tempStat = new BigDecimal(statMultipliers[j] * (k+1));
                tempStat = tempStat.setScale(0, BigDecimal.ROUND_HALF_UP);
                baseCharacterStats[j][k] = tempStat.intValue();
                fullStats[j][k] = tempStat.intValue();
            }
        }
        
        currentLevel = 1;
        currentExperience = 0;
        currentHp = baseCharacterStats[2][0] * 10;
        maxHp = baseCharacterStats[2][0] * 10;
        currentMana = baseCharacterStats[4][0] * 5;
        maxMana = baseCharacterStats[4][0] * 5;

    } //end of constructor
    
    public int getMaxHp() {
        return maxHp;
    }
    
    public int getCurrentHp() {
        return currentHp;
    }
    
    public int getMaxMana() {
        return maxMana;
    }
    
    public int getCurrentMana() {
        return currentMana;
    }
    
    public int getCurrentExperience() {
        return currentExperience;
    }
    
    public int getCurrentLevel() {
        return currentLevel;
    }
    
    public int rewardExperience(Enemy enemy) {
        currentExperience += enemy.getExperience();
        
        if (currentExperience >= requiredExperience[currentLevel+1]) {
            rewardLevel();
        }
        
        return currentExperience;
    } //end of rewardExperience
    
    public int rewardLevel() {
        currentExperience -= requiredExperience[currentLevel+1];
        currentLevel++;
        
        return currentLevel;
    } //end of rewardLevel
    
}