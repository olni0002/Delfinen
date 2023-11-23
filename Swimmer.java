package DelfinKlubben;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
public class Swimmer {

    private String name;
    private String birthDate;
    private String activity;
    private List<TrainingResult> trainingResults;

    public Swimmer(String name, String birthDate, String activity) {
        this.name = name;
        this.birthDate = birthDate;
        this.activity = activity;
        this.trainingResults = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getActivity() {
        return activity;
    }

    public List<TrainingResult> getTrainingResults() {
        return trainingResults;
    }

    public void addTrainingResult(String discipline, int bestTime, String date) {
        TrainingResult result = new TrainingResult(discipline, bestTime, date);
        trainingResults.add(result);
    }

    public List<TrainingResult> getTop5Times() {
        trainingResults.sort(Comparator.comparingInt(TrainingResult::getBestTime));
        int size = Math.min(trainingResults.size(), 5);
        return new ArrayList<>(trainingResults.subList(0, size));
    }

    public void updateTrainingResult(String discipline, int newBestTime, String newDate) {
        TrainingResult existingResult = findTrainingResult(discipline);
        if (existingResult != null) {
            existingResult.updateResult(newBestTime, newDate);
        } else {
            System.out.println("The chosen discipline was not found for the swimmer.");
        }
    }

    private TrainingResult findTrainingResult(String discipline) {
        for (TrainingResult result : trainingResults) {
            if (result.getDiscipline().equalsIgnoreCase(discipline)) {
                return result;
            }
        }
        return null;
    }
}