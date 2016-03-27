package com.asvfactory.asv.doctore.viewModels;

import com.asvfactory.asv.doctore.model.Constants;
import com.asvfactory.asv.doctore.model.Exercise;
import com.asvfactory.asv.doctore.model.Training;
import com.asvfactory.asv.doctore.model.TrainingStats;
import com.asvfactory.asv.doctore.util.FileHelper;

import java.util.ArrayList;

/**
 * Created by Alberto on 27/03/2016.
 */
public class TrainingVM {
    Training mModel;

    public TrainingVM(String trainingID) {
        this.mModel = this.load(trainingID);
    }

    //region Methods
    public void delete() {
        this.mModel.setTrainingExercises(null);
    }

    public void save() {

        FileHelper.writeDoctoreFile(this.mModel, Constants.FILE_TRAINING + this.mModel.getTrainingID());
    }

    public Training load(String trainingID) {
        return (Training) FileHelper.readDoctoreFile(Constants.FILE_TRAINING + trainingID);
    }

    public void addExerciseItem(Exercise exercise) {
        if (this.mModel != null) {

            if (this.mModel.getTrainingExercises() == null)
                this.mModel.setTrainingExercises(new ArrayList<Exercise>());

            this.mModel.getTrainingExercises().add(exercise);
        }
    }

    public void addStatsItem(TrainingStats trainingStats) {
        if (this.mModel != null) {
            if (this.mModel.getTrainingStats() == null)
                this.mModel.setTrainingStats(new ArrayList<TrainingStats>());

            this.mModel.getTrainingStats().add(trainingStats);
        }
    }

    public void deleteExerciseItem(String ExerciseID) {
        //borrar de trainingExercises
        //y de trainingStats
    }

    //
    public double totalTrainingEffort(Constants.E_EFFORT effortEnum) {
        double totalReturn = 0;
        for (Exercise item : this.mModel.getTrainingExercises()) {
            ExerciseVM exerciseVM = new ExerciseVM(item);
            totalReturn += exerciseVM.effortExercise1();
        }

        return totalReturn;
    }

    //endregion
}
