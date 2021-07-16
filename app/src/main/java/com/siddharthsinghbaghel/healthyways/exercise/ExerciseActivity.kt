  package com.siddharthsinghbaghel.healthyways.exercise

import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.siddharthsinghbaghel.healthyways.Constants
import com.siddharthsinghbaghel.healthyways.R
import com.siddharthsinghbaghel.healthyways.navFragments.ExerciseFragment
import kotlinx.android.synthetic.main.activity_exercise.*
import kotlinx.android.synthetic.main.activity_exercise_finish.*
import kotlinx.android.synthetic.main.dialog_custom_back_conformation.*
import kotlinx.android.synthetic.main.item_exercise_status.*
import java.util.*
import kotlin.collections.ArrayList

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

/* TextToSpeech.OnInitListener extended this to use the text to speech feature  */
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer? = null
    private var exerciseProgress = 0

    private var exerciseList: ArrayList<ExerciseModel>? = null
    private var currentExercisePosition = -1
    private var player: MediaPlayer? = null

    private var tts:TextToSpeech? = null /*  Instance of text to speech set to null because can't initialize directly*/

    private var exerciseAdapter: ExerciseStatusAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        setSupportActionBar(toolbar_exercise_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar_exercise_activity.setNavigationOnClickListener {

            setCustomDialogBckButton()

        }

        tts = TextToSpeech(this,this)

        /* Here Exercise Activity is both context and listener because Exercise activity is confront the onInitListener ..... */

        exerciseList = Constants.defaultExerciseList()
        setupRestView()

        setUpExerciseSetupRecyclerView()




    }


    public override fun onDestroy() {
        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        if(tts!=null){
            tts!!.stop()
            tts!!.shutdown()
        }

        if(player != null) {
            player!!.stop()
        }
        super.onDestroy()
    }


    private fun setupRestView() {

      /* Media player plays after every exercise ends*/
       try{
        player = MediaPlayer.create(applicationContext, R.raw.press_start)
        player!!.isLooping = false
        player!!.start()
       }catch(e: Exception) {
           e.printStackTrace()
       }

        llRestView.visibility = View.VISIBLE
        llExerciseView.visibility = View.GONE

        if (restTimer != null) {
            restTimer!!.cancel()
            restProgress = 0
        }

        tvUpCExerciseName.text = exerciseList!![currentExercisePosition + 1].getName()

        setRestProgressBar()


    }


    private fun setRestProgressBar() {

        progressBar.progress = restProgress

        restTimer = object : CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                progressBar.progress = 5 - restProgress
                tvTimer.text = (5 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition++

                exerciseList!![currentExercisePosition].setIsSelected(true)
                exerciseAdapter!!.notifyDataSetChanged()

                setupExerciseView()
            }
        }.start()
    }

    private fun setupExerciseView() {

        // Here according to the view make it visible as this is Exercise View so exercise view is visible and rest view is not.
        llRestView.visibility = View.GONE
        llExerciseView.visibility = View.VISIBLE

        if (exerciseTimer != null) {
            exerciseTimer!!.cancel()
            exerciseProgress = 0
        }

        speakOut(exerciseList!![currentExercisePosition].getName())
        /* speakOut - TTS*/
        ivImage_Exercise.setImageResource(exerciseList!![currentExercisePosition].getImage())
        tvExerciseName.text = exerciseList!![currentExercisePosition].getName()

        setExerciseProgressBar()

        tvStatusItem.setBackgroundResource(R.drawable.item_circular_color_grey_bg_current)
    }

    private fun setExerciseProgressBar() {

        progressBar.progress = exerciseProgress

        exerciseTimer = object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++

                exerciseProgressBar.progress = 30 - exerciseProgress
                tvExerciseTimer.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition < exerciseList!!.size - 1) {

                    exerciseList!![currentExercisePosition].setIsSelected(false)
                    exerciseList!![currentExercisePosition].setIsCompleted(true)
                    exerciseAdapter!!.notifyDataSetChanged()

                    setupRestView()

                } else {
                    finish()
                    val intent = Intent(this@ExerciseActivity, ExerciseFinishActivity::class.java)
                    startActivity(intent)
                }


            }
        }.start()
    }

    override fun onInit(status: Int) {

        if(status == TextToSpeech.SUCCESS)
            {
                val result = tts!!.setLanguage(Locale.US)
                Toast.makeText(
                        this@ExerciseActivity,
                        "TTS Fine.",
                        Toast.LENGTH_SHORT
                ).show()

                if(result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED ){
                    Toast.makeText(
                            this@ExerciseActivity,
                            "Missing.",
                            Toast.LENGTH_SHORT
                    ).show()
                }
        }else{
            Toast.makeText(
                    this@ExerciseActivity,
                    "Error in TTS",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun speakOut(text: String)
    {
        /* without utteranceId speak() shows deprecated, I don't know why */

        tts!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,null)
    }

    private fun setUpExerciseSetupRecyclerView(){

        rvExerciseStatus.layoutManager = LinearLayoutManager(this
                ,LinearLayoutManager.HORIZONTAL
                ,false)

        exerciseAdapter = ExerciseStatusAdapter(exerciseList!!,this)
        rvExerciseStatus.adapter = exerciseAdapter
    }

    private fun setCustomDialogBckButton(){

         val customDialog = Dialog(this)
        customDialog.setContentView(R.layout.dialog_custom_back_conformation)
        customDialog.tvYes.setOnClickListener{
            finish()
            customDialog.dismiss()
        }
        customDialog.tvNo.setOnClickListener{
            customDialog.dismiss()
        }

        customDialog.show()
    }


}