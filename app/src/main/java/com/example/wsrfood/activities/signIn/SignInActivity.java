package com.example.wsrfood.activities.signIn;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wsrfood.R;
import com.example.wsrfood.activities.main.MainActivity;
import com.example.wsrfood.activities.signUp.SignUpActivity;
import com.example.wsrfood.databinding.ActivitySignInBinding;
import com.example.wsrfood.utilities.Constants;
import com.example.wsrfood.utilities.Validation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class SignInActivity extends AppCompatActivity {

    private ActivitySignInBinding binding;
    private SignInViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(SignInViewModel.class);
        observeViewModel();
        setupListeners();
    }

    private void setupListeners() {
        binding.textViewForgotPassword.setOnClickListener(view -> {
            startActivity(SignUpActivity.newIntent(this));
        });
        binding.buttonSignIn.setOnClickListener(view -> {

            String email = getTrimmedValue(binding.editTextEmail);
            String password = getTrimmedValue(binding.editTextPassword);

            if (Validation.isEmailValid(email) && password.length() > 4) {
                viewModel.signInWithEmailAndPassword(email, password);
            } else {
                showAlertDialog(this,
                        getString(R.string.invalid_data),
                        getString(R.string.invalid_login_or_password));
            }
        });
    }

    private void observeViewModel() {
        viewModel.getError().observe(this, err -> {
            Toast.makeText(this, err, Toast.LENGTH_SHORT).show();
        });
        viewModel.getToken().observe(this, token -> {
            try {
                Jws<Claims> jwt = Jwts.parserBuilder()
                        .setSigningKey(Constants.SECRET_KEY.getBytes())
                        .build()
                        .parseClaimsJws(token);
                startActivity(MainActivity.newIntent(this));
            } catch (Exception err) {
                Log.d("token", err.getMessage());
                Toast.makeText(this, R.string.invalid_token, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SignInActivity.class);
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