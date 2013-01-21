package com.gmail.aellondir.dierollerdnd.nethandler;

import static com.gmail.aellondir.dierollerdnd.gui.RollerFrame.getFrame;
import com.gmail.aellondir.dierollerdnd.nethandler.packet.*;
import java.util.PriorityQueue;

/**
 *
 * @author jameshull
 */
public class PacketHandler extends Thread {

        private PriorityQueue<Packet> prQueue;
        private boolean run = true;

        public PacketHandler() {
            prQueue = new PriorityQueue<>();
        }

        public void shutDown() {
            run = false;
        }

        public boolean addToQueue(Packet packet) {
            return prQueue.add(packet);
        }

        @Override
        public void run() {
            do {
                while (!prQueue.isEmpty()) {

                    //@todo packet handleing logic.
                }

                try {
                    Thread.sleep(250L);
                } catch (InterruptedException e) {
                    getFrame().errorScreen(e);
                }
            } while (run);
        }
    }
