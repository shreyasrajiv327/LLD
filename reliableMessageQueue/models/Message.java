package reliableMessageQueue.models;

import java.util.*;
import java.time.*;
public class Message{
    public enum Priority{
        HIGH,
        MEDIUM,
        LOW
    }

    public enum MessageStatus {
    READY,
    IN_PROGRESS,
    COMPLETED,
    DEAD_LETTER
}

    private int messageId;
    private String payload;
    private Priority priority;
    private LocalDateTime scheduledTime;
    private MessageStatus messageStatus;
    private int retryCount;

    public Message(int messageId,String payload, Priority priority, LocalDateTime scheduledTime){
        this.messageId = messageId;
        this.payload = payload;
        this.priority = priority;
        this.scheduledTime = scheduledTime;
        this.retryCount = 0;
    }

    public int getMessageId(){
        return messageId;
    }

    public String getPayload(){
        return payload;
    }

    public Priority getPriority(){
        return priority;
    }

    public LocalDateTime getScheduledTime(){
        return scheduledTime;
    }
    
    public MessageStatus getMessageStatus(){
         return messageStatus;
    }

    public void setStatus(MessageStatus status) {
       this.messageStatus = status;
    }

    public void incrementRetryCount() {
       retryCount++;
    }
    
    public int getRetryCount() {
      return retryCount;
    }
    
}
