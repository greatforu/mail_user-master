import com.jfoenix.controls.*;
import com.jfoenix.transitions.JFXFillTransition;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import Codes.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.image.ImageView;
import java.io.File;

/**
 * Created by aksha on 30-03-2017.
 */

public class SignInController {
    @FXML private Label Logo;
    @FXML private JFXPasswordField password,passWord,emailPassword;
    @FXML private JFXButton signInButton,signInButton1,attach,signUp,sendMailButton,receiveMailButton;
    @FXML private JFXTextArea message;
    @FXML private JFXTextField firstName,lastName,emailID,outServer,inServer,to,subject,username,userName;
    @FXML private Label errorMessage,error,mail1,mail2,mail3,mail4,mail5,from,subjectLabel,messageLabel,date;
    @FXML private JFXToggleButton advanced;
    @FXML private AnchorPane cool;
    @FXML private Separator sep;
    @FXML private ImageView image;
    @FXML private JFXSpinner spinner,spinner1;
    @FXML private ImageView image1;

    static String users, path;
    static Stage st = new Stage();
    static db ob = new db();
    static base64 se = new base64();
    static SendMailBySite send = new SendMailBySite();
    static ReceivemailBySite receivemailBySite=new ReceivemailBySite();

    @FXML
    private void signInAction1(ActionEvent event){

        FadeTransition fade=new FadeTransition(Duration.millis(1000),signInButton);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.setCycleCount(1);

        FadeTransition fade1=new FadeTransition(Duration.millis(1000),username);
        fade1.setFromValue(0);
        fade1.setToValue(1);
        fade1.setCycleCount(1);

        FadeTransition fade2=new FadeTransition(Duration.millis(1000),password);
        fade2.setFromValue(0);
        fade2.setToValue(1);
        fade2.setCycleCount(1);

        FadeTransition fade3=new FadeTransition(Duration.millis(1000),sep);
        fade3.setFromValue(0);
        fade3.setToValue(1);
        fade3.setCycleCount(1);

        FadeTransition fade4=new FadeTransition(Duration.millis(1000),signInButton1);
        fade4.setFromValue(1);
        fade4.setToValue(0);
        fade4.setCycleCount(1);

        FadeTransition fade5=new FadeTransition(Duration.millis(1000),Logo);
        fade5.setFromValue(0);
        fade5.setToValue(1);
        fade5.setCycleCount(1);

        JFXFillTransition ft1=new JFXFillTransition(Duration.millis(1000),cool);
        ft1.setCycleCount(1);
        ft1.setToValue(Color.rgb(0,31,63,0.93));
        ft1.setFromValue(Color.WHITE);

        JFXFillTransition ft=new JFXFillTransition(Duration.millis(1000),cool);
        ft.setCycleCount(Timeline.INDEFINITE);
        ft.setFromValue(Color.rgb(0,31,63,0.93));
        ft.setToValue(Color.rgb(150,65,54,0.93));
        ft.setAutoReverse(true);

        ParallelTransition p1=new ParallelTransition(ft1,fade4);
        ParallelTransition p=new ParallelTransition();
        p.getChildren().addAll(fade,ft,fade1,fade2,fade3,fade5);
        SequentialTransition s=new SequentialTransition();
        s.getChildren().addAll(p1,p);
        ParallelTransition p2=new ParallelTransition(s);
        p2.play();
    }

    @FXML
    private void signInAction(ActionEvent event) throws Exception {
        signInButton.setVisible(false);
        //System.out.println("hello");
        spinner1.setVisible(true);
        users = username.getText();
        String pas = (new md5()).MD5(password.getText());
        if (ob.signIn(users, pas)) {
            st = (Stage) signInButton.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("Mail.fxml"));
            //Stage stage=new Stage();
            st.setTitle("Welcome " + users + "!");
            st.setScene((new Scene(root, 667, 562)));
        } else
            error.setText("Incorrect Password!");
        signInButton.setVisible(true);
        spinner1.setVisible(false);
    }

    @FXML
    private void sendMailAction(ActionEvent event) throws Exception {
        sendMailButton.setVisible(false);
        System.out.println("ok");
        spinner.setVisible(true);
        String To = to.getText();
        String sub = subject.getText();
        String messi = message.getText();
        send.sendMail(To, sub, messi, ob.ID, se.decodeBase64(ob.PASS), ob.SMTP, path);
        to.clear();
        subject.clear();
        message.clear();
        path = null;
        spinner.setVisible(false);
        sendMailButton.setVisible(true);
    }

    @FXML
    private void attachAction(ActionEvent event) throws Exception {
        System.out.println("Hey");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select");
        File file = fileChooser.showOpenDialog(new Stage());
        path = file.getAbsolutePath();
        System.out.println(path);
    }

    @FXML
    private void signUpAction(ActionEvent event) throws Exception {
        db obj = new db();
        if (obj.check(userName.getText())) {
            errorMessage.setText("Username already registered!");
        } else {
            base64 sec = new base64();
            obj.sign_Up(userName.getText(), (new md5()).MD5(passWord.getText()), firstName.getText(), lastName.getText(), emailID.getText(), sec.base64(emailPassword.getText()), inServer.getText(), outServer.getText());
            errorMessage.setText("Successfully Registered!");
        }
    }

    @FXML
    private void advancedAction(ActionEvent event) throws Exception {
        if (advanced.isSelected()) {
            outServer.setVisible(true);
            inServer.setVisible(true);
        } else {
            outServer.setVisible(false);
            inServer.setVisible(false);
        }
    }
    @FXML
    private void getMailAction(ActionEvent event)throws Exception{
        receivemailBySite.getMail(ob.ID,se.decodeBase64(ob.PASS),ob.IMAP);
        System.out.println(receivemailBySite.FROM[0]);
        mail1.setOpacity(1);
        mail2.setOpacity(1);
        mail3.setOpacity(1);
        mail4.setOpacity(1);
        mail5.setOpacity(1);
        receiveMailButton.setVisible(false);
        mail1.setText(receivemailBySite.subject1(1));
        mail2.setText(receivemailBySite.subject1(2));
        mail3.setText(receivemailBySite.subject1(3));
        mail4.setText(receivemailBySite.subject1(4));
        mail5.setText(receivemailBySite.subject1(5));
        EventHandler<MouseEvent> eventHandler1=new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("mail1.fxml"));
                    Scene scene = new Scene(root, 837, 562);
                    Stage stage = new Stage();
                    //stage.setTitle(l.getText());
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    //String ID=l.getId();
                    //int i=ID.indexOf("l");
                    //i++;
                    //String s=ID.substring(i);
                    //System.out.println(s+" "+i+" "+ID);
                    //int j=Integer.parseInt(s);
                    //System.out.println(j);
                    //j--;
                    from.setText(receivemailBySite.FROM[0]);
                    subjectLabel.setText(receivemailBySite.SUBJECT[0]);
                    date.setText(receivemailBySite.DATE[0]);
                    messageLabel.setText(receivemailBySite.MESSAGE[0]);
                }catch(Exception e){e.printStackTrace();}

            }
        };
        mail1.setOnMouseClicked(eventHandler1);
        //mail2.setOnMouseClicked(eventHandler);
        //mail3.setOnMouseClicked(eventHandler);
        //mail4.setOnMouseClicked(eventHandler);
        //mail5.setOnMouseClicked(eventHandler);
    }
}
