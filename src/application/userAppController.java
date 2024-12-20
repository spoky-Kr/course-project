/*
    userAppController - контроллер для управления формой администратора.
    Используемые подпрограммы:
    on_click_view - процедура обновления записей в tableView;
    on_click_zapros - процедура перехода к форме запросов по нажатию кнопки;
    initialize - процедура, выполняющаяся при инициализации формы.
    
 */
package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;

public class userAppController {
	@FXML
	private Tab tableST;

	@FXML
	private Tab tableGrup;
	
	@FXML
	private Tab tableCoach;
	
	@FXML
	private Tab tablePool;

	@FXML
	private Button viewButton;
	@FXML
	private Label userLable;
    @FXML
    private TableView<Coach> viewCoach;
    @FXML
    private TableColumn<Coach, Integer> id_coach;
    @FXML
    private TableColumn<Coach, String> fio_coach;
    @FXML
    private TableColumn<Coach, String> graf_coach;
    
    @FXML
    private TableView<gr> viewGr;
    @FXML
    private TableColumn<gr, Integer> id_gr;
    @FXML
    private TableColumn<gr, Integer> num_gr;
    @FXML
    private TableColumn<gr, String> cate_gr;

    @FXML
    private TableView<Bass> viewPool;
    @FXML
    private TableColumn<Bass, String> name_pool;
    @FXML
    private TableColumn<Bass, Integer> id_pool;
    @FXML
    private TableColumn<Bass, String> vPool;
    @FXML
    private TableColumn<Bass, String> adress_pool;

    @FXML
    private TableView<SeasonTicket> viewST;
    @FXML
    private TableColumn<SeasonTicket, Integer> id_ST;
    @FXML
    private TableColumn<SeasonTicket, Integer> num_ST;
    @FXML
    private TableColumn<SeasonTicket, Integer> ofWeek;
    @FXML
    private TableColumn<SeasonTicket, String> cate_ST;
    @FXML
    private TableColumn<SeasonTicket, Double> cost;
    
    @FXML
    private TableColumn<Bass, Integer> id_STFK;
    @FXML
    private TableColumn<Coach, Integer> id_grFK;
    @FXML
    private TableColumn<Bass, Integer> id_coachFK;
    @FXML
    private Button zaprosButton;
    
    Connection connection;

	// Event Listener on Button[#viewButton].onAction
	@FXML
	public void on_click_view(ActionEvent event) {
		// TODO Autogenerated
	}
   
	@FXML
    /*on_click_zapros - процедура перехода к форме запросов по нажатию кнопки.
    Локальные переменные:
    loader - объект класса FXMLLoader;
    root - объект класса Parent; 
    stage - объект класса Stage.
    */
    void on_click_zapros(ActionEvent event) {
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource("/application/sqlzapros.fxml"));
	    try {
		    loader.load();
	    } catch (IOException e) {
		    e.printStackTrace();
	    }
	    Parent root = loader.getRoot();
	    Stage stage = new Stage();
	    stage.setScene(new Scene(root));
	    stage.show();
    }
    /*initialize - процедура, выполняющаяся при инициализации формы.
   Для данной формы производится обозначение типов столбцов таблиц
   и заполнение таблиц данным из базы данных.
   Локальные переменные:
   connector - объект класса dbConnect.
   Используемые подпрограммы:
   setItems - процедура добавления элементов списка в tableView.
   getCoach - процедура получения списка записей из таблицы coach;
   getBass - процедура получения списка записей из таблицы bass;
   getgr - процедура получения списка записей из таблицы gr;
   getSeasonTicket - процедура получения списка записей из таблицы season_ticket;
   getUsers - процедура получения списка записей из таблицы users.
   */
	public void initialize() throws ClassNotFoundException, SQLException{
		// TODO Auto-generated method stub
		dbConnect connector=new dbConnect();
		connection = connector.getConnection();
    	
    		id_coach.setCellValueFactory(new PropertyValueFactory<>("id"));			//Обозначение типов для столбцов
    		id_grFK.setCellValueFactory(new PropertyValueFactory<>("idGr"));        //таблицы тренеры
    		fio_coach.setCellValueFactory(new PropertyValueFactory<>("fio"));
    		graf_coach.setCellValueFactory(new PropertyValueFactory<>("sched"));
    		viewCoach.setItems(connector.getCoach("select * from coach"));
    	
    		id_pool.setCellValueFactory(new PropertyValueFactory<>("id"));			//Обозначение типов для столбцов
    		name_pool.setCellValueFactory(new PropertyValueFactory<>("name"));		//таблицы бассейнов
    		vPool.setCellValueFactory(new PropertyValueFactory<>("variety"));
    		adress_pool.setCellValueFactory(new PropertyValueFactory<>("adress"));
    		id_coachFK.setCellValueFactory(new PropertyValueFactory<>("idCoach"));
    		id_STFK.setCellValueFactory(new PropertyValueFactory<>("idST"));
    		viewPool.setItems(connector.getBass("select * from bass"));
    	
    		id_gr.setCellValueFactory(new PropertyValueFactory<>("id"));			//Обозначение типов для столбцов
    		num_gr.setCellValueFactory(new PropertyValueFactory<>("numb"));			//таблицы Группы
    		cate_gr.setCellValueFactory(new PropertyValueFactory<>("cate"));
    		viewGr.setItems(connector.getgr("select * from gr"));
    	
    		id_ST.setCellValueFactory(new PropertyValueFactory<>("id"));			//Обозначение типов для столбцов
    		num_ST.setCellValueFactory(new PropertyValueFactory<>("numb"));			//таблицы Абонементы
    		ofWeek.setCellValueFactory(new PropertyValueFactory<>("onAweek"));
    		cate_ST.setCellValueFactory(new PropertyValueFactory<>("cate"));
    		cost.setCellValueFactory(new PropertyValueFactory<>("cost"));
    		viewST.setItems(connector.getSeasonTicket("select * from season_ticket"));

    		
	}
}
