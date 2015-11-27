package com.asvfactory.asv.doctore.entities;

import java.io.Serializable;
import java.io.SerializablePermission;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//listado con los ficheros de los entrenamientos posibles
public class TrainingTable implements Serializable {
    public List<Training> getTrainings() {
        return trainings;
    }

    public void setTrainings(List<Training> trainings) {
        this.trainings = trainings;
    }

    List<Training> trainings;

    public void addItem(Training item) {
        if (trainings == null)
            trainings = new ArrayList<>();

        trainings.add(item);
        item.save();
        this.save();

    }

    public void deleteItem() {

    }
}
//entrenamiento concreto, por ejemplo "fortalecer Falanges" contiene la funcion que llama al isolated
//con su propio
public class Training implements Serializable {
    String trainingName;//mandatory antes de poder guardar ejercicios
    String trainingID;//Nombre del file flexiones+timestamp
    List<Exercise> trainingExercises;
    List<TrainingStats> trainingStats;

    public void save() {

    }

    public void load() {

    }

    public void addExerciseItem(Exercise exercise) {

    }

    public void addStatItem(TrainingStats trainingStats) {

    }

    public void deleteItem(String ExerciseID) {
        //borrar de trainingExercises
        //y de trainingStats
    }
}
public class Exercise implements Serializable {
    String exerciseName;//flexiones
    String exerciseID;//flexiones
    int defaultSecondsExercise;//60sg //estos datos se guardan cuando se crea la tabla de ejercicios, xo no en la pantalla de training, si se modifica a posteriori se guardaría en el stats
    int defaultSecondsRelax;//60sg
    int defaultRepetitions;//3times
}
//para guardar cuantas veces y que ejercicio se ha realizado
// tb se guarda el ejercicio realizado real(en el momento en el que se cumplen las repeticiones realizadas)
//se guarda el id del exercise base y tb se guarda el exercise si difiere en algo del base
// cuando se ha realizado el Training
public class TrainingStats implements Serializable {
    Date trainingDate;
    Exercise exerciseDone;//si es null es xq es igual que el base y si tiene algo es que cuando se cargó se modificó alguno de los campos
    String baseExerciseID;


}
