package com.gmail.aellondir.dierollerdnd.logic;

import java.util.Random;

/**
 *
 * Returns one random number for each run of the specific die roller.
 *
 * @author James Hull
 * @serial JPGH.0001 class 1
 * @version 0.1
 */
public class DieRoller {

    private static Random rand = new Random(System.nanoTime());

    private DieRoller() {
    }

    public static byte rollD2() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            switch (DieRoller.rollD6()) {
                case 1:
                case 2:
                case 3:
                    intArr[i] = 1;
                    break;
                case 4:
                case 5:
                case 6:
                    intArr[i] = 2;
                    break;
                default:
                    intArr[i] = rand.nextBoolean() ? 1 : 2;
            }
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD3() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            switch (DieRoller.rollD6()) {
                case 1:
                case 2:
                    intArr[i] = 1;
                    break;
                case 3:
                case 4:
                    intArr[i] = 2;
                    break;
                case 5:
                case 6:
                    intArr[i] = 3;
                    break;
                default:
                    intArr[i] = (rand.nextBoolean() ? 1 : 2) + rand.nextInt(1);
            }
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD4() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(4) + 1;
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD6() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(6) + 1;
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD8() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(8) + 1;
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD10() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(10) + 1;
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD12() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(12) + 1;
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD20() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(20) + 1;
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }

    public static byte rollD100() {
        int[] intArr = new int[rand.nextInt(10) + 2];

        for (int i = 0; i < intArr.length; i++) {
            intArr[i] = rand.nextInt(100) + 1;
        }

        return (byte) intArr[rand.nextInt(intArr.length)];
    }
}
