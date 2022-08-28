package noeasy.server.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import noeasy.server.domain.Vote;
import noeasy.server.domain.VoteItem;

@Getter
@Setter
public class VoteItemDto {
    @NotNull
    private Long id;

    private String content;

    @NotNull
    private Vote vote;

    @Setter
    @Getter
    public static class ListDto{
        @NotNull
        private Long id;

        @NotNull
        private String content;

        @NotNull
        private Boolean isChecked;

        @JsonProperty("vote_rate")
        public Double voteRate;

    }

    public VoteItemDto(VoteItem voteItem) {
        this.id = voteItem.getId();
        this.content= voteItem.getContent();
    }
}
