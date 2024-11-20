    /*
    dobavGrController - контроллер для управления формой добавления группы.
    Используемые подпрограммы:
    on_click_insert - процедура добавления записи в БД по нажатию кнопки;
    on_click_back - процедура закрытия формы добавления по нажатию конпки.
    */
package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

public class dobavGrController{
	@FXML
	private TextField kodField; //Текстовое поле для кода группы.
	@FXML
	private Button insertButton; //Кнопка добавления группы.
	@FXML
	private Button backButton; //Кнопка закрытия текущей формы.
	@FXML
	private TextField numField; //Текстовое поле для номера группы.
	@FXML
	private TextField cateField; //Текстовое поле для категории группы.

    Connection connection;
	
    /*
    on_click_insert - процедура добавления записи по кнопки "Добавить".
    Локальные переменные:
    id - переменная, хранящая код группы, полученный из текстового поля;
    numb - переменная, хранящая номер группы, полученный из текстового поля;
    cate - переменная, хранящая категорию группы, полученную из текстового поля;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    parseInt - процедура преобразования строки в число;
    getText - процедура получения строки из текстового поля;
    isEmpty - функция, возвращающая true, если строка пустая;
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    hide - процедура скрытия текущего окна.
    */
	@FXML
	public void on_click_insert(ActionEvent event) throws ClassNotFoundException, SQLException {
		JFrame jframe= new JFrame();
		if((kodField.getText().isEmpty())                   //Условие заполненности полей
				|| (numField.getText().isEmpty())           
				|| (cateField.getText().isEmpty())){
			JOptionPane.showMessageDialog(jframe, "Введите корректные данные");
		}
		else {
			Integer id=Integer.parseInt(kodField.getText());
			Integer numb=Integer.parseInt(numField.getText());
			String cate=cateField.getText();
	    	dbConnect connector=new dbConnect();
			connection = connector.getConnection();	
			connector.setGrDobav(id, numb, cate);
			insertButton.getScene().getWindow().hide();
		}
		
	}
    /*
    on_click_back - процедура закрытия формы добавления по нажатию кнопки "Назад".
    Используемые подпрограммы:
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    hide - процедура скрытия текущего окна.
    */
	@FXML
	public void on_click_back(ActionEvent event) {
		backButton.getScene().getWindow().hide();
	}
}
