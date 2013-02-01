package com.gmail.aellondir.dierollerdnd.nethandler;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 abs class 2
 * @version 0.01
 */
public abstract class NetHandlerAbst extends Thread implements NetHandlerInterface {

    @Override
    public abstract void updateJCBPlayers();

    @Override
    public abstract void connectionAccepted();

    @Override
    public abstract void shutDown();

    @Override
    public String getUN(boolean tOrFull) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isUNTrunc() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NetHandlerAbst() {
    }
}
