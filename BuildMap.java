import java.util.ArrayList;

/**
 * @author Arin Wani
 * @version 6/9/2024
 */
public class BuildMap {
    /**
     * List of blocks in the map.
     */
    private ArrayList<Block> blocks;
    /**
     * List of pigs in the map.
     */
    private ArrayList<Pig> pigs;
    /**
     * List of birds available for the level.
     */
    private ArrayList<Integer> birds;

    /**
     * Constructs a BuildMap for the specified level.
     * Initializes blocks, pigs, and birds based on the level.
     * Stops the background music when a new map is built.
     * 
     * @param level the level for which the map is to be built.
     */
    public BuildMap(int level) {
        blocks = new ArrayList<Block>();
        pigs = new ArrayList<Pig>();
        birds = new ArrayList<Integer>();
        BackgroundMusic.stopMusic();

        if (level == 1) {
            birds.add(2);

            pigs.add(new Pig(950, 360));

            blocks.add(new IceBlock(910, 500, 90, 1));
            blocks.add(new IceBlock(990, 500, 90, 1));
            blocks.add(new IceBlock(950, 410, 0, 1));

            blocks.add(new IceBlock(910, 360, 90, 1));
            blocks.add(new IceBlock(990, 360, 90, 1));
            blocks.add(new IceBlock(950, 300, 0, 1));

        }

        if (level == 2) {
            birds.add(1);
            birds.add(2);
            birds.add(2);

            pigs.add(new Pig(860, 510));

            pigs.add(new Pig(1040, 370));

            blocks.add(new StoneBlock(820, 500, 90, 1));
            blocks.add(new StoneBlock(900, 500, 90, 1));
            blocks.add(new StoneBlock(860, 410, 0, 1));

            blocks.add(new IceBlock(820, 360, 90, 1));
            blocks.add(new IceBlock(900, 360, 90, 1));
            blocks.add(new IceBlock(860, 300, 0, 1));

            blocks.add(new StoneBlock(1000, 500, 90, 1));
            blocks.add(new StoneBlock(1080, 500, 90, 1));
            blocks.add(new StoneBlock(1040, 410, 0, 1));

            blocks.add(new IceBlock(1000, 360, 90, 1));
            blocks.add(new IceBlock(1080, 360, 90, 1));
            blocks.add(new IceBlock(1040, 300, 0, 1));
        }

        if (level == 3) {
            birds.add(3);

            pigs.add(new Pig(740, 510));

            pigs.add(new Pig(920, 380));

            pigs.add(new Pig(1100, 510));

            blocks.add(new StoneBlock(700, 500, 90, 1));
            blocks.add(new StoneBlock(780, 500, 90, 1));
            blocks.add(new StoneBlock(740, 410, 0, 1));

            blocks.add(new WoodBlock(700, 360, 90, 1));
            blocks.add(new WoodBlock(780, 360, 90, 1));
            blocks.add(new WoodBlock(740, 300, 0, 1));

            blocks.add(new StoneBlock(880, 500, 90, 1));
            blocks.add(new StoneBlock(960, 500, 90, 1));
            blocks.add(new StoneBlock(920, 410, 0, 1));

            blocks.add(new WoodBlock(880, 360, 90, 1));
            blocks.add(new WoodBlock(960, 360, 90, 1));
            blocks.add(new WoodBlock(920, 300, 0, 1));

            blocks.add(new StoneBlock(1060, 500, 90, 1));
            blocks.add(new StoneBlock(1140, 500, 90, 1));
            blocks.add(new StoneBlock(1100, 410, 0, 1));

            blocks.add(new WoodBlock(1060, 360, 90, 1));
            blocks.add(new WoodBlock(1140, 360, 90, 1));
            blocks.add(new WoodBlock(1100, 300, 0, 1));

        }

        if (level == 4) {
            birds.add(1);
            birds.add(4);

            pigs.add(new Pig(1050, 500));

            pigs.add(new Pig(1000, 360));
            pigs.add(new Pig(1100, 360));

            pigs.add(new Pig(1052, 240));

            blocks.add(new StoneBlock(990, 500, 90, 1));
            blocks.add(new StoneBlock(950, 400, 0, 1));
            blocks.add(new StoneBlock(1010, 500, 90, 1));
            blocks.add(new StoneBlock(1090, 500, 90, 1));
            blocks.add(new StoneBlock(1050, 410, 0, 1));

            blocks.add(new StoneBlock(910, 500, 90, 1));
            blocks.add(new StoneBlock(1110, 500, 90, 1));
            blocks.add(new StoneBlock(1190, 500, 90, 1));
            blocks.add(new StoneBlock(1150, 400, 0, 1));
            blocks.add(new StoneBlock(1000, 395, 0, 1));
            blocks.add(new StoneBlock(1100, 395, 0, 1));

            blocks.add(new WoodBlock(1140, 330, 90, 1));
            blocks.add(new WoodBlock(965, 330, 90, 1));
            blocks.add(new StoneBlock(1050, 330, 90, 1));
            blocks.add(new WoodBlock(1095, 260, 0, 1));
            blocks.add(new WoodBlock(1007, 260, 0, 1));

            blocks.add(new IceBlock(1010, 220, 90, 1));
            blocks.add(new IceBlock(1090, 220, 90, 1));
            blocks.add(new IceBlock(1050, 170, 0, 1));

        }

        if (level == 5) {
            birds.add(1);
            birds.add(5);
            birds.add(5);

            pigs.add(new Pig(940, 500));
            pigs.add(new Pig(880, 500));

            pigs.add(new Pig(950, 350));
            pigs.add(new Pig(870, 350));

            blocks.add(new StoneBlock(910, 500, 90, 3));
            blocks.add(new StoneBlock(910, 435, 90, 3));
            blocks.add(new StoneBlock(910, 375, 90, 3));
            blocks.add(new StoneBlock(910, 435, 90, 3));
            blocks.add(new StoneBlock(910, 375, 90, 3));

            blocks.add(new StoneBlock(965, 500, 70, 1));
            blocks.add(new StoneBlock(855, 500, 110, 1));

            blocks.add(new StoneBlock(780, 500, 90, 1));
            blocks.add(new StoneBlock(820, 400, 0, 1));

            blocks.add(new StoneBlock(1040, 500, 90, 1));
            blocks.add(new StoneBlock(1000, 400, 0, 1));

            blocks.add(new StoneBlock(990, 380, 50, 1));
            blocks.add(new StoneBlock(830, 380, 130, 1));

        }

        if (level == 6) {
            birds.add(5);
            birds.add(2);
            birds.add(2);
            birds.add(4);

            pigs.add(new Pig(50, 500));
            pigs.add(new Pig(890, 500));

            blocks.add(new StoneBlock(850, 500, 90, 1));
            blocks.add(new StoneBlock(930, 500, 90, 1));
            blocks.add(new StoneBlock(890, 410, 0, 1));

            blocks.add(new StoneBlock(815, 500, 90, 3));
            blocks.add(new StoneBlock(815, 450, 90, 3));
            blocks.add(new StoneBlock(815, 400, 90, 3));

            blocks.add(new StoneBlock(870, 380, 90, 3));
            blocks.add(new StoneBlock(900, 380, 90, 3));

            blocks.add(new StoneBlock(960, 500, 90, 3));
            blocks.add(new StoneBlock(960, 450, 90, 3));
            blocks.add(new StoneBlock(960, 400, 90, 3));

            blocks.add(new StoneBlock(765, 500, 90, 3));
            blocks.add(new StoneBlock(765, 450, 90, 3));
            blocks.add(new StoneBlock(765, 400, 90, 3));

            blocks.add(new StoneBlock(1010, 500, 90, 3));
            blocks.add(new StoneBlock(1010, 450, 90, 3));
            blocks.add(new StoneBlock(1010, 400, 90, 3));

        }

        if (level == 7) {
            birds.add(3);
            birds.add(2);
            birds.add(4);

            pigs.add(new Pig(1020, 460));
            pigs.add(new Pig(840, 500));
            pigs.add(new Pig(970, 240));

            blocks.add(new StoneBlock(790, 500, 90, 2));
            blocks.add(new StoneBlock(790, 450, 90, 2));
            blocks.add(new StoneBlock(860, 500, 90, 1));
            blocks.add(new StoneBlock(820, 410, 0, 1));
            blocks.add(new StoneBlock(810, 435, 0, 1));

            blocks.add(new StoneBlock(880, 500, 90, 1));
            blocks.add(new StoneBlock(960, 500, 90, 1));
            blocks.add(new StoneBlock(920, 410, 0, 1));
            blocks.add(new StoneBlock(920, 500, 100, 1));

            blocks.add(new StoneBlock(980, 500, 90, 1));
            blocks.add(new StoneBlock(1060, 500, 90, 1));
            blocks.add(new StoneBlock(1020, 410, 0, 1));
            blocks.add(new StoneBlock(1020, 500, 0, 3));

            blocks.add(new StoneBlock(1080, 500, 90, 1));
            blocks.add(new StoneBlock(1160, 500, 90, 1));
            blocks.add(new StoneBlock(1120, 410, 0, 1));
            blocks.add(new StoneBlock(1120, 500, 80, 1));

            blocks.add(new WoodBlock(870, 370, 90, 3));
            blocks.add(new WoodBlock(870, 330, 90, 3));

            blocks.add(new WoodBlock(1070, 370, 90, 3));
            blocks.add(new WoodBlock(1070, 330, 90, 3));

            blocks.add(new WoodBlock(970, 370, 90, 3));
            blocks.add(new WoodBlock(970, 320, 90, 3));
            blocks.add(new WoodBlock(970, 270, 90, 3));

            blocks.add(new WoodBlock(870, 230, 120, 1));
            blocks.add(new WoodBlock(1070, 230, 60, 1));

            blocks.add(new IceBlock(720, 500, 90, 3));
            blocks.add(new IceBlock(720, 440, 90, 3));
            blocks.add(new IceBlock(720, 380, 90, 3));

            blocks.add(new IceBlock(1220, 500, 90, 3));
            blocks.add(new IceBlock(1220, 440, 90, 3));
            blocks.add(new IceBlock(1220, 380, 90, 3));
        }

        if (level == 8) {
            birds.add(2);
            birds.add(1);
            birds.add(5);
            birds.add(4);

            pigs.add(new Pig(815, 500));
            pigs.add(new Pig(640, 500));
            pigs.add(new Pig(990, 500));

            blocks.add(new StoneBlock(780, 500, 90, 1));
            blocks.add(new StoneBlock(860, 500, 90, 1));
            blocks.add(new StoneBlock(820, 410, 0, 1));

            blocks.add(new WoodBlock(740, 500, 0, 3));
            blocks.add(new WoodBlock(740, 470, 0, 3));

            blocks.add(new WoodBlock(890, 500, 0, 3));
            blocks.add(new WoodBlock(890, 470, 0, 3));

            blocks.add(new IceBlock(805, 400, 90, 3));
            blocks.add(new IceBlock(830, 400, 90, 3));

            blocks.add(new StoneBlock(690, 500, 90, 2));
            blocks.add(new StoneBlock(690, 470, 90, 2));
            blocks.add(new StoneBlock(690, 440, 90, 2));

            blocks.add(new StoneBlock(590, 500, 90, 2));
            blocks.add(new StoneBlock(590, 470, 90, 2));
            blocks.add(new StoneBlock(590, 440, 90, 2));

            blocks.add(new IceBlock(640, 400, 0, 1));
            blocks.add(new IceBlock(640, 390, 0, 1));

            blocks.add(new StoneBlock(940, 500, 90, 2));
            blocks.add(new StoneBlock(940, 470, 90, 2));
            blocks.add(new StoneBlock(940, 440, 90, 2));

            blocks.add(new StoneBlock(1040, 500, 90, 2));
            blocks.add(new StoneBlock(1040, 470, 90, 2));
            blocks.add(new StoneBlock(1040, 440, 90, 2));

            blocks.add(new IceBlock(990, 400, 0, 1));
            blocks.add(new IceBlock(990, 390, 0, 1));
        }

        if (level == 9) {
            birds.add(2);
            birds.add(1);
            birds.add(3);

            pigs.add(new Pig(725, 320));
            pigs.add(new Pig(830, 500));

            pigs.add(new Pig(830, 400));
            pigs.add(new Pig(940, 300));

            blocks.add(new StoneBlock(830, 430, 0, 1));
            blocks.add(new StoneBlock(770, 500, 0, 3));
            blocks.add(new StoneBlock(890, 500, 0, 3));
            blocks.add(new StoneBlock(780, 450, 0, 2));
            blocks.add(new StoneBlock(880, 450, 0, 2));

            blocks.add(new StoneBlock(780, 390, 90, 1));
            blocks.add(new WoodBlock(720, 500, 110, 1));
            blocks.add(new WoodBlock(770, 390, 10, 1));

            blocks.add(new StoneBlock(680, 500, 90, 3));
            blocks.add(new StoneBlock(680, 440, 90, 3));
            blocks.add(new StoneBlock(680, 390, 90, 3));

            blocks.add(new StoneBlock(740, 320, 0, 1));

            blocks.add(new StoneBlock(890, 390, 90, 1));
            blocks.add(new WoodBlock(950, 500, 70, 1));
            blocks.add(new WoodBlock(900, 390, 170, 1));

            blocks.add(new StoneBlock(990, 500, 90, 3));
            blocks.add(new StoneBlock(990, 440, 90, 3));
            blocks.add(new StoneBlock(990, 380, 90, 3));

            blocks.add(new StoneBlock(935, 300, 0, 1));

            blocks.add(new StoneBlock(750, 300, 90, 1));
            blocks.add(new StoneBlock(718, 300, 90, 1));

            blocks.add(new StoneBlock(955, 290, 90, 1));
            blocks.add(new StoneBlock(925, 290, 95, 1));

        }

        if (level == 10) {
            birds.add(1);

            pigs.add(new Pig(1150, 500));

            blocks.add(new StoneBlock(680, 500, 90, 3));
            blocks.add(new StoneBlock(680, 440, 90, 3));
            blocks.add(new StoneBlock(680, 380, 90, 3));
            blocks.add(new StoneBlock(680, 320, 90, 3));
            blocks.add(new StoneBlock(680, 260, 90, 3));
            blocks.add(new StoneBlock(680, 200, 90, 3));
        }
    }

    /**
     * Returns the list of blocks in the map.
     *
     * @return the list of blocks
     */
    public ArrayList<Block> getBlockList() {
        return blocks;
    }

    /**
     * Returns the list of birds available for the level.
     *
     * @return the list of birds
     */
    public ArrayList<Integer> getBirdList() {
        return birds;
    }

    /**
     * Returns the list of pigs in the map.
     *
     * @return the list of pigs
     */
    public ArrayList<Pig> getPigList() {
        return pigs;
    }

    /**
     * Calculates and returns the maximum score possible for the level.
     * Each pig is worth 5000 points, and each block is worth 1000 points.
     *
     * @return the maximum score for the level
     */
    public int getMaxScore() {
        int maxScore = 0;

        for (int i = 0; i < pigs.size(); i++)
            maxScore += 5000;
        for (int i = 0; i < blocks.size(); i++)
            maxScore += 1000;

        return maxScore;
    }
}
