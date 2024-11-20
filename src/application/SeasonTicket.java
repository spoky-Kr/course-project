package application;
/*SeasonTicket - класс для таблицы Абонементы.
 * Поля:
 * id - код абонемента;
 * numb - номер абонемента;
 * onAweek - посещаемость по абонементу;
 * cost - стоимость абонемента;
 * cate - категория абонемента.
 */
public class SeasonTicket {
  Integer id, numb, onAweek;
  Double cost;
  String cate;
  public SeasonTicket(Integer id, Integer numb, Integer onAweek, Double cost, String cate) {
	super();
	this.id = id;
	this.numb = numb;
	this.onAweek = onAweek;
	this.cost = cost;
	this.cate = cate;
}

  public Integer getId() {
	return id;
}
  public void setId(Integer id) {
	this.id = id;
}
  public Integer getNumb() {
	return numb;
}
  public void setNumb(Integer numb) {
	this.numb = numb;
}
  public Integer getOnAweek() {
	return onAweek;
}
  public void setOnAweek(Integer onAweek) {
	this.onAweek = onAweek;
}
  public Double getCost() {
	return cost;
}
  public void setCost(Double cost) {
	this.cost = cost;
}
  public String getCate() {
	return cate;
}
  public void setCate(String cate) {
	this.cate = cate;
}
}
