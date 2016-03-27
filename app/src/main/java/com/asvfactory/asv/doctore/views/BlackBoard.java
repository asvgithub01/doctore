package com.asvfactory.asv.doctore.views;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.asvfactory.asv.doctore.customViews.CircularSeekBar;
import com.asvfactory.asv.doctore.R;
import com.asvfactory.asv.doctore.base.FullScreenActivity;
import com.asvfactory.asv.doctore.util.SystemUiHider;

import java.util.HashMap;
import java.util.Vector;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class BlackBoard extends FullScreenActivity {

    //region progreessBar/controles/variables
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView txtProgressBar;
    private Handler handler = new Handler();

    private int pbMax = 0;
    private Button btnBegin;
    private Button btnDone;
    private CircularSeekBar sbAberroncher;
    private TextView txtSB;
    private LinearLayout LayoutSB;
    private TextView txtAux;
    private TextView lblSB;

    private TextView txtExerciseTime;
    private TextView txtRelaxTime;
    private TextView txtRepTime;
    private TextView txtRepeTotTime;

    private TextView txtVolume;
    private int currentVolume = 100;
    private ImageView imgVolume;
    private boolean isVolNotMute = true;

    private FrameLayout LayoutPB;
    private Runnable runnableReset;
    private Runnable runnableRepe;
    private Runnable runnableRelax;
    private Runnable runnableAberronche;

    private Runnable runnableUI;
    private Runnable runnableDocLoop;

    private boolean isThreadCanceled = false;
    private Thread hiloDoctore;

    boolean isTime4Aberronche = true;
    boolean isFinalSerie = false;

    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_black_board);
        //region catchControls
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
        txtProgressBar = (TextView) findViewById(R.id.textView1);

        sbAberroncher = (CircularSeekBar) findViewById(R.id.circularSeekBar1);
        txtSB = (TextView) findViewById(R.id.txtSB);
        LayoutSB = (LinearLayout) findViewById(R.id.LayoutSB);
        lblSB = (TextView) findViewById(R.id.lblSB);

        btnDone = (Button) findViewById(R.id.BtnDone);

        txtExerciseTime = (TextView) findViewById(R.id.txtExercise);
        txtRelaxTime = (TextView) findViewById(R.id.txtRelax);
        txtRepTime = (TextView) findViewById(R.id.txtRepe);
        txtRepeTotTime = (TextView) findViewById(R.id.txtTotRepe);
        txtVolume = (TextView) findViewById(R.id.txtVol);

        imgVolume = (ImageView) findViewById(R.id.imgVol);

        btnBegin = (Button) findViewById(R.id.BtnStart);

        LayoutPB = (FrameLayout) findViewById(R.id.PanelPB);

        //endregion
        //region Clicks controls
        btnBegin.setOnClickListener(new View.OnClickListener()

                                    {
                                        @Override
                                        public void onClick(View view) {

                                            if (hiloDoctore != null && hiloDoctore.isAlive()) {
//                                                hiloDoctore.interrupt();
//                                                hiloDoctore = null;
                                                isThreadCanceled = true;
                                                // handler.post(runnableReset);
                                                Button btn = (Button) view;
                                                btn.setText("Cancelando");
                                                btn.setEnabled(false);
                                            } else {
                                                btnBegin.setText(getResources().getString(R.string.res004_pausa));
                                                LayoutPB.setBackgroundColor(getResources().getColor(R.color.CustomGreen));
                                                isThreadCanceled = false;

                                                hiloDoctore = new Thread(runnableDocLoop);
                                                hiloDoctore.start();
                                            }
                                        }

                                    }

        );

        final View.OnClickListener textClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //set txtAux para el onchange del circular
                txtAux = (TextView) view;
                //config View Circular
                LayoutSB.setVisibility(View.VISIBLE);
                lblSB.setText(txtAux.getTag().toString());
                sbAberroncher.setMax(20);
                sbAberroncher.setProgress(getIntFromString(txtAux));
            }
        };

        final View.OnClickListener textRepeClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                txtRepTime.setText("0");
                textClick.onClick(view);

            }
        };
        View.OnClickListener VolClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtAux = (TextView) view;

                lblSB.setText(txtAux.getTag().toString());


                LayoutSB.setVisibility(View.VISIBLE);
                txtAux = (TextView) view;
                sbAberroncher.setMax(100);
                sbAberroncher.setProgress(getIntFromString(txtAux));

                imgVolume.setTag("On");
                imgVolume.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_white_vol));
                isVolNotMute = true;
            }
        };

        txtVolume.setOnClickListener(VolClick);


        imgVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (imgVolume.getTag() != null && imgVolume.getTag().equals("Off")) {
                    txtVolume.setText("100");
                    imgVolume.setTag("On");
                    imgVolume.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_white_vol));
                    isVolNotMute = true;
                } else {
                    isVolNotMute = false;
                    txtVolume.setText("0");
                    imgVolume.setTag("Off");
                    imgVolume.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.ic_white_vol_mute));
                }
            }
        });

        txtRepeTotTime.setOnClickListener(textRepeClick);
        txtExerciseTime.setOnClickListener(textClick);
        txtRelaxTime.setOnClickListener(textClick);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutSB.setVisibility(View.GONE);

                //todo guardar en shared preferences

            }
        });
//endregion
        //region circularseekBar
        sbAberroncher.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, int i, boolean fromUser) {
                if (i < 1)
                    i = 1;

                txtAux.setText(i + "");
                circularSeekBar.setProgress(i);
                txtSB.setText(i + "");

            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });
        //endregion
        initSounds();
        //region runnables para cambiar timers/hacer el playSound
        runnableDocLoop = new Runnable() {
            public void run() {
                if (!hiloDoctore.isInterrupted()) {

                    while (getCurrentRep() < getTotRep() - 1) {
                        if (isThreadCanceled) break;
                        int Max = getExerciseTimeInMinutes() * 60;
                        progressBar.setMax(Max);
                        handler.post(runnableAberronche);
                        progressStatus = 0;
                        while (progressStatus < Max) {
                            try {
                                if (progressStatus >= Max - 10 && isVolNotMute) {
                                    //ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, getCurrentVol());
                                    int countDown = Max - progressStatus;
                                    if (countDown > 2) {
                                        //   toneG.startTone(ToneGenerator.TONE_DTMF_1, 200);
                                        playSound(200);
                                    } else {
                                        //toneG.startTone(ToneGenerator.TONE_DTMF_1, 1000); // 200 is duration in ms
                                        playSound(2000);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                            if (isThreadCanceled) break;
                            progressStatus += 1;
                            handler.post(runnableUI);
                            try {
                                Thread.currentThread().sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }

                        handler.post(runnableRelax);
                        progressStatus = 0;

                        Max = getRelaxTimeInMinutes() * 60;
                        progressBar.setMax(Max);
                        //
                        while (progressStatus < Max) {

                            try {
                                if (progressStatus >= Max - 10 && isVolNotMute) {
                                    //ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, getCurrentVol());
                                    int countDown = Max - progressStatus;
                                    if (countDown > 2) {
                                        //   toneG.startTone(ToneGenerator.TONE_DTMF_1, 200);
                                        playSound(200);
                                    } else {
                                        //toneG.startTone(ToneGenerator.TONE_DTMF_1, 1000); // 200 is duration in ms
                                        playSound(2000);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (isThreadCanceled) break;
                            progressStatus += 1;
                            handler.post(runnableUI);
                            try {
                                Thread.currentThread().sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                        if (!isThreadCanceled)
                            handler.post(runnableRepe);
                        else
                            handler.post(runnableReset);
                    }
                }
            }
        };


        runnableUI = new Runnable() {
            public void run() {
                //asv para q lo sincronize lo meto en otro runnable
                progressBar.setProgress(progressStatus);
                int Restante = (int) (progressBar.getMax() - progressStatus);
                txtProgressBar.setText(Restante + "");
            }
        };
        runnableRelax = new Runnable() {
            @Override
            public void run() {
                if (!isThreadCanceled) {
                    LayoutPB.setBackgroundColor(getResources().getColor(R.color.CustomRed));
                    isTime4Aberronche = false;
                }
            }
        };

        runnableAberronche = new Runnable() {
            @Override
            public void run() {
                if (!isThreadCanceled) {
                    LayoutPB.setBackgroundColor(getResources().getColor(R.color.CustomGreen));
                    isTime4Aberronche = true;

                }
            }
        };


        runnableReset = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), "Serie finiquited Aberrronnnncher powA!(@strings MotherFuca)", Toast.LENGTH_LONG).show();
                progressBar.setProgress(0);
                playSound(2000);
                LayoutPB.setBackgroundColor(getResources().getColor(R.color.CustomGrey));
                txtProgressBar.setText("0");
                isThreadCanceled = false;
                hiloDoctore = null;
                btnBegin.setText(getResources().getString(R.string.res003_comienzo));
                btnBegin.setEnabled(true);
            }
        };

        runnableRepe = new Runnable() {
            @Override
            public void run() {

                int MaxRepe = getTotRep();
                int CurrRepe = getCurrentRep();

                if (CurrRepe + 1 == MaxRepe) {
                    //txtRepTime.setText("0");
                    // isThreadCanceled = true;
                    handler.post(runnableReset);
                } else {

                    CurrRepe += 1;
                    txtRepTime.setText(CurrRepe + "");
                }


            }
        };

//endregion

    }

    SoundPool mSoundPool;
    HashMap<Integer, Integer> mSoundPoolMap;
    private Vector<Integer> mKillSoundQueue = new Vector<Integer>();


    @SuppressWarnings("deprecation")
    public void initSounds() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            AudioAttributes attributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ALARM) //(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            mSoundPool = new SoundPool.Builder()
                    .setAudioAttributes(attributes)
                    .build();


        } else {
            mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);//asv solo 2 sonidos
        }
        mSoundPoolMap = new HashMap<Integer, Integer>();
        mSoundPoolMap.put(0, mSoundPool.load(getApplicationContext(), R.raw.open, 1));
        mSoundPoolMap.put(1, mSoundPool.load(getApplicationContext(), R.raw.close, 1));
        mSoundPoolMap.put(2, mSoundPool.load(getApplicationContext(), R.raw.finito, 1));

        // mSoundPoolMap.put(1,mSoundPool.load(getApplicationContext(),ToneGenerator.TONE_DTMF_1,1));
        //mSoundPool = new SoundPool(nbMaxSons, AudioManager.STREAM_MUSIC, 0);
        //mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
    }

    public void playSound(int duration) {

        int index = getIndexSoundToDo();

        float currVolumen = ((float) getCurrentVol() / 100); //mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        int soundId = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            soundId = mSoundPool.play(mSoundPoolMap.get(index), currVolumen, currVolumen, 1, 0, 1f);
        } else {
            // soundId = mSoundPool.play( R.raw.open, currVolumen, currVolumen, 1, 0, 1f);
            // soundId = mSoundPool.load(getApplicationContext(), R.raw.open, 1);
            soundId = mSoundPool.play(mSoundPoolMap.get(index), currVolumen, currVolumen, 1, 0, 1f);
        }
        mKillSoundQueue.add(soundId);

        handler.postDelayed(new Runnable() {
            public void run() {

                if (!mKillSoundQueue.isEmpty()) {
                    mSoundPool.stop(mKillSoundQueue.firstElement());
                }
            }
        }, duration);

    }

    private int getIndexSoundToDo() {

        if (isTime4Aberronche)
            return 0;//R.raw.open;
        else {
            if (progressBar.getProgress() > 0)
                return 1;//R.raw.close;
            else
                return 2;
        }
    }

    private int getCurrentVol() {
        return getIntFromString(txtVolume);
    }

    private int getIntFromString(TextView txt) {
        int resul = 1;
        if (txt != null) {
            try {
                resul = Integer.parseInt(txt.getText().toString());
            } catch (NumberFormatException nfe) {
                resul = 1;
            }
        }
        return resul;
    }

    private int getExerciseTimeInMinutes() {
        return getIntFromString(txtExerciseTime);
    }

    private int getRelaxTimeInMinutes() {
        return getIntFromString(txtRelaxTime);
    }

    private int getCurrentRep() {
        return getIntFromString(txtRepTime);
    }

    private int getTotRep() {
        return getIntFromString(txtRepeTotTime);
    }

    @Override
    public void onBackPressed() {
        if (LayoutSB.getVisibility() == View.VISIBLE)
            LayoutSB.setVisibility(View.GONE);
        else
            moveTaskToBack(true);
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
