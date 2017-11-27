package src;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MessageRepository {

    public static final MessageRepository instance = new MessageRepository();

    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>(500);

    public static MessageRepository getInstance() {
        return instance;
    }

    private MessageRepository() {

    }

    public void offerBatch(List<String> msgs) {
        //System.out.println(messageQueue.size());
        msgs.forEach(messageQueue::offer);
    }

    public String take() throws InterruptedException {
        return messageQueue.take();
    }

}
