
package PETAPIWithLombok;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {

	private Integer id;
	private String name;
	private String status;
	private List<String> photoUrls;
	private List<Tag> tags;
	private Category category;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Category {
		private Integer id;
		private String name;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Tag {
		private Integer id;
		private String name;
	}

}
