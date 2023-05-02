package com.example.wsrfood.activities.signUp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.wsrfood.R;
import com.example.wsrfood.activities.signIn.SignInActivity;
import com.example.wsrfood.databinding.ActivitySignUpBinding;
import com.example.wsrfood.utilities.Validation;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";
    private ActivitySignUpBinding binding;
    private SignUpViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupListeners();
        viewModel = new ViewModelProvider(this).get(SignUpViewModel.class);
        observeViewModel();
    }

    private void setupListeners() {
        binding.buttonCancel.setOnClickListener(view -> {
            startActivity(SignInActivity.newIntent(this));
        });
        binding.buttonSignUp.setOnClickListener(view -> {
            String email = getTrimmedValue(binding.editEmail);
            String password = getTrimmedValue(binding.editPassword);
            String fullName = getTrimmedValue(binding.editFullName);
            String phone = getTrimmedValue(binding.editPhoneNumber);

            if (Validation.isEmailValid(email)
                    && password.length() > 4
                    && fullName.length() > 3
                    && Validation.isPhoneNumberValid(phone)) {
                viewModel.signUp(email, password, fullName, phone);
            } else {
                showAlertDialog(this,
                        getString(R.string.invalid_data),
                        getString(R.string.fill_the_data_correctly));
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, err -> {
            Log.d(TAG, err);
        });
        viewModel.getIsSignedUp().observe(this, isSignedUp -> {
            if (isSignedUp) {
                startActivity(SignInActivity.newIntent(this));
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    public static String getTrimmedValue(TextView textView) {
        return textView.getText().toString().trim();
    }

    public static void showAlertDialog(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton(context.getString(R.string.ok), (dialogInterface, i) -> {
                    dialogInterface.cancel();
                })
                .create().show();
    }
}