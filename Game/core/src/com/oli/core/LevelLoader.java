package com.oli.core;

import com.oli.game.constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelLoader {

    public int[][] loadLevelFromPNG(String levelName) {

        BufferedImage levelPNG = null;
        int[][] levelData;

        try {

            levelPNG = ImageIO.read(new File("levels/" + levelName  + ".png"));

        } catch (IOException e) {

            e.printStackTrace();

        }

        levelData = new int[levelPNG.getWidth()][levelPNG.getHeight()];

        for (int y = 0; y < levelPNG.getHeight(); y++) {

            for (int x = 0; x < levelPNG.getWidth(); x++) {

                int colourRGB = levelPNG.getRGB(x, y);
                int  r   = (colourRGB & 0x00ff0000) >> 16;
                int  g = (colourRGB & 0x0000ff00) >> 8;
                int  b  =  colourRGB & 0x000000ff;
                String colourHex = String.format("#%02x%02x%02x", r, g, b);

                switch (colourHex) {

                    case constants.black:
                        levelData[x][y] = constants.empty;
                        break;

                    case constants.white:
                        levelData[x][y] = constants.wall;
                        break;

                    case constants.blue:
                        levelData[x][y] = constants.player;
                        break;

                    case constants.red:
                        levelData[x][y] = constants.lava;
                        break;

                    case constants.green:
                        levelData[x][y] = constants.finish;
                        break;
                }

            }

        }

        return levelData;

    }

}
