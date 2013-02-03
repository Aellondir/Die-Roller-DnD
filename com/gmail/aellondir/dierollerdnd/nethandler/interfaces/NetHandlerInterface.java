package com.gmail.aellondir.dierollerdnd.nethandler.interfaces;

import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.net.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 extended interface 1
 * @version 0.01
 */
public interface NetHandlerInterface extends GeneralNetInterface {

    public void updateJCBPlayers();

    public void connectionAccepted(Socket socket, HandShakePacket packet);

    public String getPassWord();

    public boolean isServer();

    public void shutDown();
}
