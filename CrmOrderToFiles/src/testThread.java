import utils.initPool;

public class testThread implements Runnable {

    @Override
    public void run() {
        while (true) {
            String channel_nbr = "18084941";
            String a  = initPool.returnChannelId(channel_nbr);
        }

    }
}