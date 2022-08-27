package noeasy.server.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import noeasy.server.domain.dto.MemberDto;
import noeasy.server.util.RandomGenerator;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "member")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member extends Timestamped implements UserDetails {

    @Id
    @GeneratedValue(generator = RandomGenerator.generatorName)
    @GenericGenerator(name = RandomGenerator.generatorName, strategy = "com.noeasy.server.util.RandomGenerator")
    @Column
    private String id;

    @NotEmpty
    private String email;

    @NotNull
    private String nickname;

    @NotNull
    private String type;

    @NotNull
    @ManyToOne
    @JoinColumn(name ="team_id")
    private Team team;

    private Integer birthYear;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();


    public Member(MemberDto requestDto){

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
