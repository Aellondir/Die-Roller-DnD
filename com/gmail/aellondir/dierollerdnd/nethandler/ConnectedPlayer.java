package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 3
 * @version 0.01
 */
public class ConnectedPlayer extends Thread {
    private Socket pSocket;
    private String unFull;

    private DataInputStream pDIS;
    private DataOutputStream pDOS;

    private PacketHandler playerPH;

    private char simTUn;

    private boolean connected,
            isUnTrunc,
            isUnSim;

    public ConnectedPlayer(Socket socket, boolean isUnTrunc, String unFull) throws IOException {
        this(socket, isUnTrunc, unFull, '§', false);
    }

    public ConnectedPlayer(Socket pSocket, boolean isUNTrunc, String unFull, char simTUn, boolean isUnSim) throws IOException {
        this.pSocket = pSocket;
        this.isUnTrunc = isUNTrunc;
        this.unFull = unFull;
        this.isUnSim = isUnSim;

        pDIS = new DataInputStream(pSocket.getInputStream());
        pDOS = new DataOutputStream(pSocket.getOutputStream());

        connected = true;
        this.setDaemon(true);
    }

    public String getUN(boolean truncated) {
        if (isUnTrunc  && truncated) {
            return this.getTruncUN();
        } else if (isUnSim) {
            return simTUn + unFull;
        } else {
            return unFull;
        }
    }

    public void sendConnectionAcceptedPacket() {
        ConnectionAcceptedPacket cAP;

        cAP = ConnectionAcceptedPacket.packetFactory(playerPH.getThenIncSentID());
    }

    public void sendNewPlayerPacket(String nUn) {
        //@todo send dat packet
    }

    private String getTruncUN() {
        if (unFull.getBytes(NetHandler.getCharset()).length > 16) {
            StringBuilder truncatedUn = new StringBuilder(16);

            if (simTUn != '§') {
                truncatedUn.append(simTUn);
            }

            for (int i = 0; i < 16; i++) {
                truncatedUn.append((char) unFull.getBytes(NetHandler.getCharset())[i]);
            }

            return truncatedUn.toString();
        } else {
            return unFull;
        }
    }

    public Socket getPSocket() {
        return pSocket;
    }

    public DataInputStream getPDIS() {
        return pDIS;
    }

    public DataOutputStream getPDOS() {
        return pDOS;
    }

    @Override
    public void run() {
        while (connected) {

        }
    }
}
