package org.group9.gui;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MemberController {
    private Stage stage;
    private Button memberButton;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Button getMemberButton() {
        return memberButton;
    }

    public void setMemberButton(Button memberButton) {
        this.memberButton = memberButton;
    }
}
