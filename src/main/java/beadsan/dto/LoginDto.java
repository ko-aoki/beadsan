package beadsan.dto;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginDto implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 1, max = 50)
	private String mailAddress;
	@NotNull
	@Size(min = 1, max = 10)
	private String password;
	private String message;

}
