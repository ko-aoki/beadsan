package beadsan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 50)
	private String mailAddress;
	@NotNull
	@Size(min = 1, max = 10)
	private String password;
	@NotNull
	@Size(min = 1, max = 20)
	private String nickname;
	private String message;
	private String errorMessage;

}
