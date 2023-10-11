package ru.tandemservice.test.task1.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataGenerationUtils {

    private DataGenerationUtils() {}

    /**
     * В этом классе есть методы printCollectionRandomValues(fillCollectionRandomValues(10));
     * С их помощью были созданы тестовые данные размером 10 х 10. Они ниже.
     * Сделано выравнивание для удобства. Среди данных есть и null, и пустота, и числа, и строковые значения.
     *
     * Сами данные:
     *
     * GfMM   	sAcNUNT  	null      	null 	null   	93        	MgRUmJD	null
     * ohBrM  	null     	null      	null 	150    	null      	null   	null	607
     * 696    	XksgvVbQp	          	19   	flxD   	null      	QfigHi 	491 	475 	YiqOT
     * 810    	         	640       	null 	351    	null      	       	null	    	null
     * null   	null     	          	null 	krnujTP	          	       	685 	null	664
     *        	null     	hJTTyQZZVd	TUGwy	849    	552       	null   	    	    	null
     * 810  	226      	null      	     	null   	null      	377    	null	217
     * wTYztxX	OWHP     	          	sTVP 	       	apOXuXCLtT	       	937 	    	null
     * NykOpo 	152      	796       	null 	888    	202       	       	SQJX	678
     * 791    	         	KTBkAePNSx	223  	null   	121       	       	    	    	338
     *
     * В метод ниже, в коллекцию, добавлены данные из 1, 5 и 10 столбиков.
     * Для тестирования метода: void sort(List<String[]> rows, int columnIndex)
     */
    public static List<String[]> createTestData() {
        List<String[]> data = new ArrayList<>();
        data.add(new String[] { "GfMM",    null,      ""      });
        data.add(new String[] { "ohBrM",   "150",     ""      });
        data.add(new String[] { "696",     "flxD",    "YiqOT" });
        data.add(new String[] { "810",     "351",     null    });
        data.add(new String[] { null,      "krnujTP", "664"   });
        data.add(new String[] { "",        "849",     null    });
        data.add(new String[] { "810",     null,      ""      });
        data.add(new String[] { "wTYztxX", "",        null    });
        data.add(new String[] { "NykOpo",  "888",     ""      });
        data.add(new String[] { "791",     null,      "338"   });
        return data;
    }

    public static void printCollectionRandomValues(List<String[]> data) { // O(row*columnCount)
        int columnCount = data.get(0).length;
        int[] columnWidths = new int[columnCount];
        for (String[] row : data) {
            for (int j = 0; j < columnCount; j++) {
                String value = row[j] != null ? row[j] : "null";
                columnWidths[j] = Math.max(columnWidths[j], value.length());
            }
        }
        for (String[] row : data) {
            for (int j = 0; j < columnCount; j++) {
                String value = row[j] != null ? row[j] : "null";
                String format = "%-" + columnWidths[j] + "s\t";
                System.out.printf(format, value);
            }
            System.out.println();
        }
    }

    public static List<String[]> fillCollectionRandomValues(int size) { // O(size^2)
        List<String[]> data = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String[] row = new String[size];
            for (int j = 0; j < size; j++) {
                row[j] = generateRandomValue();
            }
            data.add(row);
        }
        return data;
    }

    private static String generateRandomValue() { // O(choice)
        Random random = new Random();
        int choice = random.nextInt(4);
        return switch (choice) {
            case 0  -> null;
            case 1  -> "";
            case 2  -> randomWord(4 + random.nextInt(7));
            default -> String.valueOf(random.nextInt(1000));
        };
    }

    private static String randomWord(int length) { // O(length)
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuilder word = new StringBuilder();
        for (int i = 0; i < length; i++) {
            word.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return word.toString();
    }

}
