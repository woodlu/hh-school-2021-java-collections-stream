package tasks;

import common.Area;
import common.Person;
import common.Task;

import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 implements Task {

  //без использования стримов мне кажется получается более понятно и наглядно, но хочу попрактиковать стримы
  //2 варианта, один добавляет строки в сет, второй возвращает строки и собирает в сет
  // первый ваиант мне кажется более понятным
  private Set<String> getPersonDescriptions(Collection<Person> persons,
                                            Map<Integer, Set<Integer>> personAreaIds,
                                            Collection<Area> areas) {
    Set<String> result = new HashSet<>();

    persons.stream().forEach(p -> personAreaIds.get(p.getId()).stream()
            .forEach(areaId -> result.add(p.getFirstName() + " - " + areas.stream().filter(a -> a.getId() == areaId).findAny().get().getName())));

//    Set<String> result = persons.stream().flatMap(
//            p -> personAreaIds.get(p.getId()).stream()
//                    .map(aId -> areas.stream().filter(a -> a.getId() == aId).findAny().get().getName())
//                    .collect(Collectors.toSet()).stream().map(a -> p.getFirstName() + " - " + a)
//            )
//            .collect(Collectors.toSet());

    return result;
  }

  @Override
  public boolean check() {
    List<Person> persons = List.of(
        new Person(1, "Oleg", Instant.now()),
        new Person(2, "Vasya", Instant.now())
    );
    Map<Integer, Set<Integer>> personAreaIds = Map.of(1, Set.of(1, 2), 2, Set.of(2, 3));
    List<Area> areas = List.of(new Area(1, "Moscow"), new Area(2, "Spb"), new Area(3, "Ivanovo"));
    return getPersonDescriptions(persons, personAreaIds, areas)
        .equals(Set.of("Oleg - Moscow", "Oleg - Spb", "Vasya - Spb", "Vasya - Ivanovo"));
  }

}
