package com.gmail.aellondir.dierollerdnd.nethandler;

/**
 *Abstract class defining the basic operation of the NetHandler subclasses.
 *
 * @author James Hull
 * @serial JPGH.0001 class 3 implementation 2
 * @version 0.01
 */
public class NetHandlerSlave extends NetHandler {

    public NetHandlerSlave(String username, String passWord) {
        super(username, passWord);
    }

    @Override
    public boolean isMaster() {
        return false;
    }
}
