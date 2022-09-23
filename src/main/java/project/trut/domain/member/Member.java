package project.trut.domain.member;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Size(min=1, max=20)
    @Column(name="login_id", length = 20)
    private String loginId;
    @NotEmpty
    @Size(min=1, max=20)
    private String password;
    @NotEmpty
    @Size(min=1, max=20)
    private String name;

    public Member() {
    }

    public Member(String loginId, String password, String name) {
        this.loginId = loginId;
        this.password = password;
        this.name = name;
    }
}
