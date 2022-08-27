package noeasy.server.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import noeasy.server.domain.Vote;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class VoteDto {

    @NotNull
    private String title;

    private List<String> voteContent;

    public static class HomeDto{
        @NotNull
        private String title;

        private String expired;

        private List<VoteItemDto> itemList;

        public HomeDto(Vote vote) {
            this.title = vote.getTitle();
            this.expired = vote.getDeadline().toString();
            this.itemList = vote.getVoteItemList().stream().map(i -> new VoteItemDto(i)).collect(Collectors.toList());
        }
    }


}
