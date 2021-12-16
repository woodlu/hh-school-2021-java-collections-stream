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
  //persons.remove() изменяет исходный лист
  public List<String> getNames(List<Person> persons) {
    if (persons.size() <= 1) {
      return Collections.emptyList();
    }
    return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется

  //можно просто воспользоваться конструктором HashSet
  public Set<String> getDifferentNames(List<Person> persons) {
    return new HashSet<>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут

  //сделал joining
  public String convertPersonToString(Person person) {
    return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
            .filter(Objects::nonNull).collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя

  //сделал чтобы не кидало ексепшн при повторе персона
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    return persons.stream().collect(Collectors.toMap(Person::getId, this::convertPersonToString, (person1, person2) -> person1));
  }

  // есть ли совпадающие в двух коллекциях персоны?

  //запихнул первую коллекцию в сет
  //добавил разные способы решения
  //2 прохода по коллекции -> O(n)
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    return persons2.stream().anyMatch(new HashSet<>(persons1)::contains);
  }

  //...

  //затупил... вместо каунта зачем-то сумму считал
  //переменную count убрал т.к. непонятно зачем она вообще нужна, она не паблик статик
  //никакого доступа к ней всё равно нет и кроме этого метода больше нигде не использовалась
  public long countEven(Stream<Integer> numbers) {
    return numbers.filter(num -> num % 2 == 0).count();
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
