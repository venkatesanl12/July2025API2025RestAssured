package DeserializationWithJSONArrayResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO with Lombok


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLombok {
	
	private Integer id;
	private String name;
	private String email;
	private String gender;
	private String status;

}