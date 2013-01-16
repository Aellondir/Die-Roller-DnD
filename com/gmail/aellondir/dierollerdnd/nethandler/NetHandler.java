package com.gmail.aellondir.dierollerdnd.nethandler;

/**
 *Abstract class defining the basic operation of the NetHandler subclasses.
 *
 * @author James Hull
 * @serial JPGH.0001 abst class 3
 * @version 0.01
 */
public abstract class NetHandler {
    protected String username;
    protected String passWord;

    public NetHandler(String username, String passWord) {
        this.username = username;
        this.passWord = passWord;
    }


    public String getPW() {
        return passWord;
    }

    public String getUN() {
        return username;
    }

    public abstract boolean isMaster();
}