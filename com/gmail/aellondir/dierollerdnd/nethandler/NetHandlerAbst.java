package com.gmail.aellondir.dierollerdnd.nethandler;

import com.gmail.aellondir.dierollerdnd.nethandler.interfaces.NetHandlerInterface;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abs class 2
 * @version 0.01
 */
public abstract class NetHandlerAbst extends Thread implements NetHandlerInterface {

    private String unFull,
            passWord;

    public NetHandlerAbst(String un, String passWord) {
        super(netThreads, (un + ":DieRoller NetHandler Thread"));

        this.unFull = un;
        this.passWord = passWord;
    }

    @Override
    public String getPassWord() {
        return passWord;
    }

    @Override
    public String getUN(boolean tOrFull) {
        if (tOrFull) {
            return this.truncUN(unFull);
        } else {
            return unFull;
        }
    }

    @Override
    public final String truncUN(String un) {
        String truncationStr = un;

        if (truncationStr.length() > 16) {
            truncationStr = "";

            for (byte b : un.getBytes(cs)) {
                truncationStr += (char) b;
            }
        }

        return truncationStr;
    }
}
