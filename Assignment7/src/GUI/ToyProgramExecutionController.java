package GUI;

import Controller.Controller;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import Model.ADTs.MyIDictionary;
import Model.ADTs.MyIHeap;
import Model.PrgState.PrgState;
import Model.Statement.IStmt;
import Model.Value.Value;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class Pair<T1, T2>{
    T1 first;
    T2 second;

    public Pair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }
}

public class ToyProgramExecutionController {
    private Controller controller;

    @FXML
    private TextField numberOfProgramStatesTextField;

    @FXML
    private TableView<Pair<Integer, Value>> heapTableView;

    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> addressColumn;

    @FXML
    private TableColumn<Pair<Integer, Value>, String> valueColumn;

    @FXML
    private ListView<String> outListView;

    @FXML
    private ListView<String> fileTableListView;

    @FXML
    private ListView<Integer> programStateListView;

    @FXML
    private TableView<Pair<String, Value>> symbolTableView;

    @FXML
    private TableColumn<Pair<String, Value>, String> variableNameColumn;

    @FXML
    private TableColumn<Pair<String, Value>, String> variableValueColumn;

    @FXML
    private ListView<String> executionStackListView;

    @FXML
    private Button runOneStepButton;

    @FXML
    public void initialize(){
        programStateListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        addressColumn.setCellValueFactory(p->new SimpleIntegerProperty(p.getValue().first).asObject());
        valueColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().second.toString()));
        variableNameColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().first));
        variableValueColumn.setCellValueFactory(p->new SimpleStringProperty(p.getValue().second.toString()));
    }

    private PrgState getCurrentPrgState(){
        if(controller.getProgramStates().size() == 0){
            return null;
        }
        else{
            int currentID = programStateListView.getSelectionModel().getSelectedIndex();
            if(currentID == -1){
                return controller.getProgramStates().get(0);
            }
            else{
                return controller.getProgramStates().get(currentID);
            }
        }
    }

    public void changeProgramState(MouseEvent event){
        populateExecutionStackListView();
        populateSymbolTableView();
    }

    public void populate(){
        populateHeapTableView();
        populateOutListView();
        populateFileTableListView();
        populateProgramStateListView();
        populateSymbolTableView();
        populateExecutionStackListView();
    }

    public void setController(Controller controller){
        this.controller = controller;
        populate();
    }

    private void populateNumberOfProgramStatesTextField(){
        List<PrgState> programStates = controller.getProgramStates();
        numberOfProgramStatesTextField.setText(String.valueOf(programStates.size()));
    }

    public void populateHeapTableView(){
        PrgState programState = getCurrentPrgState();
        MyIHeap heap = Objects.requireNonNull(programState).getHeap();
        ArrayList<Pair<Integer, Value>> heapValues = new ArrayList<>();
        for(Map.Entry<Integer, Value> heapValue: heap.getContent().entrySet()){
            heapValues.add(new Pair<>(heapValue.getKey(), heapValue.getValue()));
        }
        heapTableView.setItems(FXCollections.observableArrayList(heapValues));
    }
    public void populateOutListView(){
        PrgState programState = getCurrentPrgState();
        List<String> out = new ArrayList<>();
        List<Value> outList = Objects.requireNonNull(programState).getOut().getList();
        int index;
        for(index = 0; index < outList.size(); index++){
            out.add(outList.get(index).toString());
        }
        outListView.setItems(FXCollections.observableArrayList(out));
    }
    public void populateFileTableListView(){
        PrgState programState = getCurrentPrgState();
        List<String> files = new ArrayList<>(Objects.requireNonNull(programState).getFileTable().getDictionary().keySet());
        fileTableListView.setItems(FXCollections.observableList(files));
    }
    public void populateProgramStateListView(){
        List<PrgState> programStates = controller.getProgramStates();
        List<Integer> idList = programStates.stream().map(PrgState::getID).collect(Collectors.toList());
        programStateListView.setItems(FXCollections.observableList(idList));
        populateNumberOfProgramStatesTextField();
    }
    public void populateSymbolTableView(){
        PrgState prgState = getCurrentPrgState();
        MyIDictionary<String, Value> symTbl = Objects.requireNonNull(prgState).getSymTable();
        ArrayList<Pair<String, Value>> symTblEntries = new ArrayList<>();
        for(Map.Entry<String, Value> entry: symTbl.getDictionary().entrySet()){
            symTblEntries.add(new Pair<>(entry.getKey(), entry.getValue()));
        }
        symbolTableView.setItems(FXCollections.observableArrayList(symTblEntries));
    }
    public void populateExecutionStackListView(){
        PrgState prgState = getCurrentPrgState();
        List<String> executionStackList = new ArrayList<>();
        if(prgState != null){
            for(IStmt stmt: prgState.getStk().getReversed()) {
                executionStackList.add(stmt.toString());
            }
        }
        executionStackListView.setItems(FXCollections.observableList(executionStackList));
    }

    @FXML
    private void runOneStep(ActionEvent actionEvent){
        if(controller != null){
            try{
                List<PrgState> programStates = Objects.requireNonNull(controller.getProgramStates());
                if(programStates.size() > 0){
                    controller.oneStep();
                    populate();
                    programStates = controller.removeCompletedPrg(controller.getProgramStates());
                    controller.setProgramStates(programStates);
                    populateProgramStateListView();
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Execution Error");
                    alert.setContentText("There is nothing left to execute");
                    alert.showAndWait();
                }
            }
            catch (IOException | StatementExecutionException | ExpressionEvaluationException | InterruptedException | ADTException e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Execution Error");
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Execution Error");
            alert.setContentText("No program selected");
            alert.showAndWait();
        }
    }
}
