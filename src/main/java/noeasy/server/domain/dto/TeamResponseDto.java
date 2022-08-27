package noeasy.server.domain.dto;

import lombok.Getter;
import lombok.Setter;
import noeasy.server.domain.Team;

@Getter @Setter
public class TeamResponseDto {
    Long id;
    String name;
    Double temperature;

    public TeamResponseDto(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.temperature = team.getTemperature();
    }
}


