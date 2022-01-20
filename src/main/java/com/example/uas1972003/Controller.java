package com.example.uas1972003;

import com.example.DAO.MemberDAO;
import com.example.DAO.PointDAO;
import com.example.DAO.TransactionDAO;
import com.example.Model.FeMemberEntity;
import com.example.Model.FePointEntity;
import com.example.Model.FeTransactionEntity;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.lang.reflect.Member;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller implements Initializable {


    public TextField txt1;
    public TextField txt2;
    public TextArea txtArea;
    public TextField txt4;
    public TextField txt5;
    public TextField txt6;
    public TextField txtNom;
    public DatePicker date1;
    public DatePicker date2;
    public Label lab1;
    public Label lab2;
    public Label lab3;
    public Label lab4;
    public Label lab5;
    public Label lab6;
    public Label lab7;
    public Label lab8;
    public Label lab9;
    public TableView<FeMemberEntity> tabMember;
    public TableColumn<FeMemberEntity, String> col1;
    public TableColumn<FeMemberEntity, String> col2;
    public TableColumn<FeMemberEntity, String> col3;
    public TableColumn<FeMemberEntity, String> col4;
    public Button btnSave;
    public Button btnReset;
    public Button btnUpdate;
    public TableView<FeTransactionEntity> tabTransaction;
    public TableColumn<FeTransactionEntity, String> colTransaction;
    public TableColumn<FeTransactionEntity, String> colNom;
    public Label labTotal;
    public Label labTotMember;
    public Label labTotPoin;
    public TableView<FePointEntity> tabPoint;
    public TableColumn<FePointEntity, String> colId;
    public TableColumn<FePointEntity, String> colPoint;
    private ObservableList<FeMemberEntity> anggotas;
    private ObservableList<FeTransactionEntity> transactions;
    private ObservableList<FePointEntity> points;

    public void saveMemberAction(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                FeMemberEntity member =new FeMemberEntity();
                member.setCitizenId(txt1.getText());
                member.setName(txt2.getText());
                member.setAddress(txtArea.getText());
                member.setPhone(txt4.getText());
                member.setEmail(txt5.getText());
                member.setUsername(txt6.getText());
                member.setBirthdate(Date.valueOf(date1.getValue()));
                MemberDAO memberDAO = new MemberDAO();
                memberDAO.addData(member);
                anggotas.clear();
                anggotas.addAll(memberDAO.showData());
                tabMember.refresh();
                txt1.setText("");
                txt2.setText("");
                txt4.setText("");
                txt5.setText("");
                txt6.setText("");
                date1.setValue(null);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();

    }

    public void resetAction(ActionEvent actionEvent) {
        resetForm();
    }

    private void resetForm(){
        txt1.clear();
        txt2.clear();
        txtArea.clear();
        txt4.clear();
        txt5.clear();
        txt6.clear();
        date1.setValue(null);

    }
    public void updateAction(ActionEvent actionEvent) {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                FeMemberEntity selected;
                selected = tabMember.getSelectionModel().getSelectedItem();
                selected.setBirthdate(Date.valueOf(date1.getValue()));
                selected.setName(txt2.getText());
                selected.setAddress(txtArea.getText());
                selected.setPhone(txt4.getText());
                selected.setEmail(txt5.getText());
                selected.setUsername(txt6.getText());
                MemberDAO memberDAO = new MemberDAO();
                int result = memberDAO.updateData(selected);
                if (result != 0){
                    System.out.println("Update Berhasil");
                }
                anggotas.clear();
                anggotas.addAll(memberDAO.showData());
                tabMember.refresh();
                btnSave.setDisable(false);
                btnReset.setDisable(false);
                btnUpdate.setDisable(true);
                txt1.setDisable(false);
                txt1.clear();
                txt2.clear();
                txtArea.clear();
                txt4.clear();
                txt5.clear();
                txt6.clear();
                date1.setValue(null);
                return null;
            }
        };
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(task);
        service.shutdown();

    }

    public void saveTransAction(ActionEvent actionEvent) {
        FeTransactionEntity transaction =new FeTransactionEntity();
        transaction.setNominal(Long.valueOf(txtNom.getText()));
        transaction.setTransDate(Date.valueOf(date2.getValue()));
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.addData(transaction);
        transactions.clear();
        transactions.addAll(transactionDAO.showData());
        tabTransaction.refresh();
        txtNom.setText("");
        date2.setValue(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MemberDAO memberDAO = new MemberDAO();
        anggotas = (ObservableList<FeMemberEntity>) memberDAO.showData();
        tabMember.setItems(anggotas);
        col1.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getCitizenId())));
        col2.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getName())));
        col3.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getPhone())));
        col4.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getBirthdate())));




        TransactionDAO transactionDAO = new TransactionDAO();
        transactions = (ObservableList<FeTransactionEntity>) transactionDAO .showData();
        tabTransaction.setItems(transactions);
        colTransaction.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getTransDate())));
        colNom.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getNominal())));
        int total = 0 ;
        for (FeTransactionEntity item : tabTransaction.getItems()) {
            total = (int) (total + item.getNominal());
        }
        labTotal.setText(String.valueOf(total));

        PointDAO pointDAO = new PointDAO();
        points = (ObservableList<FePointEntity>) pointDAO .showData();
        tabPoint.setItems(points);
        colId.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        colPoint.setCellValueFactory(data->new SimpleStringProperty(String.valueOf(data.getValue().getValue())));

//        int column1 = tabPoint.getColumns().size();
//        labTotPoin.setText(String.valueOf(column1));

        int column = tabMember.getColumns().size();
        labTotMember.setText(String.valueOf(column));
    }

    public void itemPil(MouseEvent event) {
        FeMemberEntity member = tabMember.getSelectionModel().getSelectedItem();
        txt1.setDisable(true);
        btnSave.setDisable(true);
        btnUpdate.setDisable(false);
        btnReset.setDisable(false);
        txt1.setText(String.valueOf(tabMember.getSelectionModel().getSelectedItem().getCitizenId()));
        txt2.setText(String.valueOf(tabMember.getSelectionModel().getSelectedItem().getName()));
        txtArea.setText(String.valueOf(tabMember.getSelectionModel().getSelectedItem().getAddress()));
        txt4.setText(String.valueOf(tabMember.getSelectionModel().getSelectedItem().getPhone()));
        txt5.setText(String.valueOf(tabMember.getSelectionModel().getSelectedItem().getEmail()));
        txt6.setText(String.valueOf(tabMember.getSelectionModel().getSelectedItem().getUsername()));
        date1.setValue(LocalDate.parse(String.valueOf(tabMember.getSelectionModel().getSelectedItem().getBirthdate())));
    }
}