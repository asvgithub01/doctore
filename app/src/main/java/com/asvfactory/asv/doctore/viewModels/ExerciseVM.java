package com.asvfactory.asv.doctore.viewModels;

import com.asvfactory.asv.doctore.model.Constants;
import com.asvfactory.asv.doctore.model.Exceptions;
import com.asvfactory.asv.doctore.model.Exercise;
import com.asvfactory.asv.doctore.model.Training;
import com.asvfactory.asv.doctore.model.TrainingStats;
import com.asvfactory.asv.doctore.model.TrainingTable;
import com.asvfactory.asv.doctore.util.FileHelper;

import java.util.ArrayList;

/**
 * Created by Alberto on 29/11/2015.
 * asv falta ver como impedir que se cree el vm si el modelo es null
 */
public class ExerciseVM {
    Exercise mModel;

    public ExerciseVM(Exercise model) {
        if (model != null)
            this.mModel = model;
       /* else
           throw Exceptions.exceptionVMMNULL;
           */
    }

    //region methods calculo de esfuerzo del ejercicio
    //tiempo de ejercicio neto*repeticiones
    public long effortExercise1() {
        return (this.mModel.getDefaultSecondsExercise() - this.mModel.getDefaultSecondsRelax()) * this.mModel.getDefaultRepetitions();
    }

    //tiempo total / tiempo total relax
    public long effortExercise2() {
        return ((this.mModel.getDefaultSecondsExercise() + this.mModel.getDefaultSecondsRelax()) * this.mModel.getDefaultRepetitions()) / (this.mModel.getDefaultSecondsRelax() * this.mModel.getDefaultRepetitions());
    }

    //custom 1, de momento el q mas convence
    public long effortExercise3() {
        return ((this.mModel.getDefaultSecondsExercise() * 2) / this.mModel.getDefaultSecondsRelax()) + (this.mModel.getDefaultRepetitions() / Constants.MAX_REPETITIONS);
    }
    //endregion
}