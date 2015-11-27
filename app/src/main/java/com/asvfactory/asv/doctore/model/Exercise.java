package com.asvfactory.asv.doctore.model;

import java.io.Serializable;

/**
 * Created by Alberto on 27/11/2015.
 */
public class Exercise implements Serializable {
    String exerciseName;//flexiones
    String exerciseDescription;//con las manos mirando hacia adentro flexionar los coodos
    String exerciseID;//flexiones
    int defaultSecondsExercise;//60sg //estos datos se guardan cuando se crea la tabla de ejercicios, xo no en la pantalla de training, si se modifica a posteriori se guardaría en el stats
    int defaultSecondsRelax;//60sg
    int defaultRepetitions;//3times

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

    //region methods
    //tiempo de ejercicio neto*repeticiones
    public long effortExercise1() {
        return (this.defaultSecondsExercise - this.defaultSecondsRelax) * this.defaultRepetitions;
    }

    //tiempo total / tiempo total relax
    public long effortExercise2() {
        return ((this.defaultSecondsExercise + this.defaultSecondsRelax) * this.defaultRepetitions) / (this.defaultSecondsRelax * this.defaultRepetitions);
    }

    //custom 1
    public long effortExercise3() {
        return ((this.defaultSecondsExercise * 2) / this.defaultSecondsRelax) + (this.defaultRepetitions / Constants.MAX_REPETITIONS);
    }
    //endregion
}
