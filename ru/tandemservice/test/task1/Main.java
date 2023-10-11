package ru.tandemservice.test.task1;

import ru.tandemservice.test.task1.utils.DataGenerationUtils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> data = DataGenerationUtils.createTestData();
        Task1Impl.getInstance().sort(data, 0);
        DataGenerationUtils.printCollectionRandomValues(data);
    }
}
