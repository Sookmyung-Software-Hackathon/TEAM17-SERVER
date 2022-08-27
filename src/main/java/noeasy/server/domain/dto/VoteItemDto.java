package noeasy.server.domain.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import noeasy.server.domain.VoteItem;

@Getter
@Setter
public class VoteItemDto {
    @NotNull
    private Long id;

    private String content;

    public VoteItemDto(VoteItem voteItem) {
        this.id = voteItem.getId();
        this.content= voteItem.getContent();
    }
}
