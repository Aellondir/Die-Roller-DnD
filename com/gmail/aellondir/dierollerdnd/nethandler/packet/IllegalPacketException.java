package com.gmail.aellondir.dierollerdnd.nethandler.packet;

/**
 *May or may not be used, just here in case.
 *
 * @author jameshull
 * @serial JPGH.0001 throwable class 1
 * @version 0.01;
 */
public class IllegalPacketException extends IllegalArgumentException {

    public IllegalPacketException() {
    }

    public IllegalPacketException(String s) {
        super(s);
    }

    public IllegalPacketException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPacketException(Throwable cause) {
        super(cause);
    }
}
