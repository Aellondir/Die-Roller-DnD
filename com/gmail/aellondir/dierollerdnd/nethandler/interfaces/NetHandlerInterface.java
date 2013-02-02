package com.gmail.aellondir.dierollerdnd.nethandler.interfaces;

/**
 *
 * @author jameshull
 * @serial JPGH.0001 extended interface 1
 * @version 0.01
 */
public interface NetHandlerInterface extends GeneralNetInterface {

    public void updateJCBPlayers();

    public void connectionAccepted();

    public String getPassWord();

    public boolean isServer();

    public void shutDown();
}
