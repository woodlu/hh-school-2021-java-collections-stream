package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй

  //Не совсем понял что здесь нужно сделать
  //Отдать List без первого элемента либо пустой лист?
  public List<String> getNames(List<Person> persons) {
    if (persons.size() <= 1) {
      return Collections.emptyList();
    }
  //  persons.remove(0);
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется

  //можно просто воспользоваться конструктором HashSet
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут

  //укоротил запись
  public String convertPersonToString(Person person) {
    return person == null ? "" : person.getFirstName() + " " + person.getSecondName();
  }

  // словарь id персоны -> ее имя

  //сделал стримом
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(p -> p.getId(), p -> convertPersonToString(p)));
  }

  // есть ли совпадающие в двух коллекциях персоны?

  //сделал стримом
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return persons1.stream().anyMatch(p1 -> persons2.contains(p1));
  }

  //...

  //убрал переменную count
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).mapToInt(Integer::intValue).sum();
  }

  //???
  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = true;
    boolean reviewerDrunk = false;
    return codeSmellsGood || reviewerDrunk;
  }
}
