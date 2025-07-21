package tests.lesson_16;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.SuperHeroes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@DisplayName("Проверка данных команды супергероев")
public class JsonFileParsingTests {
  private ClassLoader cl = JsonFileParsingTests.class.getClassLoader();
  private final String pathToJsonFile = "heroes.json";

  @Test
  @DisplayName("Проверка дееспособности команды")
  void squadShouldBeActiveAndNumerousTest() throws Exception {
    try (Reader reader = new InputStreamReader(cl.getResourceAsStream(pathToJsonFile))) {
      ObjectMapper mapper = new ObjectMapper();
      SuperHeroes heroesData = mapper.readValue(reader, SuperHeroes.class);
      List<String> members = heroesData.members;

      Assertions.assertEquals(heroesData.squadName, "SuperHeroes");
      Assertions.assertTrue(heroesData.active);
      Assertions.assertEquals(3, members.size());
    }
  }

  @Test
  @DisplayName("Проверка суперспособностей команды")
  void expectedSuperPowersTest() throws Exception {
    try (Reader reader = new InputStreamReader(cl.getResourceAsStream(pathToJsonFile))) {
      ObjectMapper mapper = new ObjectMapper();
      SuperHeroes heroesData = mapper.readValue(reader, SuperHeroes.class);
      List<String> powers = heroesData.powers;
      List<String> expectedPowers = List.of(new String[]{"Immortality", "Teleportation", "Million tonne punch", "Damage resistance"});

      Assertions.assertEquals(expectedPowers, powers);
    }
  }
}

