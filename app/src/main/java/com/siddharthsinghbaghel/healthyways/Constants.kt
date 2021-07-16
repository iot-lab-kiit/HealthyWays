package com.siddharthsinghbaghel.healthyways

import com.siddharthsinghbaghel.healthyways.exercise.ExerciseModel

class Constants {

    /* companion is equal to static  */
    companion object{
          fun defaultExerciseList(): ArrayList<ExerciseModel>{

              val exerciseList = ArrayList<ExerciseModel>()

              val jumpingJacks = ExerciseModel(1,
                      "Jumping Jacks",
                      R.drawable.ic_jumping_jacks,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(jumpingJacks)

              val wallSit = ExerciseModel(2,
                      "Wall Sits",
                      R.drawable.ic_wall_sit,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(wallSit)

              val pushUps = ExerciseModel(3,
                      "Push Ups",
                      R.drawable.ic_push_up,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(pushUps)

              val abdominalCrunch = ExerciseModel(4,
                      "Abdominal Crunch",
                      R.drawable.ic_abdominal_crunch,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(abdominalCrunch)

              val  stepUpOnChair= ExerciseModel(5,
                      "Step Up On Chair",
                      R.drawable.ic_step_up_onto_chair,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(stepUpOnChair)

              val  squat= ExerciseModel(6,
                      "Squats",
                      R.drawable.ic_squat,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(squat)

              val  tricepsDipOnChair= ExerciseModel(7,
                      "Triceps Dip On Chair",
                      R.drawable.ic_triceps_dip_on_chair,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(tricepsDipOnChair)

              val  plank = ExerciseModel(8,
                      "Plank",
                      R.drawable.ic_plank,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(plank)

              val  highKneesRunning = ExerciseModel(9,
                      "High Knees Running In Place",
                      R.drawable.ic_high_knees_running_in_place,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(highKneesRunning)

              val  lunges = ExerciseModel(10,
                      "Lunges",
                      R.drawable.ic_lunge,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(lunges)


              val pushUpsAndRotation = ExerciseModel(11,
                      "Push Ups And Rotation",
                      R.drawable.ic_push_up_and_rotation,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(pushUpsAndRotation)

              val sidePlank = ExerciseModel(12,
                      "Side Plank",
                      R.drawable.ic_side_plank,
                      isCompleted = false,
                      isSelected = false
              )
              exerciseList.add(sidePlank)

              return exerciseList
          }
    }
}