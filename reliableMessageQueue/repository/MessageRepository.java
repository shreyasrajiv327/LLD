package reliableMessageQueue.repository;

import reliableMessageQueue.models.*;
import java.util.*;
import java.time.*;

public class MessageRepository(){
    private HashMap<Integer,Message> messageRepository;

    public MessageRepository(){
        this.messageRepository = new HashMap<>();
    }

    public void addMessage(Message message){
        messageRepository.put(message.getMessageId,message);
    }

    public Message getMessage(int messageId){
        if(!messageRepository.get(messageId)){
           throw new IllegalArgumentException("Message not found");
        }

        return messageRepository.get(messageId);
    }

    public boolean messageExists(int messageId){
          if(!messageRepository.get(messageId)){
          return false;
        }
        return true;
    }

    public void removeMessage(int messageId){
        messageRepository.remove(messageId);
    }

    public List<Message> getAllMessages(){
        return new ArrayList<>(messageRepository.values());
    }
}