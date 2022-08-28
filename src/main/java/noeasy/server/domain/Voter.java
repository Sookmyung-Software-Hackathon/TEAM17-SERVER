package noeasy.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Voter {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="vote_id")
    private Vote vote;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "voteItem_id")
    private VoteItem voteItem;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="member_id")
    private Member member;

    public Voter(Vote vote, VoteItem voteItem, Member member) {
        this.vote = vote;
        this.voteItem = voteItem;
        this.member = member;
    }
    @NotNull
    @JsonProperty("vote_id")
    private String voteId;

    @NotNull
    private String memberId;

}
