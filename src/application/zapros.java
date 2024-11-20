package application;

/*zapros - класс для 2-ого запроса.
 * Поля:
 * zp - доход тренера;
 * namePool - название бассейна;
 * fio - фио бассейна.
 */
public class zapros {
 Double zp;
 String namePool;
 String fio;
public Double getZp() {
	return zp;
}
public void setZp(Double zp) {
	this.zp = zp;
}
public String getNamePool() {
	return namePool;
}
public void setNamePool(String namePool) {
	this.namePool = namePool;
}
public String getFio() {
	return fio;
}
public void setFio(String fio) {
	this.fio = fio;
}

public zapros(Double zp, String namePool, String fio) {
	super();
	this.zp = zp;
	this.namePool = namePool;
	this.fio = fio;
}
 
}
