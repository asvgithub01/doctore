package com.asvfactory.asv.doctore.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Alberto on 27/11/2015.
 *
 *  asv falta:
 * trasladar todo lo q son metodos al ViewModel
 * generar getter/setter trainingOrder, training description
 * generar constructor
 * hacer q sea @Bindable, o no y simplemente oneway
 */ //entrenamiento concreto, por ejemplo "fortalecer Falanges" contiene la funcion que llama al isolated
//con su propio
public class Training implements Serializable {
    String trainingName;//mandatory antes de poder guardar ejercicios
    String trainingID;//Nombre del file flexiones+timestamp
    List<Exercise> trainingExercises;
    List<TrainingStats> trainingStats;
    int trainingOrder;
    String trainingDescription;


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

}
