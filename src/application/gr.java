package application;
/*gr - класс для таблицы Группы.
 * Поля:
 * id - код группы;
 * numb - номер группы;
 * cate - категория группы.
 */
public class gr {
  private Integer id;
  private Integer numb;
  private String cate;

  
  public gr(Integer id, Integer numb, String cate) {
	super();
	this.id = id;
	this.numb = numb;
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
  public String getCate() {
	return cate;
}
  public void setCate(String cate) {
	this.cate = cate;
}


  
  
  
}
