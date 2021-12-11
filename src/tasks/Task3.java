package tasks;

import common.Person;
import common.Task;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/*
Задача 3
Отсортировать коллекцию сначала по фамилии, по имени (при равной фамилии), и по дате создания (при равных фамилии и имени)
 */
public class Task3 implements Task {

  // !!! Редактируйте этот метод !!!

  //делаю свой компаратор
  private List<Person> sort(Collection<Person> persons) {

    return persons.stream().sorted((p1, p2) -> {
      if (p1.getSecondName().compareTo(p2.getSecondName()) > 0) return 1;
      if (p1.getSecondName().compareTo(p2.getSecondName()) < 0) return -1;
      if (p1.getFirstName().compareTo(p2.getFirstName()) > 0) return 1;
      if (p1.getFirstName().compareTo(p2.getFirstName()) < 0) return -1;
      if (p1.getCreatedAt().compareTo(p2.getCreatedAt()) > 0) return 1;
      if (p1.getCreatedAt().compareTo(p2.getCreatedAt()) < 0) return -1;
      return 0;
    }).collect(Collectors.toList());
  }

  @Override
  public boolean check() {
    Instant time = Instant.now();
    List<Person> persons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(2, "Vasya", "Petrov", time),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1))
    );
    List<Person> sortedPersons = List.of(
        new Person(1, "Oleg", "Ivanov", time),
        new Person(4, "Oleg", "Ivanov", time.plusSeconds(1)),
        new Person(3, "Oleg", "Petrov", time.plusSeconds(1)),
        new Person(2, "Vasya", "Petrov", time)
    );
    return sortedPersons.equals(sort(persons));
  }
}
