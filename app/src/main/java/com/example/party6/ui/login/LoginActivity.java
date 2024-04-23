package com.example.party6.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.party6.MainActivity;
import com.example.party6.R;
import com.example.party6.data.LoginDataSource;
import com.example.party6.data.LoginRepository;
import com.example.party6.data.Result;
import com.example.party6.data.model.LoggedInUser;

public class LoginActivity extends AppCompatActivity {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private ProgressBar loadingProgressBar;

    private LoginRepository loginRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);

        loginRepository = LoginRepository.getInstance(new LoginDataSource(), this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                login(username, password);
            }
        });
    }

    private void login(String username, String password) {
        Result<LoggedInUser> result = loginRepository.login(username, password);
        if (result instanceof Result.Success) {
            LoggedInUser loggedInUser = ((Result.Success<LoggedInUser>) result).getData();
            String userType = loggedInUser.getDisplayName().equals("admin") ? "admin" : "user";
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("userType", userType);
            startActivity(intent);
            finish(); // Закрываем текущую активность, чтобы при возвращении на нее пользователь не мог вернуться к форме входа
        } else if (result instanceof Result.Error) {
            Exception error = ((Result.Error) result).getError();
            Toast.makeText(this, "Ошибка входа в систему: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
