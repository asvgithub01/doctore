package com.asvfactory.asv.doctore.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alberto on 27/11/2015.
 */ //entrenamiento concreto, por ejemplo "fortalecer Falanges" contiene la funcion que llama al isolated
//con su propio
public class Training implements Serializable {
    String trainingName;//mandatory antes de poder guardar ejercicios
    String trainingID;//Nombre del file flexiones+timestamp
    List<Exercise> trainingExercises;
    List<TrainingStats> trainingStats;

    //region getter/setter
    public String getTrainingName() {
        return trainingName;
    }

    public void setTrainingName(String trainingName) {
        this.trainingName = trainingName;
    }

    public String getTrainingID() {
        return trainingID;
    }

    public void setTrainingID(String trainingID) {
        this.trainingID = trainingID;
    }

    public List<Exercise> getTrainingExercises() {
        return trainingExercises;
    }

    public void setTrainingExercises(List<Exercise> trainingExercises) {
        this.trainingExercises = trainingExercises;
    }

    public List<TrainingStats> getTrainingStats() {
        return trainingStats;
    }

    public void setTrainingStats(List<TrainingStats> trainingStats) {
        this.trainingStats = trainingStats;
    }

    //endregion
    //region Methods
    public void save() {

    }

    public void load() {

    }

    public void addExerciseItem(Exercise exercise) {

    }

    public void addStatsItem(TrainingStats trainingStats) {

    }

    public void deleteItem(String ExerciseID) {
        //borrar de trainingExercises
        //y de trainingStats
    }

    //
    public double totalTrainingEffort(Constants.E_EFFORT effortEnum) {
        double totalReturn = 0;
        for (Exercise item : this.trainingExercises) {
            totalReturn += item.effortExercise3();
        }

        return totalReturn;
    }

    //endregion
}
