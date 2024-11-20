package application;
/*Bass - класс для таблицы Бассейны.
 * Поля:
 * id - код бассена;
 * idCoach - код тренера;
 * idST - код абонемента;
 * name - название бассейна;
 * adress - адрес бассейна;
 * variety - разновидность бассейна.
 */
public class Bass {
	private Integer id, idCoach,idST;
	private String name, adress, variety;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdCoach() {
		return idCoach;
	}
	public void setIdCoach(Integer idCoach) {
		this.idCoach = idCoach;
	}
	public Integer getIdST() {
		return idST;
	}
	public void setIdST(Integer idST) {
		this.idST = idST;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public Bass(Integer id, Integer idCoach, Integer idST, String name, String adress, String variety) {
		super();
		this.id = id;
		this.idCoach = idCoach;
		this.idST = idST;
		this.name = name;
		this.adress = adress;
		this.variety = variety;
	}

	

}
