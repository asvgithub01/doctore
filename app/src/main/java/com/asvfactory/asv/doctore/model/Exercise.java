package com.asvfactory.asv.doctore.model;

import java.io.Serializable;

/**
 * Created by Alberto on 27/11/2015.
 * asv falta:
 * trasladar todo lo q son metodos al ViewModel
 * generar getter/setter exercise Order
 * generar constructor
 * hacer q sea @Bindable, o no y simplemente oneway
 */
public class Exercise implements Serializable {
    String exerciseName;//flexiones
    String exerciseDescription;//con las manos mirando hacia adentro flexionar los coodos
    String exerciseID;//flexionestimestamp hash
    int exerciseOrder;//lo alimenta el orden del training

    Constants.E_EXERCISE_TYPE exerciseType;//este campo resultado de un switch, determina si rellenar las repeticiones
    //E_EXERCISE_TYPE-->TIME
    int defaultSecondsExercise;//60sg //estos datos se guardan cuando se crea la tabla de ejercicios, xo no en la pantalla de training, si se modifica a posteriori se guardaría en el stats
    int defaultSecondsRelax;//60sg
    //E_EXERCISE_TYPE-->REPETITIONS
    int defaultRepetitions;//3times
    int defaultNumSeries;//2 series de x repeticiones

    //region getter/setter
    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getExerciseDescription() {
        return exerciseDescription;
    }

    public void setExerciseDescription(String exerciseDescription) {
        this.exerciseDescription = exerciseDescription;
    }

    public String getExerciseID() {
        return exerciseID;
    }

    public void setExerciseID(String exerciseID) {
        this.exerciseID = exerciseID;
    }

    public int getDefaultSecondsExercise() {
        return defaultSecondsExercise;
    }

    public void setDefaultSecondsExercise(int defaultSecondsExercise) {
        this.defaultSecondsExercise = defaultSecondsExercise;
    }

    public int getDefaultSecondsRelax() {
        return defaultSecondsRelax;
    }

    public void setDefaultSecondsRelax(int defaultSecondsRelax) {
        this.defaultSecondsRelax = defaultSecondsRelax;
    }

    public int getDefaultRepetitions() {
        return defaultRepetitions;
    }

    public void setDefaultRepetitions(int defaultRepetitions) {
        this.defaultRepetitions = defaultRepetitions;
    }

    //endregion


}
