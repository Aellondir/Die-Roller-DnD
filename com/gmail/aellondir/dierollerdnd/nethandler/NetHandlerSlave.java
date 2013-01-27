package com.gmail.aellondir.dierollerdnd.nethandler;

import java.net.*;

/**
 *Handles the slave side of the master/slave relationship for the die roller.
 *
 * @author James Hull
 * @serial JPGH.0001 class 3 implementation 2
 * @version 0.01
 */
public class NetHandlerSlave extends NetHandler {
    private long sentID = 0L;
    private long receivedID = 0L;
    private ServerSocket socket;

    public NetHandlerSlave(String username, String passWord, String inetAddress) {
        super(username, passWord);

    }

    @Override
    public boolean isMaster() {
        return false;
    }

    @Override
    public void shutDown() {
        //@todo shutdown methodology.
    }
}
