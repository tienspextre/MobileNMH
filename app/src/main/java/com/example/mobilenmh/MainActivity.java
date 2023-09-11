package com.example.mobilenmh;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import yuku.ambilwarna.AmbilWarnaDialog;

public class MainActivity extends AppCompatActivity {

    private Button shapeButton, colorButton, submitButton;
    int chosenColor = Color.RED;
    RelativeLayout relativeLayout;
    private final String[] shapeOptions = {"Circle", "Rectangle", "Triangle", "Square", "Oval"};
    int chosenShape = 0;
    ShapeView shapeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shapeView = findViewById(R.id.shapeView);
        shapeView.setShapeType(chosenShape);
        shapeView.setShapeColor(chosenColor);

        shapeButton = findViewById(R.id.shapeButton);
        colorButton = findViewById(R.id.colorButton);
        submitButton = findViewById(R.id.submitButton);

        shapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShapePickerDialog();
            }
        });

        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle color button click, e.g., show a dialog or another activity for color selection.
                showColorPickerDialog(false);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle submit button click and navigate to Scene 2 with chosen shape and color.
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                // Pass chosen shape and color data as extras in the intent.
//                 intent.putExtra("shape", chosenShape);
                intent.putExtra("color", chosenColor);
                intent.putExtra("shape", chosenShape);
                startActivity(intent);
            }
        });
    }
    private void showColorPickerDialog(boolean AlphaSupport) {
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(MainActivity.this, chosenColor, AlphaSupport, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onOk(AmbilWarnaDialog ambilWarnaDialog, int color) {
                chosenColor = color;
                shapeView.setShapeColor(chosenColor);
//                relativeLayout.setBackgroundColor(color);
            }

            @Override
            public void onCancel(AmbilWarnaDialog ambilWarnaDialog) {

                Toast.makeText(MainActivity.this, "Color Picker Closed", Toast.LENGTH_SHORT).show();
            }
        });
        ambilWarnaDialog.show();
    }

    private void showShapePickerDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a Shape");
        builder.setItems(shapeOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                chosenShape = which;
                shapeView.setShapeType(chosenShape);
            }
        });
        builder.show();
    }

}
