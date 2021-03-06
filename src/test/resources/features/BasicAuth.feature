# language: ru
@all
Функция: авторизация на сайте
  Алерт должен спросить у пользователя текст
  Вебстраница должна отобразить текст, содержащий в себе ранее введеный текст

  @correct
  Сценарий: Успешная авторизация
    Допустим сайт с базовой аутентификацией и верными данными открыт
    И пользователь нажал DISPLAY IMAGE
    Тогда на вебстранице отображается изображение с данными входа

  @fail
  Сценарий: Неуспешная авторизация
    Допустим сайт с базовой аутентификацией и неверными данными открыт
    И пользователь нажал DISPLAY IMAGE
    Тогда на вебстранице не отображается изображение с данными входа