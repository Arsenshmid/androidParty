import os
import pyperclip

# Список файлов для обработки
files = [
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/data/model/LoggedInUser.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/data/model/LoginActivity.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/data/LoginDataSource.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/data/LoginRepository.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/data/Result.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/ui/login/LoggedInUserView.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/ui/login/LoginActivity.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/ui/login/LoginFormState.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/ui/login/LoginResult.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/ui/login/LoginViewModel.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/ui/login/LoginViewModelFactory.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/DBHelper.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/MainActivity.java',
'C:/Users/apce1/Desktop/party6/app/src/main/res/layout/activity_login.xml',
'C:/Users/apce1/Desktop/party6/app/src/main/res/layout/activity_main.xml',
'C:/Users/apce1/Desktop/party6/build.gradle',
'C:/Users/apce1/Desktop/party6/app/build.gradle',
'C:/Users/apce1/Desktop/party6/settings.gradle',
'C:/Users/apce1/Desktop/party6/app/src/main/AndroidManifest.xml',
'C:/Users/apce1/Desktop/party6/app/src/main/res/drawable/news_background.xml',
'C:/Users/apce1/Desktop/party6/app/src/main/res/drawable/white_background.xml',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/NewsItem.java',
'C:/Users/apce1/Desktop/party6/app/src/main/res/drawable/round_button.xml',
'C:/Users/apce1/Desktop/party6/app/src/main/res/layout/activity_add_news.xml',
'C:/Users/apce1/Desktop/party6/app/src/main/res/layout/activity_profile.xml',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/AddNewsActivity.java',
'C:/Users/apce1/Desktop/party6/app/src/main/java/com/example/party6/ProfileActivity.java',

]

# Открываем файл для записи в кодировке UTF-8
with open('сохранено.txt', 'w', encoding='utf-8') as output_file:
    for file_path in files:
        # Печатаем имя файла в сохраненный файл
        output_file.write('\n\n\n\n\n\n\n\n\n\n\n\n\nFile: {}\n'.format(file_path))
        
        # Открываем файл и записываем его содержимое в сохраненный файл
        with open(file_path, 'r', encoding='utf-8') as input_file:
            output_file.write(input_file.read())

# Открываем файл сохранено.txt и копируем его содержимое в буфер обмена
with open('сохранено.txt', 'r', encoding='utf-8') as saved_file:
    file_content = saved_file.read()
    pyperclip.copy(file_content)

# Открываем файл сохранено.txt для просмотра
os.system('notepad сохранено.txt')
