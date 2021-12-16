package tasks;

import common.Person;
import common.PersonService;
import common.Task;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*
Задача 1
Метод на входе принимает List<Integer> id людей, ходит за ними в сервис
(он выдает несортированный Set<Person>, внутренняя работа сервиса неизвестна)
нужно их отсортировать в том же порядке, что и переданные id.
Оценить асимпотику работы
 */
public class Task1 implements Task {

  // !!! Редактируйте этот метод !!!
  //делаю мапу, id -> Person, повторных Персонов быть не может т.к. делаем из сета
  //прохожу по листу доставая из мапы Персонов
  //всего 2 прохода, O(n)
  private List<Person> findOrderedPersons(List<Integer> personIds) {
    Set<Person> persons = PersonService.findPersons(personIds);

    Map<Integer, Person> idPersonMap = persons.stream().collect(Collectors.toMap(Person::getId, person -> person));
    return personIds.stream().map(idPersonMap::get).collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    List<Integer> ids = List.of(1, 2, 3);

    return findOrderedPersons(ids).stream()
        .map(Person::getId)
        .collect(Collectors.toList())
        .equals(ids);
  }

}
