package com.example.cafmealplanner.ui.Menu;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafmealplanner.R;

import java.util.Vector;

public class FoodInfo extends Fragment implements View.OnClickListener {

    private FoodInfoViewModel mViewModel;

    public static FoodInfo newInstance() {
        return new FoodInfo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_food_info, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create a vector of strings to keep all the ingredients
        Vector<String> ingredientNames = new Vector<String>(3);
        ingredientNames.add(new String("White jasmine rice"));
        ingredientNames.add(new String("chopped pork"));
        ingredientNames.add(new String("mushrooms"));
        ingredientNames.add(new String("celery"));
        ingredientNames.add(new String("cabbage"));
        ingredientNames.add(new String("sesame"));

        // Display the ingredients list in the text view
        TextView ingredientsList = getView().findViewById(R.id.ingredients);

        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientsList.append(ingredientNames.get(i));

            if (i < ingredientNames.size() - 1) {
                ingredientsList.append(", ");
            }
        }

        // Implement the "rate this" button
        Button addRating = getView().findViewById(R.id.rating);
        addRating.setOnClickListener((View.OnClickListener) this);
        getView().findViewById(R.id.backToMenu).setOnClickListener(this);

        //allow info text to scroll
        ingredientsList.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.rating)) {
            // Get the rating the user entered
            EditText rating = getView().findViewById(R.id.rateThis);
            Integer numStars = Integer.valueOf(rating.getText().toString());

            int i;
            ImageView star;

            // Change the number of stars the user entered to yellow

            for (i = 0; i < numStars; i++) {

                if (i == 0)
                    star = getView().findViewById(R.id.star1);
                else if (i == 1)
                    star = getView().findViewById(R.id.star2);
                else if (i == 2)
                    star = getView().findViewById(R.id.star3);
                else if (i == 3)
                    star = getView().findViewById(R.id.star4);
                else
                    star = getView().findViewById(R.id.star5);

                star.setImageResource(R.drawable.sss);
                star.setAdjustViewBounds(true);

                star.setMaxHeight(50);
                star.setMaxWidth(50);
            }

            // Change the remaining amount of stars to gray

            while (i < 5) {
                if (i == 0)
                    star = getView().findViewById(R.id.star1);
                else if (i == 1)
                    star = getView().findViewById(R.id.star2);
                else if (i == 2)
                    star = getView().findViewById(R.id.star3);
                else if (i == 3)
                    star = getView().findViewById(R.id.star4);
                else
                    star = getView().findViewById(R.id.star5);

                star.setImageResource(R.drawable.gray_star);

                star.setAdjustViewBounds(true);
                star.setMaxHeight(50);
                star.setMaxWidth(50);

                i++;
            }
        }

        else if (v == getView().findViewById(R.id.backToMenu)) {
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, MenuFragment.class, null);
            // Commit the transaction

            transaction.commit();
        }
    }
}