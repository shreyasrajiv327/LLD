package reliableMessageQueue.models;

import java.util.*;
import java.time.*;
class Message{
    enum Priority{
        HIGH,
        MEDIUM,
        LOW
    }
    private int messageId;
    private String payload;
    private Priority priority;
    private LocalDateTime scheduledTime;

    public Message(int messageId,String payload, Priority priority, LocalDateTime scheduledTime){
        this.messageId = messageId;
        this.payload = payload;
        this.priority = priority;
        this.scheduledTime = scheduledTime;
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


    
}
