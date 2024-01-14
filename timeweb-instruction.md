# Инструкция по подключению к виртуальной машине Linux.

Виртуальная машина выдается, если операционная система на компьютере студента **старше, чем Windows 10 либо MacOS Big Sur**. Доступ к виртуальной машине выдается на неделю, этого времени должно быть достаточно для выполнения домашней работы. Для вас бронируется виртуальная машина, вы получаете ip адрес, логин и пароль. При необходимости продления срока пользования виртуальной машиной (например, выполнение дипломной работы) необходимо написать сотруднику Нетологии, который выдавал вам доступ.

На виртуальной машине все готово для выполнения заданий с использованием Docker (установлены Git, Docker, Java и тд). Стоит отметить, что у вас не будет UI интерфейса, все действия выполняются с помощью терминальных команд.
Подключение к виртуальной машине происходит с помощью протокола ssh. Вы можете использовать любой удобный/известный вам способ для работы с ssh. Ниже приведены стандартные инструкции:  
[Описание программы **Putty**](https://timeweb.com/ru/help/display/DOC/PuTTY)  
[Инструкция по настройке и подключению](https://timeweb.com/ru/community/articles/vse-o-putty-ustanovka-nastroyka-osnovnye-komandy)

#### Последовательность действий при выполнения заданий
Вы можете выполнять задания в иной последовательности, если вам удобнее.
1. На личной машине подготовить шаблон проекта (например, создать и заполнить файл **docker-compose.yml**) и запушить проект на git-сервер в заранее подготовленный репозиторий (например, на Github).
2. После подключения к виртуальной машине вы попадаете в директорию пользователя. Никуда не перемещаетесь и клонируете свой проект с git-сервера `git clone <name of repository>`.
3. Перейти в директорию клонированного проекта `cd <name of repository>`, теперь это ваша рабочая директория и все команды выполняются здесь.
4. Можно запускать контейнеры, экспериментировать с конфигурацией, редактируя конфигурационные файлы непосредственно в терминале.
5. Когда вы настроите и запустите docker контейнеры в виртуальной машине, то запуск jar-приложения (SUT) и написание/запуск тестов можно производить уже на собственном компьютере. Отличие будет лишь в том, что **вместо localhost** вы везде будете **указывать ip виртуальной машины** выданной вам в самом начале. Если вы корректно настроили конфигурацию контейнеров, то они будут доступны по сети (будьте внимательны во время настройки портов для контейнера).

#### Шпаргалка с базовыми linux-командами
`cd <folder's name>` - переход в директорию, расположенную в текущем каталоге.  
`cd ..` - переход в родительский каталог.  
`cd ~` - переход домашний каталог, куда вы попали при авторизации.  
`ls` - просмотр файлов и директорий в текущем каталоге.  
`pwd` - вывести название текущего каталога.  
`mkdir <folder's name>` - создание новой директории в текущем каталоге.  
`touch <file's name with extension>` - создание нового файла в текущем каталоге.  
`rm <file's name>` - удаление файла в текущем каталоге.  
`rm -r <folder's name>` - удаление директории в текущем каталоге.  
`nano <file's name>` - открытие файла из текущего каталога для редактирования.  
`cat <file's name>` - просмотр содержимого файла из текущего каталога.