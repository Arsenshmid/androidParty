package com.example.party6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

public class EquipmentSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_selection);

        // Находим радиогруппу и кнопку "Подтвердить"
        RadioGroup equipmentRadioGroup = findViewById(R.id.equipmentRadioGroup);
        Button confirmButton = findViewById(R.id.confirmButton);

        // Обработчик нажатия на кнопку "Подтвердить"
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем выбранный элемент из радиогруппы
                int selectedId = equipmentRadioGroup.getCheckedRadioButtonId();
                RadioButton selectedRadioButton = findViewById(selectedId);
                String selectedEquipment = selectedRadioButton.getText().toString();

                // Сохраняем выбранную аппаратуру
                saveSelectedEquipment(selectedEquipment);

                // Закрываем активность
                finish();
            }
        });
    }

    // Метод для сохранения выбранной аппаратуры
    private void saveSelectedEquipment(String equipment) {
        MainActivity mainActivity = new MainActivity();
        mainActivity.saveSelectedEquipment(equipment);
    }
}
