package com.gmail.aellondir.dierollerdnd.nethandler;

import java.net.*;

/**
 *Abstract class defining the basic operation of the NetHandler subclasses.
 *
 * @author James Hull
 * @serial JPGH.0001 class 3 implementation 2
 * @version 0.01
 */
public class NetHandlerSlave extends NetHandler {
    private long sentID = 0L;
    private long recievedID = 0L;
    private ServerSocket socket;

    public NetHandlerSlave(String username, String passWord, String inetAddress) {
        super(username, passWord);

    }

    @Override
    public boolean isMaster() {
        return false;
    }
}
