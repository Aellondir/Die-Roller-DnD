package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import static com.gmail.aellondir.dierollerdnd.nethandler.NetHandlerServer.getNHS;
import static com.gmail.aellondir.dierollerdnd.nethandler.interfaces.GeneralNetInterface.netThreads;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.io.*;
import java.net.Socket;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6
 * @version 0.01
 */
public class AuthenticateConnection extends Thread {

    private Socket socket;
    private HandShakePacket packet;

    public AuthenticateConnection(Socket socket, int i) {
        super(netThreads,"Connection Authentication Thread " + Integer.toString(i));

        packet = new HandShakePacket();

        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dIS = new DataInputStream(socket.getInputStream());

            packet.processReadPacket(dIS);

            boolean pTFail, pWFail;

            if ((pTFail = packet.getPacketType() == 1) & (pWFail = getNHS().getPassWord().equals(packet.getpW()))) {

                getNHS().connectionAccepted(socket, packet);
            } else if (pTFail && pWFail) {
                
            } else if (pTFail == false && pWFail == true) {

            } else if (pTFail == true && pWFail == false) {

            }
        } catch (IOException e) {
            getFrame().errorScreen(e, this);
        }
    }
}
