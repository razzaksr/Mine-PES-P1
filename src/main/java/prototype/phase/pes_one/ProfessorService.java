package prototype.phase.pes_one;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService implements ProfessorRepository {
    File file=new File("mec.csv");
    private List<Professor> professors = new ArrayList<>();

    public List<Professor> getAllProfessors() {
        try{
            if(!file.exists())
                file.createNewFile();
            Reader reader = new FileReader("mec.csv");
            CsvToBean<Professor> csvToBean = new CsvToBeanBuilder<Professor>(reader)
            .withType(Professor.class).withIgnoreLeadingWhiteSpace(true).build();
            professors = csvToBean.parse();
        }catch(IOException e ){
            e.printStackTrace();
        }
        return professors;
    }

    public Optional<Professor> getProfessorById(int id) {
        professors = getAllProfessors();
        return professors.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Professor addProfessor(Professor professor) {
        try {
            professors = getAllProfessors();
            professors.add(professor);
            if(!file.exists())
                file.createNewFile();
            Writer writer = new FileWriter("mec.csv");
            StatefulBeanToCsv<Professor> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Professor>(writer).build();
            statefulBeanToCsv.write(professors);
            writer.flush();
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            e.printStackTrace();
        }
        return professor;
    }

    public Professor updateProfessor(int id, Professor updatedProfessor) {
        professors = getAllProfessors();
        for (int i = 0; i < professors.size(); i++) {
            if (professors.get(i).getId() == id) {
                professors.set(i, updatedProfessor);
                try {
                    Writer writer = new FileWriter("mec.csv");
                    StatefulBeanToCsv<Professor> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Professor>(writer).build();
                    statefulBeanToCsv.write(professors);
                    writer.flush();
                } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
                    e.printStackTrace();
                }
                return updatedProfessor;
            }
        }
        return null;
    }

    public boolean deleteProfessor(int id) {
        professors = getAllProfessors();
        boolean returned = professors.removeIf(p -> p.getId() == id);
        try {
            Writer writer = new FileWriter("mec.csv");
            StatefulBeanToCsv<Professor> statefulBeanToCsv = new StatefulBeanToCsvBuilder<Professor>(writer).build();
            statefulBeanToCsv.write(professors);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException | IOException e) {
            e.printStackTrace();
        }
        return returned;
    }
}