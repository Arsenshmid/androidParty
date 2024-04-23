package com.example.party6.ui.login;

import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.party6.data.LoginDataSource;
import com.example.party6.data.LoginRepository;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    private final Context context; // Добавлено поле для хранения контекста

    // Конструктор для получения контекста
    public LoginViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            // Передаем контекст вместе с LoginDataSource
            return (T) new LoginViewModel(LoginRepository.getInstance(new LoginDataSource(), context));
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}
