package entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Productos {
	private Long id;
	private String name;
	private String price;
	private int stock_min;
	private int stock_max;
	private String description;
	private boolean home;
}
