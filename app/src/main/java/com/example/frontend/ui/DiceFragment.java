package com.example.frontend.ui;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.frontend.gamelogic.Dice;
import com.example.frontend.R;
import com.example.frontend.ui.viewmodels.DiceView;

public class DiceFragment extends Fragment implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private Dice dice;
    private ImageView diceImage;
    private Button continueButton;
    private DiceView diceView;

    private final float SHAKE_THRESHOLD = 15;
    private long sensorCount;
    private float lastX,lastY,lastZ;
    public DiceFragment(){

    }

    public static DiceFragment newInstance() {
        DiceFragment dicefrag = new DiceFragment();
        Bundle args = new Bundle();
        dicefrag.setArguments(args);
        return dicefrag;
    }
    //TODO: add onCreate für server
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.sensorManager = (SensorManager) requireActivity().getSystemService(Context.SENSOR_SERVICE);
        this.dice = new Dice();
        this.sensorCount=0;
        this.lastX = -1f;
        this.lastY = -1f;
        this.lastZ = -1f;
        this.accelerometer=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        this.diceView = new ViewModelProvider(requireActivity()).get(DiceView.class);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dice, container, false);
        diceImage = view.findViewById(R.id.diceImage);
        continueButton = view.findViewById(R.id.continueButtonDiceFragment);
        continueButton.setOnClickListener(v -> {

            // TODO: Weitere navigation zu anderen fragments
            diceView.setContinuePressed(true);
            diceView.setDices(dice);

        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(sensorEvent.sensor.getType() != Sensor.TYPE_ACCELEROMETER) return;

        long currentTime = System.currentTimeMillis();
        if((currentTime - sensorCount) > 1000) {

            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];


            if(x > 3.0 || y > 3.0 || z > 3.0){

                dice.useDice();

                updateDiceImage(diceImage, dice.getDice());
                System.out.println("Würfel:" + dice.getDice());

            }
        }
    }


    private void updateDiceImage(ImageView diceImage,int diceValue) {
        //TODO: add pictures of dices
        switch(diceValue){
            case 1:
                diceImage.setImageResource(R.drawable.dice1);
                break;
            case 2:
                diceImage.setImageResource(R.drawable.dice1);
                break;
            case 3:
                diceImage.setImageResource(R.drawable.dice1);
                break;
            case 4:
                diceImage.setImageResource(R.drawable.dice1);
                break;
            case 5:
                diceImage.setImageResource(R.drawable.dice1);
                break;
            case 6:
                diceImage.setImageResource(R.drawable.dice1);
                break;
        }
        // statt switch: int resId = getResources().getIdentifier("dice_" + diceNumber, "drawable", requireActivity().getPackageName());
        //        diceImage.setImageResource(resId);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Kann leer bleiben, falls nicht benötigt.
    }
}
