package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.HandshakePacket;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 4
 * @version 0.01
 */
public class AuthenticateConnection extends Thread {

    private Socket socket;
    private NetHandlerMaster nHM;

    public AuthenticateConnection(Socket socket, NetHandlerMaster nHM) throws IOException {
        this.socket = socket;
        this.nHM = nHM;
    }

    @Override
    public void run() {
        try (DataInputStream dIS = new DataInputStream(socket.getInputStream()); DataOutputStream dOS = new DataOutputStream(socket.getOutputStream())) {
            byte packetType = dIS.readByte();

            if (packetType != 1) {
                nHM.connectionDenied(socket);
            }

            HandshakePacket cP = HandshakePacket.processReadPacket(dIS);

            if (nHM.getPW().equals(cP.getpW())) {
                //Dialogue to ask the GM what he wants to do. Deny or Accept. if yes passes through to the connection accepted method
                //if no then a connection denied packet is sent to the offending player.

                nHM.connectionAccepted(socket, cP);
            } else {
                nHM.connectionDenied(socket);
            }
        } catch (final IOException e) {
            getFrame().errorScreen(e);
        }


    }
}
