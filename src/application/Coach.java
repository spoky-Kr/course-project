package application;

/*Coach - класс таблицы Тренеры.
 * Поля:
 * id - код тренера;
 * idGr - код группы;
 * fio - фио тренера;
 * sched - график тренера.
 */
public class Coach {
	private Integer id, idGr;
	private String fio,sched;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIdGr() {
		return idGr;
	}
	public void setIdGr(Integer idGr) {
		this.idGr = idGr;
	}
	public String getFio() {
		return fio;
	}
	public void setFio(String fio) {
		this.fio = fio;
	}
	public String getSched() {
		return sched;
	}
	public void setSched(String sched) {
		this.sched = sched;
	}
	public Coach(Integer id, Integer idGr, String fio, String sched) {
		super();
		this.id = id;
		this.idGr = idGr;
		this.fio = fio;
		this.sched = sched;
	}


}
