/*sqlzaprosController - контроллер для управления формой запросов.
 * Используемые подпрограммы:
 * on_click_back - процедура закрытия формы;
 * on_zap1 - процедура заполнения полей 1-ого запроса;
 * on_zap4 - процедура заполнения полей 4-ого запроса;
 * on_zap5 - процедура заполнения полей 5-ого запроса;
 * on_zap6 - процедура заполнения полей 6-ого запроса;
 * initialize() - процедура выполняющаяся при открытии формы.
 */
package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class sqlzaprosController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<zapros, String> Z2_col_FIO_Coach;

    @FXML
    private TableColumn<zapros, String> Z2_col_name_pool;

    @FXML
    private TableColumn<zapros, Double> Z2_col_zp_coach;

    @FXML
    private Button backButton;  //Кнопка назад

    @FXML
    private ComboBox<String> box_bass; //ComboBox с названиями бассейнов

    @FXML
    private ComboBox<String> box_bass2; //ComboBox с названиями бассейнов

    @FXML
    private ComboBox<String> box_bass3; //ComboBox с названиями бассейнов

    @FXML
    private ComboBox<String> box_coach_1; //ComboBox с ФИО тренеров

    @FXML
    private ComboBox<String> box_coach_2; //ComboBox с ФИО тренеров

    @FXML
    private ComboBox<String> box_coach_3; //ComboBox с ФИО тренеров

    @FXML
    private ComboBox<String> box_gr; //ComboBox с названиями групп

    @FXML
    private ComboBox<String> box_gr2;//ComboBox с названиями групп

    @FXML
    private ComboBox<String> box_money; //ComboBox с заработком

    @FXML
    private TableView<zapros> viewZap2;

    @FXML
    private Tab zapros1;

    @FXML
    private Tab zapros2;

    @FXML
    private Tab zapros3;

    @FXML
    private Tab zapros4;

    @FXML
    private Tab zapros5;

    @FXML
    private Tab zapros6;
    Connection connection;
    @FXML
    /*
    on_click_back - процедура закрытия формы добавления по нажатию кнопки "Назад".
    Используемые подпрограммы:
    getScene - процедура получения текущей сцены;
    getWindow - процедура получения текущего окна;
    hide - процедура скрытия текущего окна.
    */
    void on_click_back(ActionEvent event) {
		backButton.getScene().getWindow().hide();
    }

    /*on_zap1 - процедура выполнения первого запроса.
     *Локальные переменные:
     *field - поле, полученное из box_bass;
     *connector - объект класса dbConnect. 
     */
    @FXML
    void on_zap1(ActionEvent event) throws ClassNotFoundException, SQLException {
     	dbConnect connector=new dbConnect();
		connection = connector.getConnection();	
    	String field=box_bass.getSelectionModel().getSelectedItem();
    	box_coach_1.setItems(connector.getString("select coach.FIO from coach, bass where coach.id_coach=bass.id_coach and bass.name_pool='"+field+"'","coach.FIO"));
    
    }
    /*on_zap4 - процедура выполнения 4-ого запроса.
    *Локальные переменные:
    *field - поле, полученное из box_gr;
    *connector - объект класса dbConnect. 
     */
    @FXML
    void on_zap4(ActionEvent event) throws ClassNotFoundException, SQLException {
    	dbConnect connector=new dbConnect();
		connection = connector.getConnection();
		String field=box_gr.getSelectionModel().getSelectedItem();
		box_coach_3.setItems(connector.getString("select  coach.FIO from coach, gr where gr.id_gr=coach.id_gr and gr.category_gr='"+field+"'", "FIO"));
    }
    /*on_zap5 - процедура выполнения 5-ого запроса.
    *Локальные переменные:
    *field - поле, полученное из box_bass2;
    *connector - объект класса dbConnect. 
     */
    @FXML
    void on_zap5(ActionEvent event) throws ClassNotFoundException, SQLException {
    	dbConnect connector=new dbConnect();
		connection = connector.getConnection();
		String field=box_bass2.getSelectionModel().getSelectedItem();
		box_gr2.setItems(connector.getString("select count(gr.id_gr) from coach, bass, gr where gr.id_gr=coach.id_gr and coach.id_coach=bass.id_coach and bass.name_pool='"+field+"'", "count(gr.id_gr)"));
    }
    /*on_zap6 - процедура выполнения 6-ого запроса.
    *Локальные переменные:
    *field - поле, полученное из box_bass3;
    *connector - объект класса dbConnect. 
     */
    @FXML
    void on_zap6(ActionEvent event) throws ClassNotFoundException, SQLException {
    	dbConnect connector=new dbConnect();
		connection = connector.getConnection();
    	String field=box_bass3.getSelectionModel().getSelectedItem();
    	box_money.setItems(connector.getString("select sum(season_ticket.cost*10) from bass, season_ticket where season_ticket.id_season_ticket=bass.id_season_ticket and bass.name_pool='"+field+"'", "sum(season_ticket.cost*10)"));
    }
    /*initialize - процедура выполняющаяся при запуске формы.
     Для данной формы ComboBox заполняются различными данными,
     а также выполняются 2 и 3 запросы.
    Локальные переменные:
    connector - объект класса dbConnect;
    box_bass - ComboBox с названиями бассейнов;
    box_bass2 - ComboBox с названиями бассейнов;
    box_bass3 - ComboBox с названиями бассейнов;
    box_coach_2 - ComboBox ФИО тренеров;
    box_gr - ComboBox с названиями групп;
    Z2_col_FIO_Coach - колонка с ФИО тренеров;
    Z2_col_zp_coach - колонка с зарплатами тренеров;
    Z2_col_name_pool - колонка с названиями бассейнов;
    viewZap2 - таблица для демонстрации 2-ого запроса.
    Использованные подпрограммы:
    getConnection - процедура получения соединения с БД;
    setItems - добавление элементов в ComboBox.
     */
    @FXML
    void initialize() throws ClassNotFoundException, SQLException {
    	dbConnect connector=new dbConnect();
    	connection = connector.getConnection();	
    	box_bass.setItems(connector.getString("select distinct name_pool from bass","name_pool"));
    	box_coach_2.setItems(connector.getString("select  coach.FIO from coach, gr where gr.id_gr=coach.id_gr and gr.category_gr='Начинающие'", "FIO"));
    	box_gr.setItems(connector.getString("select distinct category_gr from gr","category_gr"));
    	box_bass2.setItems(connector.getString("select distinct name_pool from bass","name_pool"));
    	box_bass3.setItems(connector.getString("select distinct name_pool from bass","name_pool"));
    	Z2_col_FIO_Coach.setCellValueFactory(new PropertyValueFactory<>("fio"));
    	Z2_col_zp_coach.setCellValueFactory(new PropertyValueFactory<>("zp"));
    	Z2_col_name_pool.setCellValueFactory(new PropertyValueFactory<>("namePool"));
    	viewZap2.setItems(connector.zapros("select coach.FIO, season_ticket.cost*10, bass.name_pool from coach, bass, season_ticket where coach.id_coach=bass.id_coach and season_ticket.id_season_ticket=bass.id_season_ticket;"));
    }

}

    
    
   

