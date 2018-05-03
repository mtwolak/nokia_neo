package neo.example.demo;

import java.util.Collection;

class Utils {
    static boolean isEmptyList(Collection<String> listToCheck) {
        return listToCheck == null || listToCheck.isEmpty();
    }
}
