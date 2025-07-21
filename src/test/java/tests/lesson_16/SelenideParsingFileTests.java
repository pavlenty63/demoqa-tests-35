package tests.lesson_16;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

@DisplayName("Тесты с различными файлами")
public class SelenideParsingFileTests {
  private ClassLoader cl = SelenideParsingFileTests.class.getClassLoader();

  @Test
  @DisplayName("Проверка наличия текста в PDF-файле")
  void pdfFileParsingTest() throws Exception {
    try (InputStream zip_file = cl.getResourceAsStream("pdf_example.zip")) {
      assert zip_file != null;
      try (ZipInputStream pdf_file = new ZipInputStream(zip_file)) {
        ZipEntry entry;
        while ((entry = pdf_file.getNextEntry()) != null) {
          if (entry.getName().contains("pdf_example.pdf")) {
            PDF pdf = new PDF(pdf_file);
            assertThat(pdf.text).contains("Lorem ipsum");
          }
        }

      }
    }
  }

  @Test
  @DisplayName("Проверка записи в ячейке в Exel-файле")
  void xlsFileParsingTest() throws Exception {
    try (InputStream zip_file = cl.getResourceAsStream("xls_example.zip")) {
      assert zip_file != null;
      try (ZipInputStream xls_file = new ZipInputStream(zip_file)) {
        ZipEntry entry;
        while ((entry = xls_file.getNextEntry()) != null) {
          if (entry.getName().contains("xls_example.xls")) {
            XLS xls = new XLS(xls_file);
            String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(4).getStringCellValue();
            Assertions.assertTrue(actualValue.contains("France"));
          }
        }

      }
    }
  }

  @Test
  @DisplayName("Проверка массивов в CSV-файле")
  void csvFileParsingTest() throws Exception {
    try (InputStream zip_file = cl.getResourceAsStream("csv_example.zip")) {
      assert zip_file != null;
      try (ZipInputStream csv_file = new ZipInputStream(zip_file)) {
        ZipEntry entry;
        while ((entry = csv_file.getNextEntry()) != null) {
          if (entry.getName().contains("csv_example.csv")) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(csv_file));
            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(6, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"John", "Doe", "120 jefferson st.", "Riverside", "NJ", "08075"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"Jack", "McGinnis", "220 hobo Av.", "Phila", "PA", "09119"},
                    data.get(1)
            );

          }
        }
      }
    }
  }
}