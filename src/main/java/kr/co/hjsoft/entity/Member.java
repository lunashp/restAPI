package kr.co.hjsoft.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Builder // builder를 사용할수 있게 합니다.
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member") // 'member' 테이블과 매핑됨을 명시
public class Member extends BaseEntity implements UserDetails {
    @Id
    private String memberNICKNAME;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(length = 200, nullable = false)
    private String memberPW;

    @Column(length = 30, nullable = false)
    private String memberNAME;

    @Column(length = 200, nullable = false)
    private String memberEMAIL;

    @Column(length = 11, nullable = false)
    private String memberPHONE;

    @Column(length = 3, nullable = false)
    private String memberGENDER;

    @Column(length = 600)
    private String memberADDRESS;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getPassword() {
        return this.memberPW;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public String getUsername() {
        return this.memberEMAIL;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
