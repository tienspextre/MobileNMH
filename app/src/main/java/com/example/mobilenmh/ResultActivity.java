package com.example.mobilenmh;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get the chosen shape and color data from the intent extras
        Intent intent = getIntent();
        if (intent != null) {
            // Replace these lines with your logic to retrieve chosen shape and color
            int chosenColor = intent.getIntExtra("color", Color.BLACK); // Default color is black
            int chosenShape = intent.getIntExtra("shape", 0);

            // Set the text for the shape and color TextViews
            ShapeView shapeView = findViewById(R.id.shapeView);

            // Set the shape type (0 for circle, 1 for rectangle)
            shapeView.setShapeType(chosenShape); // Change this to the desired shape type

            // Set the shape color
            shapeView.setShapeColor(chosenColor);

        }
    }
}
