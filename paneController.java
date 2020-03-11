package demo2;

import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.*;


public class paneController {

    private static int resultIndex = 0, plofResultIndex = 0;
    private Scene accountMenu, loginMenu, mainMenu, searchMenu, recoverMenu, examMenu, resultsMenu, patientMenu, helpMenu;
    private Stage primaryStage;
    private AnchorPane root;
    private static double[] answers = new double[42];
    private static double[] results = new double[5],  plofResults = new double[5];;
    private static String[] info = new String[4];
    private int index = 0;




    public paneController(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public Scene initStart(){
        clearRoot();
        loginMenu loginScene = new loginMenu(root, 800, 600);
        loginMenu = new Scene(loginScene.login_Pane(this), 800, 600);
        return loginMenu;
    }

    public void setInitialScene(){
        primaryStage.setTitle("Informal Cognitive Linguistic Assessment");
        primaryStage.setScene(initStart());
        primaryStage.show();
    }

    public void enterLoginMenu(){
        clearRoot();
        loginMenu loginScene = new loginMenu(root, 800, 600);
        loginMenu = new Scene(loginScene.login_Pane(this), 800, 600);
        primaryStage.setScene(loginMenu);

    }

    public void enterAccountMenu(){
        clearRoot();
        accountMenu accountScene = new accountMenu(root, 800, 600);
        accountMenu = new Scene(accountScene.accountPane(this), 800, 600);
        primaryStage.setScene(accountMenu);
    }

    public void enterMainMenu(){
        clearRoot();
        mainMenu mainMenuScene = new mainMenu(root, 800, 600);
        mainMenu = new Scene(mainMenuScene.menu_Pane(this), 800, 600);
        primaryStage.setScene(mainMenu);
    }

    public void enterSearchMenu(){
        clearRoot();
        searchMenu searchScene = new searchMenu(root, 800, 600);
        searchMenu = new Scene(searchScene.search_Pane(this), 800, 600);
        primaryStage.setScene(searchMenu);
    }

    public void enterRecoverMenu(){
        clearRoot();
        recoverMenu recoverScene = new recoverMenu(root, 800, 600);
        recoverMenu = new Scene(recoverScene.recoverPane(this),800,600);
        primaryStage.setScene(recoverMenu);
    }
    
    public void enterPatientMenu() {
    	clearRoot();
    	patientMenu patientScene = new patientMenu(root, 800, 600);
    	patientMenu = new Scene(patientScene.patient_Pane(this), 800, 600);
    	primaryStage.setScene(patientMenu);
    }

    public void enterExamMenu(){
        clearRoot();
        examMenu examScene = new examMenu(root, 800, 600);
        examMenu = new Scene(examScene.assess_Pane(this), 800, 600);
        primaryStage.setScene(examMenu);
    }
    
    public void enterHelpMenu(){
        clearRoot();
        helpMenu helpScene = new helpMenu(root, 800, 600);
        helpMenu = new Scene(helpScene.help_Pane(this), 800, 600);
        primaryStage.setScene(helpMenu);
    }

    public void enterResultsMenu(){
        clearRoot();
        resultsMenu resultsScene =new resultsMenu(root, 800, 600);
        resultsMenu = new Scene(resultsScene.resultsPane(this), 800, 600);
        primaryStage.setScene(resultsMenu);
    }

    public void sendRecover(String who){
        javaMail javaMail = new javaMail(who);
        try {
           javaMail.sendMail(who);
        }
        catch(Exception e)
        {
            ;
        }
    }

    public void clearRoot(){
        AnchorPane root = new mainPane();
        this.root = root;
    }

    public void setAnswers(double answerIn)
    {
        answers[index] = answerIn;
        index++;
    }

    public static double[] getAnswers()
    {
        return answers;
    }

    public void refreshAnswers()
    {
        index--;
    }

    public static void setResults(double resultIn)
    {
        results[resultIndex] = resultIn;
        resultIndex++;
    }

    public static double[] getResults()
    {
        return results;
    }

    public static void setPlofResults(double resultIn)
    {

        plofResults[plofResultIndex] = resultIn;
        plofResultIndex++;
    }

    public static double[] getPlofResults()
    {
        return plofResults;
    }


    
	public static void setInfo(String[] patientInfo) {
		info = patientInfo;
	}
	
	public static void print() {
		for (int i = 0; i < info.length; i++) {
			System.out.println(info[i]);
		}
	}
	
	public String[] getName() {
		return info;
	}


    


    


}