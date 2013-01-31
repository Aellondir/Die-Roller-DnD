package com.gmail.aellondir.dierollerdnd.nethandler;

import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;

/**
 *
 * @author jameshull
 * @serial JPGH.0001 abst class 1
 * @version 0.01
 */
public abstract class NetHandlerAbs extends Thread {

    public abstract boolean isMaster();

    public abstract boolean sendAllPlayers(Packet packet);
}
