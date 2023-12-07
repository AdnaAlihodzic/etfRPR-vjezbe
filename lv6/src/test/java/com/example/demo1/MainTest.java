package com.example.demo1;

import org.testfx.framework.junit5.Start;

import java.util.Objects;

@ExtendWith(ApplicationExtension.class)
class MainTest {

    private Label display;

    @Start
    public void start (Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(getClass().getResource("/com/example/demo1/hello-view.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
        stage.toFront();
    }

    @Test
    public void startWithZero (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        assertEquals("0", display.getText());
    }

    @Test
    public void numberOne (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        assertEquals("1", display.getText());
    }

    @Test
    public void number123 (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn2");
        robot.clickOn("#btn3");
        assertEquals("123", display.getText());
    }

    @Test
    public void number123Plus456 (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn2");
        robot.clickOn("#btn3");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn4");
        robot.clickOn("#btn5");
        robot.clickOn("#btn6");
        assertEquals("456", display.getText());
    }

    @Test
    public void number123Plus456Equals (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn2");
        robot.clickOn("#btn3");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn4");
        robot.clickOn("#btn5");
        robot.clickOn("#btn6");
        robot.clickOn("#equalsBtn");
        assertEquals("579.0", display.getText());
    }

    @Test
    public void dotBtn (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn8");
        robot.clickOn("#btn8");
        robot.clickOn("#dotBtn");
        robot.clickOn("#btn8");
        robot.clickOn("#btn8");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn1");
        robot.clickOn("#equalsBtn");
        assertEquals("89.88", display.getText());
    }

    @Test
    public void zeroBtn (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        assertEquals("0", display.getText());
    }

    @Test
    public void zeroBtn2 (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        robot.clickOn("#btn1");
        robot.clickOn("#btn0");
        assertEquals("10", display.getText());
    }

    @Test
    public void number100plusnumber100div2 (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn1");
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        robot.clickOn("#divBtn");
        robot.clickOn("#btn2");
        robot.clickOn("#equalsBtn");
        assertEquals("150.0", display.getText());
    }

    @Test
    public void viseZaRedomOperacija (FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#btn1");
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        robot.clickOn("#plusBtn");
        robot.clickOn("#minusBtn");
        robot.clickOn("#btn1");
        robot.clickOn("#btn0");
        robot.clickOn("#btn0");
        robot.clickOn("#plusBtn");
        robot.clickOn("#btn2");
        robot.clickOn("#equalsBtn");
        assertEquals("2.0", display.getText());
    }

    @Test
    public void tackaSaNulom(FxRobot robot) {
        display = robot.lookup("#display").queryAs(Label.class);
        robot.clickOn("#dotBtn");
        robot.clickOn("#btn9");
        robot.clickOn("#btn9");
        robot.clickOn("#plusBtn");
        robot.clickOn("#dotBtn");
        robot.clickOn("#btn0");
        robot.clickOn("#btn1");
        robot.clickOn("#equalsBtn");
        assertEquals("1.0", display.getText());
    }
}
