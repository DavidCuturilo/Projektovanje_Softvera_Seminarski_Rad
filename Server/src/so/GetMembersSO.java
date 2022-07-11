/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import java.util.List;
import model.Member;
import persistence.db.repository.MemberRepository;

/**
 *
 * @author David
 */
public class GetMembersSO extends AbstractSO{
    private MemberRepository memberRepository;
    public GetMembersSO() {
        this.memberRepository = new MemberRepository();
    }

    @Override
    protected void precondition(Object param) throws Exception {
        //no payload data for this operation
    }

    @Override
    protected Object executeTransaction(Object param) throws Exception {
        List<Member> members = this.memberRepository.getAll();
        return members;
    }
}
