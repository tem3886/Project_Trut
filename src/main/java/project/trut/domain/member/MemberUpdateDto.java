package project.trut.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Required;

import javax.validation.constraints.NotEmpty;

@Setter @Getter
public class MemberUpdateDto {

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

    public MemberUpdateDto() {
    }

    public MemberUpdateDto(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
