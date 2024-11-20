    /*
    dobavSTController - контроллер для управления формой добавления Абонемента.
    Используемые подпрограммы:
    on_click_insert - процедура добавления записи в БД по нажатию кнопки;
    on_click_back - процедура закрытия формы добавления по нажатию конпки;
    initialize - метод выполняющийся при открытии формы.
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

public class dobavSTController {
	@FXML
	private TextField kodField;
	@FXML
	private Button insertButton;
	@FXML
	private Button backButton;
	@FXML
	private TextField numField;
	@FXML
	private TextField cateField;
	@FXML
	private TextField onceField;
	@FXML
	private TextField costField;

    Connection connection;
    /*
    on_click_insert - процедура добавления записи по кнопки "Добавить".
    Локальные переменные:
    id -код абонемента;
    numb - номер абонемента;
    cate - категория абонемента;
    once - посещаемость по абонементу;
    cost - стоимость абонемента;
    jframe - объект класса JFrame;
    connector - объект класса dbConnect.
    Используемые подпрограммы:
    parseInt - процедура преобразования строки в число;
    getText - процедура получения строки из текстового поля;
    isEmpty - функция, возвращающая true, если строка пустая;
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    setStDobav - добавления записи в таблицу Абонементы;
    hide - процедура скрытия текущего окна.
    */
	@FXML
	public void on_click_insert(ActionEvent event) throws ClassNotFoundException, SQLException {
		JFrame jframe= new JFrame();
		if((kodField.getText().isEmpty()) 
				|| (numField.getText().isEmpty()) 
				|| (cateField.getText().isEmpty()) 
				|| (onceField.getText().isEmpty())
				|| (costField.getText().isEmpty())){
			JOptionPane.showMessageDialog(jframe, "Введите корректные данные");
		}
		else {
			Integer id=Integer.parseInt(kodField.getText());
			Integer numb=Integer.parseInt(numField.getText());
			String cate=cateField.getText();
			Integer once=Integer.parseInt(onceField.getText());
			Double cost=Double.parseDouble(costField.getText());
	    	dbConnect connector=new dbConnect();
			connection = connector.getConnection();	
			connector.setStDobav(id, numb, cate, once, cost);
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
