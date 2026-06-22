package library.repository;

import java.util.*;
import library.models.*;

public class MemberRepository {
    private final HashMap<Integer,Member> memberRepository;

    public MemberRepository(){
        memberRepository = new HashMap<>();
    }

    public void addMemeber(Member member){
        memberRepository.put(member.getMemberId(),member);
    }

    public Member getMember(int memberId){
        if(memberRepository.containsKey(memberId)){
            memberRepository.get(memberId);
        }

        return null;
    }

    public boolean memberExists(int memberId){
         if(memberRepository.containsKey(memberId)){
            return true;
        }

        return false;
    }

    public List<Member> getAllMembers(){
        return new ArrayList<>(memberRepository.values());
    }
}
