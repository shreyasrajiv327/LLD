import reliableMessageQueue.models.*;
import reliableMessageQueue.repository.*;

import java.util.*;
import java.time.*;

public class MessageService{
    private final MessageRepository messageRepository;
    private final PriorityQueue<Message> messageQueue;
    private final Queue<Message> deadLetterQueue;

    private int messageId;
    private static final int MAX_RETRIES = 3;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
        messageQueue = new PriorityQueue<>(
    (m1, m2) -> {
        if (m1.getPriority() != m2.getPriority()) {
            return m1.getPriority().ordinal() - m2.getPriority().ordinal();
        }
        return Integer.compare(m1.getMessageId(), m2.getMessageId());
    }
);
        this.messageId = 1;
        this.deadLetterQueue = new LinkedList<>();
    }

    public void publishMessage(Message message){
           message.setStatus(Message.MessageStatus.READY);
           messageRepository.addMessage(message);
           messageQueue.offer(message);
    }

    public Message pollMessage(){
            if (messageQueue.isEmpty()) {
              return null;
            }

        Message message = messageQueue.poll();
        message.setStatus(Message.MessageStatus.IN_PROGRESS);
        return message;
    }

    public void ack(int messageId){
        Message message = messageRepository.getMessage(messageId);

       if (message == null) {
           throw new IllegalArgumentException("Invalid Message ID");
        }
       message.setStatus(Message.MessageStatus.COMPLETED);
       messageRepository.remove(messageId);
    }
    
    public void nack(int messageId) {

    Message message = messageRepository.getMessage(messageId);

    if (message == null) {
        throw new IllegalArgumentException("Invalid Message ID");
    }

    message.incrementRetryCount();

    if (message.getRetryCount() < MAX_RETRIES) {

        message.setStatus(Message.MessageStatus.READY);

        messageQueue.offer(message);

    } else {

        message.setStatus(Message.MessageStatus.DEAD_LETTER);

        deadLetterQueue.offer(message);
    }
}

 public List<Message> getDeadLetters() {
    return new ArrayList<>(deadLetterQueue);
}




}