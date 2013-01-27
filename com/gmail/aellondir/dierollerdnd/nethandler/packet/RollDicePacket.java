package com.gmail.aellondir.dierollerdnd.nethandler.packet;

import com.gmail.aellondir.dierollerdnd.enumerations.DiceDefinitions;
import java.io.*;

/**
 *
 * @author James Hull
 * @serial JPGH.0001 class 6 Subclass 7
 * @version 0.01
 */
public class RollDicePacket extends Packet {

    private short numTRoll = 0;
    private DiceDefinitions dTR = DiceDefinitions.D100;

    private RollDicePacket(long sentID, short numTRoll, DiceDefinitions dTR) {
        super((byte) 65, sentID);

        this.numTRoll = numTRoll;
        this.dTR = dTR;
    }

    public static RollDicePacket packetFactory(long sentID, short numTRoll, DiceDefinitions dTR) {
        return new RollDicePacket(sentID, numTRoll, dTR);
    }

    public short getNumTRoll() {
        return numTRoll;
    }

    public DiceDefinitions getdTR() {
        return dTR;
    }

    public static RollDicePacket processReadPacket(DataInputStream dIS) throws IOException {
        long sentIDR = dIS.readLong();
        short numTRollR = dIS.readShort();
        DiceDefinitions dTR = DiceDefinitions.values()[dIS.readInt()];

        return RollDicePacket.packetFactory(sentIDR, numTRollR, dTR);
    }

    @Override
    public void processSendPacket(DataOutputStream dOS) throws IOException {
        dOS.writeByte(packetType);
        dOS.writeLong(sentID);

        dOS.writeShort(numTRoll);
        dOS.writeInt(dTR.ordinal());

        dOS.flush();
    }
}
