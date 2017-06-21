# ExpositJavaTest
# Task 1 (1.1 1.2 1.3) (http downloader)
Для того, чтобы запустить приложение:
  1. Скачайте архив в корне репозитория JavaTasksCompiledExposit.rar (содержит 1 и 2 задание). Так же можно найти в папке target
     в исходниках проектов.
  2. Для запуска 1-ого задания (загрузчик файлов по http) необходимо:
        1. При скачивании по ссылке необходимы аргументы: -l ваша_ссылка -p путь_сохраниня -n имя_файла.* 
                                                                (необходимо обязательно указывать формат).
           <br>#Пример: java -jar Downloader-1.0.jar -l https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ-JRS0HQYmkScuDy3IZEf0Ebju9gJCO9EeTm6z6GTXTQBsXDdZ08kiAQ
                                                -p X:\newfolder -n animal.jpg
                                                <br>
        2. При взятии ссылок из файла (xml, json, csv) необходимы следующие аргументы: -f путь_к_файлу -p путь_сохранения -t кол-во
            потоков (опционально)
           <br>#Пример: java -jar Downloader-1.0.jar -f X:\folder\links.json -p X:\downloads -t 4
            
# Task 2 (csv searcher)
  1. Скачайте архив в корне репозитория JavaTasksCompiledExposit.rar (содержит 1 и 2 задание). Так же можно найти в папке target
     в исходниках проектов.
  2. Для запуска 2-ого задания (поиска столбцов в csv) необходимо:
        1. необходимые параметры -i входной_csv_файл -q строка_поиска -o выходной_файл(txt или csv)
          <br>#Пример: java -jar CsvParser-1.0.jar -i X:\folder\input.csv -q one -o X:\newfolder\output.txt
                                                                                        
