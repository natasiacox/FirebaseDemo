package aydin.firebasedemo;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class WelcomeController {

    @FXML
    private Button registerButton;

    @FXML
    private Button signInButton;

    @FXML
    private TextField userEmail;

    @FXML
    private PasswordField userPassword;

    @FXML
    void registerButtonClicked(ActionEvent event) {
        registerUser();
    }

    public boolean registerUser() {
        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(userEmail.getText())
                .setEmailVerified(false)
                .setPassword(userPassword.getText());
        /*
                .setDisplayName(name);
                .setDisabled(false);
                .setPhoneNumber("");
         */

        UserRecord userRecord;
        try {
            userRecord = DemoApp.fauth.createUser(request);
            System.out.println("Successfully created new user with Firebase Uid: " + userRecord.getUid()
                    + " check Firebase > Authentication > Users tab");
            return true;

        } catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error creating a new user in the firebase");
            return false;
        }

    }

    @FXML
    void signInButtonClicked(ActionEvent event) throws FirebaseAuthException {
        signInUser();
    }

    public boolean signInUser()  {

        try {
            UserRecord userRecord = FirebaseAuth.getInstance().getUserByEmail(userEmail.getText());

            System.out.println("Successfully signed in user with User email: " + userRecord.getEmail());

            DemoApp.setRoot("primary");

            return true;

        }
        catch (FirebaseAuthException ex) {
            // Logger.getLogger(FirestoreContext.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Incorrect email and password entered");
            return false;
        }

        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}


