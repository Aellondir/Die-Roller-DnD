package com.gmail.aellondir.dierollerdnd.nethandler.interfaces;

import java.nio.charset.Charset;

/**
 *
 * @author jameshull
 * @serial JPGH.0001 interface 1
 * @version 0.01
 */
public interface GeneralNetInterface {

    static final ThreadGroup tG1 = new ThreadGroup("main");
    static final ThreadGroup netThreads = new ThreadGroup(tG1, "Net Threads");
    static final ThreadGroup playerThreads = new ThreadGroup(netThreads, "Player Threads");
    public static final Charset cs = Charset.forName("UTF-8");

    public String getUN(boolean tOrFull);

    String truncUN(String un);
}
