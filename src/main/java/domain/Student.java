package domain;

import json.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private List<Tuple<String, Integer>> exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = Arrays.asList(exams);
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject json = super.toJsonObject();
        JsonObject[] studentExams = new JsonObject[exams.size()];
        for (int i = 0; i < exams.size(); i++) {
            Tuple<String , Integer> exam = exams.get(i);
            studentExams[i] = new JsonObject();
            studentExams[i].add(new JsonPair("course", new JsonString(exam.key)));
            studentExams[i].add(new JsonPair("mark", new JsonNumber(exam.value)));
            studentExams[i].add(new JsonPair("passed", new JsonBoolean(exam.value > 2)));
        }
        json.add(new JsonPair("exams", new JsonArray(studentExams)));
        return json;
    }
}