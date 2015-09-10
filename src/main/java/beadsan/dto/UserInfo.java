package beadsan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String mailAddress;
	private String password;
	private String firstName;
	private String lastName;
	private String nickname;

	private String getFullName() {
		return this.firstName + this.lastName;
	}
}
