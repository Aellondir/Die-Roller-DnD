package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.nio.charset.Charset;

/**
 * Abstract class defining the basic operation of the NetHandler subclasses.
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 1
 * @version 0.01
 */
public abstract class NetHandler extends Thread {

    protected String username;
    protected String truncatedUsername = null;
    private static Charset cs = Charset.forName("UTF-8");
    protected String passWord;

    public NetHandler(String username, String passWord) {
        if (username.getBytes(cs).length > 16) {
            this.username = username;

            truncatedUsername = "";

            for (int i = 0; i < 16; i++) {
                truncatedUsername += (char) username.getBytes(cs)[i];
            }
        } else {
            this.username = username;
        }


        this.passWord = passWord;
    }

    public String getPW() {
        return passWord;
    }

    public String getUN() {
        return username;
    }

    public boolean isUNTrunc() {
        return truncatedUsername != null;
    }

    public static Charset getCharset() {
        return cs;
    }

    public abstract boolean isMaster();

}