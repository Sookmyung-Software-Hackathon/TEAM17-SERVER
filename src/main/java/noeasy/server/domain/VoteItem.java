package noeasy.server.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
public class VoteItem {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="vote_id")
    private Vote vote;

    @NotNull
    private String content;

    public VoteItem(Vote vote, String content){
        this.vote = vote;
        this.content = content;
    }
}
