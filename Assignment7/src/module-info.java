module Assignment2 {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    exports GUI;
    opens GUI to javafx.fxml;

    exports Controller;
    opens Controller to javafx.fxml;

    exports Model.Expression;
    opens Model.Expression to javafx.fxml;

    exports Model.PrgState;
    opens Model.PrgState to javafx.fxml;

    exports Model.Statement;
    opens Model.Statement to javafx.fxml;

    exports Model.Type;
    opens Model.Type to javafx.fxml;

    exports Model.ADTs;
    opens Model.ADTs to javafx.fxml;

    exports Model.Value;
    opens Model.Value to javafx.fxml;

    exports Repository;
    opens Repository to javafx.fxml;
}