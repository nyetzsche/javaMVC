package demo2;

//
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;

public class resultsMenu extends Scene {
//
//
    private Label patientName, date, dateOfBirth,overallScore, maxScore, minScore, auditoryProcessing, problemSolving, reasoning, memory, pragmatic, slpNotes;
    private Text patientNameOut, dateOut, overallScoreOut, maxScoreOut, minScoreOut, auditoryProcessingOut, problemSolvingOut, reasoningOut, memoryOut, pragmaticOut,slpNotesOut;
    private Button examButton, menuButton;
    Label title = new Label(" Informal Cognitive Linguistic Assessment \n RESULTS");
    private double minimum, moderate, max;
    private double[] answers;
    private double[] results;
    private double[] plofResults;
//
    public resultsMenu(Parent root, double width, double height) {
        super(root, width, height);
//
    }
        public Pane resultsPane (paneController paneController){

            Pane resultsPane = new Pane();
            resultsPane.setStyle("-fx-background-color: white");
            title.setStyle("-fx-font: 25 arial;");
            title.setTranslateX(70);
            resultsPane.getChildren().add(title);
            
            answers = paneController.getAnswers();
            results = paneController.getResults();
            double maxResult = 0;
            double minResult = 100;

            for (int resultsOutputs = 0; resultsOutputs < results.length; resultsOutputs++)
            {
                Text textResults = new Text();
                textResults.setText(resultsOutput(results[resultsOutputs], resultsOutputs));
                textResults.setTranslateX(350);
                textResults.setTranslateY((resultsOutputs * 40) + 330);
                resultsPane.getChildren().addAll(textResults);
                if (results[resultsOutputs] > maxResult)
                {
                    maxResult = results[resultsOutputs];
                }
                if (results[resultsOutputs] < minResult)
                {
                    minResult = results[resultsOutputs];
                }
            }


            plofResults = paneController.getPlofResults();

            String[] patientNameIn = paneController.getName();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDate localDate = LocalDate.now();
            String[] patientInfo = new String[5];
            patientInfo[0] = patientNameIn[0];
            patientInfo[1] = dtf.format(localDate);
            patientInfo[2] = String.valueOf(patientNameIn[3]) + " / " + String.valueOf(patientNameIn[1]) + " / "
            + String.valueOf(patientNameIn[2]);
            patientInfo[3] = String.valueOf(maxResult);
            patientInfo[4] = String.valueOf(minResult);

            for (int infoTotal = 0; infoTotal < patientInfo.length; infoTotal++)
            {
                Text infoText = new Text();
                infoText.setText(patientInfo[infoTotal]);
                infoText.setTranslateX(350);
                infoText.setTranslateY((infoTotal * 40) + 80);
                resultsPane.getChildren().addAll(infoText);
            }

            Label[] scoresTotalarr = {patientName, date, dateOfBirth, maxScore, minScore};
            String[] scoresTotalStringarr = {"patientName: ", "date: ", "dob: ", "maxScore: ", "minScore: "};

            for (int scoresTotal = 0; scoresTotal < 5; scoresTotal++)
            {

                scoresTotalarr[scoresTotal] = new Label(scoresTotalStringarr[scoresTotal]);
                scoresTotalarr[scoresTotal].setStyle("-fx-font-size: 12 arial; "
							                        + "-fx-background-color: #1dbfdb; "
							                        + "-fx-text-fill: white;"
							                        + "-fx-background-radius: 35px;");
                scoresTotalarr[scoresTotal].setTranslateX(300);
                scoresTotalarr[scoresTotal].setTranslateY((scoresTotal * 40) + 50);
                resultsPane.getChildren().addAll(scoresTotalarr[scoresTotal]);

            }


            Label[] resultsLabelarr = {auditoryProcessing, problemSolving,reasoning, memory, pragmatic, slpNotes};
            String[] resultsLabelStringsarr = {"auditory processing: ", "problem solving: ", "reasoning: ","memory: ", "pragmatic: ", "SLP notes (physician notes): "};

            for (int resultsLabels = 0; resultsLabels < 6; resultsLabels++)
            {
                resultsLabelarr[resultsLabels] = new Label(resultsLabelStringsarr[resultsLabels]);
                resultsLabelarr[resultsLabels].setStyle("-fx-font-size: 12 arial; "
                        + "-fx-background-color: #1dbfdb; "
                        + "-fx-text-fill: white;"
                        + "-fx-background-radius: 35px;");
                resultsLabelarr[resultsLabels].setTranslateX(270);
                resultsLabelarr[resultsLabels].setTranslateY((resultsLabels * 40) + 300);
                resultsPane.getChildren().addAll(resultsLabelarr[resultsLabels]);

            }


            menuButton = new Button("return to main menu");
            menuButton.setStyle("-fx-font-size: 15 arial; "
                    + "-fx-background-color: #1dbfdb; "
                    + "-fx-text-fill: white;"
                    + "-fx-background-radius: 25px;");
            menuButton.setTranslateY(550);
            menuButton.setTranslateX(50);
            menuButton.setOnAction(e -> {paneController.enterMainMenu();});

            resultsPane.getChildren().addAll(menuButton);

            return resultsPane;

        }

        public String resultsOutput(double resultIn, int categoryIndex)
        {
            double minLow = 10;
            double modLow = 25;
            double maxHigh = 60;
            String resultOut;

            if(resultIn > minLow && resultIn < modLow)

                resultOut = resultOutputArrays(categoryIndex, 2);
            }
            else if(resultIn > modLow && resultIn < maxHigh)
            {
                resultOut = resultOutputArrays(categoryIndex, 1);
            }
            else if(resultIn >= maxHigh)
            {
                resultOut = resultOutputArrays(categoryIndex, 0);
            }
            else
            {
                resultOut = resultOutputArrays(categoryIndex, 3);
            }

            return resultOut;
        }

        public String resultOutputArrays(int categoryIndex, int index)
        {
            String results[][] = new String[5][4];

            //auditory processing
            results[0][0] = "Max: At this level patient presents with severe decreased ability to understand thoughts in a timely manner. Patient is unable to answer long abstract questions. It is easier for patients to answer simple concrete and familiar questions. Patient will not understand long or complex conversations or more then one step directions. The family member will have to seek for clarification from the patient to make sure that the patient understands the information. Family may need to rephrase questions to optimize understanding.Patient benefits from concrete questions, no more than 3-4 words mean length of utterance. Patient benefits from repetition and needs at least 90 seconds to process information.";
            results[0][1] = "Moderate: At this level patient is able to answer moderately abstract questions that are 5-6 worlds in mean length of utterance. Family may need to rephrase questions as needed, however at his level patient may be able to ask for clarification at time. The patient may present a delay of processing information of at least 45 seconds and may benefit from additional time for thought organization. Family may need to repeat questions and redirect the patient�s attention to tasks to optimize understanding.";
            results[0][2] = "Min: At this level the patient benefits from repetition and clarification to optimize understanding of questions. The patient may be able to answer more complex questions. The patient may present at least a 10-15 seconds delay in answering questions, however the patient is able to ask for clarification when not understanding a question.";
            results[0][3] = "Functional: Patient is able to engage in normal question and answer process";

            //processing
            results[1][0] = "Max: At this level the patient is not able to solve problems by himself or herself. The patient is unable to identify problems, however once the problem has been identified for the patient, the patient may be able to provide a simple solution. However, the patient is unable to implement the solution without one on one assistance.";
            results[1][1] = "Moderate: At this level the patient may need some redirection to identify a problem and may be able to provide simple solutions, however the solution may not be the most viable solution. The patient may need direct supervision to solve simple everyday problems related to activities of daily living.";
            results[1][2] = "Min: At this level the patient is able to identify everyday problems and come up with multiple simple solutions, however may require some assistance to implement the solution.";
            results[1][3] = "Functional: The patient is able to identify the problem and implement solutions without assistance.";

            //reasoning
            results[2][0] = "Max: At this level the patient has severe difficulties understanding information related to cause and effect and logic. Patient may not be able to understand simple abstract information and may not be able to understand everyday language including idioms or dialectical difference in language. The patient is unable to process context clues.";
            results[2][1] = "Moderate: The patient may be able to understand familiar idioms and process tasks involving cause and effect given direct assistance. The patient is able to pick up more context clues during conversation and demonstrate some understanding or dialectical differences.";
            results[2][2] = "Min: The patient is able to understand familiar idioms and understand familiar dialectical differences as well as be able to understand cause and effect. The patient needs some supervision and redirection to understand more abstract information. The patient may be able to understand context clues when they are familiar to him/her.";
            results[2][3] = "Functional: The patient is able to understand all reasoning tasks";

            //memory
            results[3][0] = "Max: The patient may not be able to recall names or dates. The patient is unable to plan for future events based on previously learned information. If the patient is engaged in a task and stops he or she may not be able to resume task on their own. The patient may not be able to recall information that has just been given to them. Families need to provide max assistance for recalling of all information. At this level the patient is unable to use a memory journal. The patient is dependent on others to get medication as scheduled.";
            results[3][1] = "Moderate: At this level the patient benefits from the use of memory strategies including memory journals under direct supervision. The patient may not be able to remember dates, may have difficulties remembering new names, and will need direct supervision to implement schedule of medication. ";
            results[3][2] = "Min: At this level the patient may be able to use memory strategies including memory journals or agendas to plan for future events. May be able to follow a written schedule for medication. The patient may have some difficulty remembering past events and needs to write down important numbers and appointments as they will have a dependency on written queues to recall important events and activities.";
            results[3][3] = "Functional: The patient is able to recall information and implement strategies needed without supervision";

            //pragmatic
            results[4][0] = "Max: At this level the patient presents severe difficulties initiating conversations and staying on topic. The patient will become easily distracted by environmental noises. The patient may not be able to maintain eye contact and may use improper language during conversations. The patient is unable to take turns during conversation. ";
            results[4][1] = "Moderate: At this level the patient may present difficulties in turn taking and staying on topics after a few communication exchanges. The patient may become distracted and may need clarification on topic to continue to engage in conversation. The patient may be inappropriate at times in the use of their language, however can be redirected. The patient may avert eye contact with unfamiliar people.";
            results[4][2] = "Min: At this level the patient may need tactile cues to maintain eye contact and continue the flow of conversation. The patient may need to be reminded when it is his/her turn in an activity such as games. The patient may present with minimal communication breakdowns, however may be able to be verbally redirected to continue with conversations";
            results[4][3] = "Functional: The patient is able to fully participate in activities and conversations, take turns, and can maintain eye contact with no cues.";


            return results[categoryIndex][index];
        }

    }
