package the.game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
import java.math.BigDecimal;

/**
 *
 * @author Mark Aronin
 * @author Alex Craig
 *
 * To make a unique item, first find a sprite in Unused_Sprites and copy it to Used_Sprites, then follow the comments with this symbol: U.Q
 */

public class TheGame {

    private JFrame frame;
    private JLabel lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7, lbl8;
    private JComboBox lvlList;
    private Box statBox;
    private JPanel p1;
    private JPanel p2;
    private JButton btnSubmit;
    private String[] lvls;
    private String[] properties;
    private String[] resists;
    private String[] armorType;
    private int[][][] baseStats;
    private double[] multiplier;
    private int[][][] uniques;
    private double[][] characterStatMultipliers;
    //U.Q Declare your unique here, as a 2d int array
    private int[][] andeicslostmedallion;

    public TheGame() {
        lvls = new String[]{"1-9", "10-19", "20-29", "30-39", "40-49", "50-59", "60-69", "70-79", "80-89", "90-99", "100"};
        properties = new String[]{"Strength", "Intelligence", "Vitality", "Dexterity", "Wisdom", "Resist", "Resist All", "All Stats", "Crit Chance", "Damage Rating", "Plus Max Damage", "Plus Min Damage", "Mana Per Turn", "Life Per Turn", "Dodge", "Absorb"};
        resists = new String[]{"Resist Crush", "Resist Stab", "Resist Slash", "Resist Pierce", "Resist Magic"};
        armorType = new String[]{"gloves", "boots", "chest", "helm", "ring", "earings", "neck", "shield", "book", "pants"};
        multiplier = new double[]{2, 2, 3, 2.3, 1.2, 1.2, 1.4, 1.8, 1.8, 2.6};
        characterStatMultipliers = new double[][]{/*warrior*/{/*str*/2.05, /*int*/1, /*vit*/2.3, /*dex*/1.28, /*wis*/1.4}, /*ranger*/{/*str*/1.5, /*int*/1, /*vit*/1.39, /*dex*/2.23, /*wis*/1.44}, /*mage*/{/*str*/1, /*int*/2.45, /*vit*/1.5, /*dex*/0.8, /*wis*/1.9} };
        baseStats = new int[][][]{ /*
             * str
             */{{1, 5}, {3, 9}, {6, 19}, {12, 30}, {21, 44}, {33, 59}, {49, 76}, {61, 93}, {77, 112}, {95, 132}, {113, 152}},/*
             * int
             */ {{1, 5}, {3, 9}, {6, 19}, {12, 30}, {21, 44}, {33, 59}, {49, 76}, {61, 93}, {77, 112}, {95, 132}, {113, 152}},/*
             * vit
             */ {{1, 5}, {3, 9}, {6, 19}, {12, 30}, {21, 44}, {33, 59}, {49, 76}, {61, 93}, {77, 112}, {95, 132}, {113, 152}},/*
             * dex
             */ {{1, 5}, {3, 9}, {6, 19}, {12, 30}, {21, 44}, {33, 59}, {49, 76}, {61, 93}, {77, 112}, {95, 132}, {113, 152}},/*
             * wis
             */ {{1, 5}, {3, 9}, {6, 19}, {12, 30}, {21, 44}, {33, 59}, {49, 76}, {61, 93}, {77, 112}, {95, 132}, {113, 152}},/*
             * resists
             */ {{1, 4}, {2, 6}, {3, 10}, {5, 15}, {8, 21}, {12, 27}, {16, 34}, {20, 41}, {24, 49}, {29, 56}, {34, 63}}, /*
             * resist all
             */ {{1, 1}, {1, 2}, {1, 3}, {2, 5}, {3, 7}, {4, 9}, {5, 11}, {7, 14}, {8, 16}, {10, 19}, {11, 21}}, /*
             * all stats
             */ {{1, 1}, {1, 2}, {1, 4}, {2, 6}, {4, 9}, {7, 12}, {9, 15}, {12, 19}, {15, 22}, {19, 26}, {23, 30}}, /*
             * crit
             */ {{1, 2}, {1, 4}, {3, 6}, {5, 8}, {7, 10}, {9, 12}, {11, 14}, {13, 16}, {15, 18}, {17, 20}, {19, 22}}, /*
             * damage rating
             */ {{1, 4}, {2, 7}, {5, 10}, {8, 13}, {11, 16}, {14, 19}, {17, 22}, {20, 25}, {23, 28}, {26, 31}, {29, 34}}, /*
             * min damage
             */ {{1, 2}, {1, 3}, {2, 7}, {5, 11}, {8, 16}, {12, 22}, {17, 28}, {22, 35}, {29, 42}, {35, 49}, {42, 56}}, /*
             * max damage
             */ {{1, 2}, {1, 3}, {2, 7}, {5, 11}, {8, 16}, {12, 22}, {17, 28}, {22, 35}, {29, 42}, {35, 49}, {42, 56}},/*
             * mana regen
             */ {{1, 2}, {1, 2}, {1, 3}, {2, 4}, {2, 4}, {2, 6}, {3, 7}, {4, 9}, {5, 11}, {6, 14}, {8, 17}}, /*
             * life regen
             */ {{1, 4}, {3, 7}, {4, 8}, {4, 10}, {5, 11}, {6, 14}, {7, 16}, {9, 20}, {11, 24}, {13, 28}, {15, 34}}, /*
             * dodge
             */ {{1, 2}, {2, 4}, {3, 6}, {4, 8}, {5, 10}, {6, 12}, {7, 14}, {8, 16}, {9, 18}, {10, 20}, {11, 22}}, /*
             * absorb
             */ {{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}}};

        //U.Q Add the initialization here with stats it will always roll
        andeicslostmedallion = new int[][]{ /*
             * dex
             */{193, 231}, /*
             * crit
             */ {30, 36}, /*
             * damage rating
             */ {47, 55}};

        uniques = new int[][][]{andeicslostmedallion}; //U.Q Add the crated array into this one

        MakeWindow();
    }

    public void MakeWindow() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        frame = new JFrame();
        frame.setForeground(Color.GRAY);
        lbl1 = new JLabel("");
        lbl2 = new JLabel("");
        lbl3 = new JLabel("");
        lbl4 = new JLabel("");
        lbl5 = new JLabel("");
        lbl6 = new JLabel("");
        lbl7 = new JLabel("");
        lbl8 = new JLabel("");
        lvlList = new JComboBox(lvls);
        btnSubmit = new JButton("Submit");
        statBox = Box.createVerticalBox();
        p1 = new JPanel(new BorderLayout());
        p2 = new JPanel();

        frame.setTitle("The Game");
        frame.setSize(200, 215);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation((dim.width - (frame.getSize().width)) / 2, (dim.height - (frame.getSize().height)) / 2);
        frame.setLayout(new BorderLayout());

        statBox.add(Box.createGlue());
        statBox.add(lbl1);
        statBox.add(lbl2);
        statBox.add(lbl3);
        statBox.add(lbl4);
        statBox.add(lbl5);
        statBox.add(lbl6);
        statBox.add(lbl7);
        statBox.add(lbl8);

        p1.add(statBox);

        p2.add(Box.createRigidArea(new Dimension(0, 5)));
        p2.add(lvlList);
        p2.add(btnSubmit);


        frame.add(p1, BorderLayout.NORTH);
        p1.setBackground(Color.DARK_GRAY);
        p2.setBackground(Color.DARK_GRAY);
        frame.add(p2, BorderLayout.PAGE_END);
        addListeners();
    }

    public void addListeners() {
        btnSubmit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                String[] item = GenerateItem();

            }
        });
    }

    public String[] GenerateItem() {

        int flag[] = new int[16];
        int randomInt;
        int count = 0;
        int slots = 0;
        ImageIcon type = new ImageIcon();
        String[] item;
        int[] stats;
        int armor;
        Random randomGenerator = new Random();

        //Stupid way of declaring all 0 for array
        for (int i = 0; i < flag.length; i++) {
            flag[i] = 0;
        }

        randomInt = randomGenerator.nextInt(400);

        //Unique Items
        if (randomInt == 0 && lvlList.getSelectedIndex() == 10) {
            item = new String[6];
            stats = new int[6];
            slots = 6;
            double mult = 1;

            armor = randomGenerator.nextInt(uniques.length);

            //U.Q Copy this section of code up until the end comment
            if (uniques[armor] == andeicslostmedallion) { //U.Q change to else if statment, and name to new uniques name
                type = new ImageIcon(getClass().getResource("/Used_Sprites/andeicslostmedallion.png")); //U.Q change image path to new unique's image path 
                lbl2.setText("Andeic\'s Lost Medallion"); //U.Q change to new uniques name (\ allows use of special characters)
                mult = multiplier[6]; //U.Q change array index to match the multiplier of the armor type that the uniqe is

                for (count = 0; count < 3; count++) { //U.Q change loop condition to number of predetermined stats uniqe has
                    stats[count] = randomGenerator.nextInt(andeicslostmedallion[count][1] - andeicslostmedallion[count][0] + 1) + andeicslostmedallion[count][0]; //U.Q change array name to new uniques array name

                    if (count == 0) {
                        item[count] = properties[3]; //U.Q change properties array index to match the stat of the uniqe (eg dex is at index 3)
                        flag[3] = 1; //U.Q change flag array index to match the stat of the uniqe (eg dex is at index 3)                 
                    } else if (count == 1) {
                        item[count] = properties[8]; //U.Q change properties array index to match the stat of the uniqe (eg dex is at index 3)
                        flag[8] = 1; //U.Q change flag array index to match the stat of the uniqe (eg dex is at index 3)
                    } else {
                        item[count] = properties[9]; //U.Q change properties array index to match the stat of the uniqe (eg dex is at index 3)
                        flag[9] = 1; //U.Q change flag array index to match the stat of the uniqe (eg dex is at index 3)
                    }
                }
            }
            //U.Q end; paste copied code below

            while (count != slots) {
                randomInt = randomGenerator.nextInt(15);

                if (flag[randomInt] == 0) {
                    if (randomInt == 5) {
                        flag[randomInt] = 1;
                        BigDecimal tempStat = new BigDecimal((randomGenerator.nextInt(baseStats[randomInt][10][1] - baseStats[randomInt][10][0] + 1) + baseStats[randomInt][10][0]) * mult);
                        tempStat = tempStat.setScale(0, BigDecimal.ROUND_HALF_UP);
                        stats[count] = tempStat.intValue();
                        randomInt = randomGenerator.nextInt(5);
                        item[count] = resists[randomInt];
                        count++;
                    } else {
                        item[count] = properties[randomInt];
                        flag[randomInt] = 1;
                        BigDecimal tempStat = new BigDecimal((randomGenerator.nextInt(baseStats[randomInt][10][1] - baseStats[randomInt][10][0] + 1) + baseStats[randomInt][10][0]) * mult);
                        tempStat = tempStat.setScale(0, BigDecimal.ROUND_HALF_UP);
                        stats[count] = tempStat.intValue();
                        count++;
                    }
                }
            }
        } //Non Unique Items
        else {
            randomInt = randomGenerator.nextInt(2);

            if (randomInt == 0) {
                item = new String[6];
                stats = new int[6];
                slots = 3;
            } else {
                item = new String[6];
                stats = new int[6];
                slots = 6;
            }

            armor = randomInt = randomGenerator.nextInt(10);
            type = new ImageIcon(getClass().getResource("/Used_Sprites/" + slots + armorType[randomInt] + ".png"));
            lbl2.setText(slots + " property " + armorType[randomInt]);

            while (count != slots) {
                randomInt = randomGenerator.nextInt(15);

                if (flag[randomInt] == 0) {

                    if (randomInt == 5) {
                        flag[randomInt] = 1;
                        BigDecimal tempStat = new BigDecimal((randomGenerator.nextInt(baseStats[randomInt][lvlList.getSelectedIndex()][1] - baseStats[randomInt][lvlList.getSelectedIndex()][0] + 1) + baseStats[randomInt][lvlList.getSelectedIndex()][0]) * multiplier[armor]);
                        tempStat = tempStat.setScale(0, BigDecimal.ROUND_HALF_UP);
                        stats[count] = tempStat.intValue();
                        randomInt = randomGenerator.nextInt(5);
                        item[count] = resists[randomInt];
                        count++;
                    } else {
                        item[count] = properties[randomInt];
                        flag[randomInt] = 1;
                        BigDecimal tempStat = new BigDecimal((randomGenerator.nextInt(baseStats[randomInt][lvlList.getSelectedIndex()][1] - baseStats[randomInt][lvlList.getSelectedIndex()][0] + 1) + baseStats[randomInt][lvlList.getSelectedIndex()][0]) * multiplier[armor]);
                        tempStat = tempStat.setScale(0, BigDecimal.ROUND_HALF_UP);
                        stats[count] = tempStat.intValue();
                        count++;
                    }
                }
            }
        }

        lbl1.setIcon(type);
        lbl3.setText("+" + stats[0] + " " + item[0]);
        lbl4.setText("+" + stats[1] + " " + item[1]);
        lbl5.setText("+" + stats[2] + " " + item[2]);
        lbl2.setForeground(Color.GRAY);
        lbl3.setForeground(Color.GRAY);
        lbl4.setForeground(Color.GRAY);
        lbl5.setForeground(Color.GRAY);
        lbl6.setForeground(Color.GRAY);
        lbl7.setForeground(Color.GRAY);
        lbl8.setForeground(Color.GRAY);
        if (item[3] != null) 
            lbl6.setText("+" + stats[3] + " " + item[3]);
         else 
            lbl6.setText("");
        
        if (item[4] != null) 
            lbl7.setText("+" + stats[4] + " " + item[4]);
         else 
            lbl7.setText("");
        
        if (item[5] != null) 
            lbl8.setText("+" + stats[5] + " " + item[5]);
         else 
            lbl8.setText("");
        
        return item;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TheGame a = new TheGame();
    }
}